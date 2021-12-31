package com.qxml.annotationprocess;

import com.google.auto.service.AutoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.qxml.annotationprocess.scanner.AttrMethodInfoScanner;
import com.qxml.annotationprocess.scanner.LocalVarScanner;
import com.qxml.tools.encrypt.RSAUtils;
import com.qxml.tools.log.LogUtil;
import com.qxml.tools.model.GenClassInfoModel;
import com.qxml.tools.model.LocalVarInfoModel;
import com.sun.source.util.Trees;
import com.yellow.qxml_annotions.LocalVar;
import com.yellow.qxml_annotions.ViewParse;

import java.io.Writer;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.zip.CRC32;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Types;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

import com.qxml.constant.Constants;
import com.yellow.qxml_annotions.ViewReplace;


@AutoService(Processor.class)
@SupportedAnnotationTypes({Constants.ATTR_ANNOTATION_CLASS_NAME, Constants.VIEW_PARSE_ANNOTATION_CLASS_NAME, Constants.VIEW_REPLACE_ANNOTATION_CLASS_NAME, Constants.ON_END_ANNOTATION_CLASS_NAME})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class QXmlProcessor extends AbstractProcessor {

    private Messager mMessager;
    private Types mTypes;
    private Filer mFiler;

    private Trees trees;

    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    private AttrMethodInfoScanner methodInfoScanner;
    private LocalVarScanner localVarScanner;
    private String rsaPrivateKey;
    private String qxmlValidCode;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mMessager = processingEnv.getMessager();
        mTypes = processingEnv.getTypeUtils();
        mFiler = processingEnv.getFiler();

        rsaPrivateKey = processingEnv.getOptions().get("RSA_PRIVATE_KEY");
        String debug = processingEnv.getOptions().get(Constants.QXML_LOG_ENABLE);
        qxmlValidCode = processingEnv.getOptions().get(Constants.QXML_VALID_CODE);

        LogUtil.setEnable(debug != null && debug.equalsIgnoreCase("true"));

        trees = Trees.instance(processingEnv);

        methodInfoScanner = new AttrMethodInfoScanner(trees, mTypes, mMessager);
        localVarScanner = new LocalVarScanner(trees);
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (set != null && set.size() != 0) {
            LogUtil.pl("process start ");
            long time = System.currentTimeMillis();
            GenClassInfoModel genClassInfoModel = new GenClassInfoModel();
            Set<? extends Element> viewParseAnnotations = roundEnvironment.getElementsAnnotatedWith(ViewParse.class);
            Set<? extends Element> viewReplaceAnnotations = roundEnvironment.getElementsAnnotatedWith(ViewReplace.class);

            Set<? extends Element> localVarAnnotations = roundEnvironment.getElementsAnnotatedWith(LocalVar.class);

            Map<String, LocalVarInfoModel> localVarInfoModelMap = localVarScanner.scanAndGenerate(localVarAnnotations);
            genClassInfoModel.setLocalVarMap(localVarInfoModelMap);
            methodInfoScanner.setLocalVarInfoModelMap(localVarInfoModelMap);

            collectFuncInfo(viewParseAnnotations, viewReplaceAnnotations, genClassInfoModel);
            genClassInfoModel.setSign("");
            genClassInfoModel.setValidCode("");

            String infos = gson.toJson(genClassInfoModel);
            if (rsaPrivateKey != null && !rsaPrivateKey.isEmpty()) {
                try {
                    CRC32 crc32 = new CRC32();
                    byte[] infoBytes = infos.getBytes();
                    crc32.update(infoBytes);
                    String crc32Str = Long.toHexString(crc32.getValue());
                    String md5Str = new String(MessageDigest.getInstance("md5").digest(infoBytes));
                    String signStr = crc32Str + "_" + md5Str + "_" + infos.length();
                    String sign = RSAUtils.encryptByPrivateKey(signStr, rsaPrivateKey);
                    genClassInfoModel.setSign(sign);
                    if (genClassInfoModel.getSign() == null || genClassInfoModel.getSign().isEmpty()) {
                        throw new RuntimeException("签名错误");
                    }
                    infos = gson.toJson(genClassInfoModel);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            } else {
                if (qxmlValidCode == null || qxmlValidCode.isEmpty()) {
                    throw new IllegalStateException("请在gradle.properties中添加一个QXML_VALID_CODE=**********(随机字符串)作为临时验签");
                }
                genClassInfoModel.setValidCode(qxmlValidCode);
                infos = gson.toJson(genClassInfoModel);
            }

            //LogUtil.pl("infos "+infos);
            try {
                FileObject fileObject = mFiler.createResource(StandardLocation.SOURCE_OUTPUT, Constants.QXML_CONIFG_PATH, Constants.QXML_PARSE_CONFIG_FILE_NAME);
                Writer w = fileObject.openWriter();
                w.write(infos);
                w.flush();
                w.close();
            } catch (Exception e) {
                System.out.println("f process err "+e.getMessage());
                e.printStackTrace();
            }
            long spendTime = (System.currentTimeMillis() - time);
            LogUtil.pl("processor cost time: "+spendTime+"ms");
        }
        return false;
    }

    private void collectFuncInfo(Set<? extends Element> viewParse, Set<? extends Element> viewReplace, GenClassInfoModel genClassInfoModel) {
        LogUtil.pl("process viewParse "+viewParse);
        LogUtil.pl("process viewReplace "+viewReplace);
        ArrayList<String> viewParseList = new ArrayList<>();
        ArrayList<String> viewReplaceList = new ArrayList<>();
        for (Element element: viewParse) {
            viewParseList.add(((TypeElement) element).getQualifiedName().toString());
            checkAndGenerate((TypeElement) element, (TypeElement) element);
        }
        for (Element element: viewReplace) {
            viewReplaceList.add(((TypeElement) element).getQualifiedName().toString());
            checkAndGenerate((TypeElement) element, (TypeElement) element);
        }
        genClassInfoModel.setVersionCode(Constants.QXML_VERSION_CODE);
        genClassInfoModel.setViewParseList(viewParseList);
        genClassInfoModel.setViewReplaceList(viewReplaceList);
        genClassInfoModel.setGenClassNameMap(methodInfoScanner.genClassNameMap);
        genClassInfoModel.setViewGenClassModelMap(methodInfoScanner.viewGenClassModelMap);
        genClassInfoModel.setInterfaceModelMap(methodInfoScanner.interfaceMethodInfoMap);
        genClassInfoModel.setParentClassMap(methodInfoScanner.parentClassMap);
        genClassInfoModel.setCompatViewInfoModelMap(methodInfoScanner.compatClassMap);
        genClassInfoModel.setLayoutParamInitMap(methodInfoScanner.layoutParamInitMap);
        genClassInfoModel.setCallOnFinishInflateMap(methodInfoScanner.callOnFinishInflateMap);

        //LogUtil.pl("genMap "+methodInfoScanner.genClassNameMap);
        /*LogUtil.pl("interfaceInfo "+methodInfoScanner.interfaceMethodInfoMap);
        LogUtil.pl("genInfo "+methodInfoScanner.viewGenClassModelMap);*/
    }

    private void checkAndGenerate(TypeElement element, TypeElement belongElement) {
        methodInfoScanner.checkAndGenerate(element, belongElement);
    }

}