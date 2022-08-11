package com.qxml.annotationprocess.scanner;

import com.qxml.constant.Constants;
import com.qxml.tools.log.LogUtil;
import com.qxml.tools.model.AttrFuncInfoModel;
import com.qxml.tools.model.CompatViewInfoModel;
import com.qxml.tools.model.LocalVarInfoModel;
import com.qxml.tools.model.ViewGenClassModel;
import com.sun.source.util.TreePath;
import com.sun.source.util.Trees;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeScanner;
import com.yellow.qxml_annotions.CallOnInflateConfig;
import com.yellow.qxml_annotions.ViewParse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.Messager;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

/**
 * 获取类被{@link com.yellow.qxml_annotions.Attr}
 * , {@link com.yellow.qxml_annotions.OnEnd}和{@link java.lang.Override}注解的方法信息
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class AttrMethodInfoScanner extends TreeScanner {

    private static final String VOID_CLASS_NAME = "java.lang.Void";

    public Map<String, ViewGenClassModel> interfaceMethodInfoMap = new HashMap<>();
    public Map<String, ViewGenClassModel> viewGenClassModelMap = new HashMap<>();
    public Map<String, Boolean> callOnFinishInflateMap = new HashMap<>();
    public Map<String, String> layoutParamInitMap = new HashMap<>();
    //viewGen与viewTypeName
    public Map<String, String> genClassNameMap = new HashMap<>();
    //类与其父类名
    public Map<String, String> parentClassMap = new HashMap<>();
    //类与其父类名
    public Map<String, CompatViewInfoModel> compatClassMap = new HashMap<>();

    private Map<String, LocalVarInfoModel> localVarInfoModelMap;

    private ViewGenClassModel curViewGenClassModel = null;

    private final AttrAnnotationScanner attrAnnotationScanner = new AttrAnnotationScanner();

    private final Trees trees;
    private final Types mTypes;
    private final Messager messager;
    private TypeElement curScanElement;
    private boolean curScanIsInterface;

    public AttrMethodInfoScanner(Trees trees, Types mTypes, Messager messager) {
        this.trees = trees;
        this.mTypes = mTypes;
        this.messager = messager;
    }

    public void setLocalVarInfoModelMap(Map<String, LocalVarInfoModel> localVarInfoModelMap) {
        this.localVarInfoModelMap = localVarInfoModelMap;
        attrAnnotationScanner.setLocalVarInfoModelMap(localVarInfoModelMap);
    }

    /**
     * 检查和生成Gen类的信息
     * 该方法只会生成该当前类可以获取到的被{@link com.yellow.qxml_annotions.Attr}, {@link com.yellow.qxml_annotions.OnEnd}和{@link java.lang.Override}注解的方法信息.
     * 不可访问到的父类（子module或依赖）中的信息不会生成.
     * 最后的信息合并在transform的ViewGenInfoCombiner中完成
     * @param element
     * @param belongElement
     */
    public void checkAndGenerate(TypeElement element, TypeElement belongElement) {
        if (element == null) {
            return;
        }
        String curElementName = element.getQualifiedName().toString();
        String belongElementName = belongElement.getQualifiedName().toString();
        if (element.getKind().isInterface()) {
            ViewGenClassModel interfaceMethodInfo = interfaceMethodInfoMap.get(curElementName);
            if (interfaceMethodInfo == null) {
                JCTree jcTree = (JCTree) trees.getTree(element);
                if (jcTree != null) {
                    visit(jcTree, element, belongElement);
                    TypeElement superElement = (TypeElement) mTypes.asElement(element.getSuperclass());
                    //LogUtil.pl("interface super "+superElement+" "+element.getSuperclass()+" "+element);
                    if (superElement != null) {
                        checkAndGenerate(superElement, belongElement);
                    }
                }
            } else {
                ViewGenClassModel genClassModel = viewGenClassModelMap.get(belongElementName);
                if (genClassModel != null) {
                    genClassModel.getFuncInfoModelHashMap().putAll(interfaceMethodInfo.getFuncInfoModelHashMap());
                    genClassModel.getOnEndFuncInfoModelMap().putAll(interfaceMethodInfo.getOnEndFuncInfoModelMap());
                    genClassModel.getOverrideFuncInfoModelList().addAll(interfaceMethodInfo.getOverrideFuncInfoModelList());
                }
            }
        } else {
            if (!viewGenClassModelMap.containsKey(curElementName)) {
                JCTree jcTree = (JCTree) trees.getTree(element);

                if (jcTree != null) {
                    TypeElement superElement = (TypeElement) mTypes.asElement(element.getSuperclass());
                    //先生成可接触到的父类
                    if (superElement != null && !Constants.OBJ_CLASS_NAME.equals(superElement.getQualifiedName().toString())) {
                        checkAndGenerate(superElement, superElement);
                    }
                    visit(jcTree, element, belongElement);
                    List<? extends TypeMirror> elementInterfaces = element.getInterfaces();
                    //先生成接口
                    for (TypeMirror typeMirror: elementInterfaces) {
                        TypeElement interfaceElement = (TypeElement) mTypes.asElement(typeMirror);
                        checkAndGenerate(interfaceElement, belongElement);
                    }
                    TypeElement parentElement = element;
                    String parentElementName = parentElement.getQualifiedName().toString();
                    ViewParse viewParse = parentElement.getAnnotation(ViewParse.class);
                    while (viewParse == null && !Constants.OBJ_CLASS_NAME.equals(parentElementName)) {
                        parentElement = (TypeElement) mTypes.asElement(parentElement.getSuperclass());
                        parentElementName = parentElement.getQualifiedName().toString();
                        viewParse = parentElement.getAnnotation(ViewParse.class);
                    }
                    if (viewParse != null) {
                        String viewClassName = getViewParseValue(viewParse);
                        genClassNameMap.put(curElementName, viewClassName);
                        String layoutParamInit = viewParse.layoutParamInit();
                        layoutParamInitMap.put(viewClassName, layoutParamInit);
                        CallOnInflateConfig callOnInflateConfig = viewParse.callOnFinishInflate();
                        if (callOnInflateConfig != CallOnInflateConfig.EXTENDS) {
                            callOnFinishInflateMap.put(viewClassName, callOnInflateConfig == CallOnInflateConfig.YES);
                        }
                        String compatViewClassName = getViewParseCompatOf(viewParse);
                        if (!VOID_CLASS_NAME.equals(compatViewClassName)) {
                            CompatViewInfoModel compatViewInfoModel = new CompatViewInfoModel();
                            compatViewInfoModel.setCompatCondition(viewParse.compatCondition());
                            compatViewInfoModel.setCompatViewClassName(compatViewClassName);
                            compatViewInfoModel.setViewClassName(viewClassName);
                            compatClassMap.put(compatViewClassName, compatViewInfoModel);
                        }
                    }

                    parentElementName = element.getSuperclass().toString();
                    if (Constants.OBJ_CLASS_NAME.equals(parentElementName)) {
                        //标记当前为gen类
                        parentClassMap.put(curElementName, null);
                    } else {
                        parentClassMap.put(curElementName, parentElementName);
                    }
                }
            }
        }
    }

    private String getViewParseCallOnFinishInflateValue(ViewParse viewParse) {
        try {
            viewParse.callOnFinishInflate();
        } catch (MirroredTypeException mte) {
            return mTypes.asElement(mte.getTypeMirror()).toString();
        }
        return null;
    }

    private String getViewParseValue(ViewParse viewParse) {
        try {
            viewParse.value();
        } catch (MirroredTypeException mte) {
            return mTypes.asElement(mte.getTypeMirror()).toString();
        }
        return null;
    }

    private String getViewParseCompatOf(ViewParse viewParse) {
        try {
            viewParse.compatOf();
        } catch (MirroredTypeException mte) {
            return mTypes.asElement(mte.getTypeMirror()).toString();
        }
        return null;
    }

    void println(String log) {
        System.out.println(log);
    }

    private void visit(JCTree jcTree, TypeElement curElement, TypeElement belongElement) {
        curViewGenClassModel = new ViewGenClassModel();
        curScanElement = curElement;
        curScanIsInterface = curElement.getKind().isInterface();
        if (curScanIsInterface) {
            interfaceMethodInfoMap.put(curElement.getQualifiedName().toString(), curViewGenClassModel);
        } else {
            viewGenClassModelMap.put(curElement.getQualifiedName().toString(), curViewGenClassModel);
        }
        TreePath treePath = trees.getPath(curElement);
        HashMap<String, String> importPackageMap = new HashMap<>();
        List<?> importTrees = treePath.getCompilationUnit().getImports();
        for (int i = 0; i < importTrees.size(); i++) {
            String importClassOrigStr = importTrees.get(i).toString();
            if (importClassOrigStr.startsWith("import")) {
                if (importClassOrigStr.startsWith("import static")) {
                    messager.printMessage(Diagnostic.Kind.ERROR, "暂不支持 import static 引用("+curElement.getSimpleName()+")");
                }
                String importClass = importClassOrigStr.substring(7, importClassOrigStr.length() - 1);
                if (!importClass.contains(".")) {
                    importPackageMap.putIfAbsent(importClass, "");
                } else {
                    importPackageMap.putIfAbsent(importClass.substring(0, importClass.lastIndexOf(".")), "");
                }
            }
        }
        String curElementPath = curElement.getQualifiedName().toString();
        int lastIndexOfDot = curElementPath.lastIndexOf(".");
        if (lastIndexOfDot > 0) {
            curElementPath = curElementPath.substring(0, lastIndexOfDot);
            importPackageMap.putIfAbsent(curElementPath, "");
        }
        curViewGenClassModel.setImportPackageMap(importPackageMap);
        jcTree.accept(this);
        if (curScanIsInterface) {
            String belongElementName = belongElement.getQualifiedName().toString();
            ViewGenClassModel genClassModel = viewGenClassModelMap.get(belongElementName);
            //把接口的信息放到所属类中
            if (genClassModel != null) {
                //暂不支持接口对父类接口方法override
                // TODO: support Interface override
                genClassModel.getFuncInfoModelHashMap().putAll(curViewGenClassModel.getFuncInfoModelHashMap());
                genClassModel.getOnEndFuncInfoModelMap().putAll(curViewGenClassModel.getOnEndFuncInfoModelMap());
                genClassModel.getImportPackageMap().putAll(curViewGenClassModel.getImportPackageMap());
            }
        }
        curViewGenClassModel = null;
    }

    @Override
    public void visitMethodDef(JCTree.JCMethodDecl jcMethodDecl) {
        AttrFuncInfoModel attrFuncInfoModel = attrAnnotationScanner.getAttrFuncInfoModel(jcMethodDecl);
        if (attrFuncInfoModel != null) {
            if (attrFuncInfoModel.getType() == AttrFuncInfoModel.ATTR_TYPE) {
                String attrName = getRealAttrName(attrFuncInfoModel.getAttrName());
                curViewGenClassModel.getFuncInfoModelHashMap().put(attrName, attrFuncInfoModel);
            } else if (attrFuncInfoModel.getType() == AttrFuncInfoModel.OVERRIDE_TYPE) {
                curViewGenClassModel.getOverrideFuncInfoModelList().add(attrFuncInfoModel);
            } else {
                curViewGenClassModel.getOnEndFuncInfoModelMap().put(attrFuncInfoModel.getFuncSignInfo(), attrFuncInfoModel);
            }
        }
    }


    /**
     * 注解获取
     */
    private static class AttrAnnotationScanner extends TreeScanner {

        private static final String OVERRIDE_ANNOTATION_CLASS_NAME = Override.class.getCanonicalName();

        private Map<String, LocalVarInfoModel> localVarInfoModelMap;
        String attrValue = null;
        private JCTree.JCMethodDecl jcMethodDecl;

        private AttrFuncInfoModel attrFuncInfoModel = null;

        public void setLocalVarInfoModelMap(Map<String, LocalVarInfoModel> localVarInfoModelMap) {
            this.localVarInfoModelMap = localVarInfoModelMap;
        }

        public AttrFuncInfoModel getAttrFuncInfoModel(JCTree.JCMethodDecl jcMethodDecl) {
            if (jcMethodDecl.body == null) {
                return null;
            }
            attrFuncInfoModel = null;
            this.jcMethodDecl = jcMethodDecl;
            jcMethodDecl.accept(this);
            this.jcMethodDecl = null;
            return attrFuncInfoModel;
        }

        @Override
        public void visitAnnotation(JCTree.JCAnnotation jcAnnotation) {
            if (jcMethodDecl.params.isEmpty()) {
                return;
            }
            String methodAnnotationName = jcAnnotation.annotationType.type.toString();
            String funcName = jcMethodDecl.name.toString();

            String bodyContent = jcMethodDecl.body.toString();
            //合并后再设置共享变量
            /*for(LocalVarInfoModel varInfoModel : localVarInfoModelMap.values()){
                bodyContent = bodyContent.replace(varInfoModel.getChangeStr(), varInfoModel.getReplaceStr());
            }*/

            if (Constants.ATTR_ANNOTATION_CLASS_NAME.equals(methodAnnotationName)
                    || OVERRIDE_ANNOTATION_CLASS_NAME.equals(methodAnnotationName)) {
                JCTree.JCVariableDecl viewParamVar = jcMethodDecl.params.get(0);
                String viewParamName = viewParamVar.getName().toString();
                String viewParamType = viewParamVar.vartype.type.toString();

                String valueParamName = null;
                String valueParamType = null;
                if (jcMethodDecl.params.size() > 1) {
                    JCTree.JCVariableDecl valueParamVar = jcMethodDecl.params.get(1);
                    valueParamName = valueParamVar.getName().toString();
                    valueParamType = valueParamVar.vartype.type.toString();
                }

                bodyContent = replaceViewParamNameByTemp(bodyContent, viewParamName);

                attrFuncInfoModel = new AttrFuncInfoModel();
                if (valueParamName != null) {
                    if (Constants.QXML_VALUE_INFO_CLASS_NAME.equals(valueParamType)) {
                        attrFuncInfoModel.setFuncBodyContent(replaceValueInfoParamNameByTemp(bodyContent, valueParamName));
                    } else {
                        attrFuncInfoModel.setFuncBodyContent(replaceBaseTypeParamNameByTemp(bodyContent, valueParamName));
                    }
                } else {
                    attrFuncInfoModel.setFuncBodyContent(bodyContent);
                }
                if (Constants.ATTR_ANNOTATION_CLASS_NAME.equals(methodAnnotationName)) {
                    String attrValue = ((JCTree.JCAssign)jcAnnotation.args.get(0)).rhs.toString();
                    String requiredCondition = "";
                    if (jcAnnotation.args.size() > 1) {
                        requiredCondition = ((JCTree.JCAssign)jcAnnotation.args.get(1)).rhs.toString();
                        if (requiredCondition.startsWith("\"")) {
                            requiredCondition = requiredCondition.substring(1, requiredCondition.length() - 1);
                        }
                    }

                    attrFuncInfoModel.setViewParamType(viewParamType);
                    attrFuncInfoModel.setViewParamName(viewParamName);
                    attrFuncInfoModel.setValueParamType(valueParamType);
                    attrFuncInfoModel.setValueParamName(valueParamName);
                    attrFuncInfoModel.setAttrName(attrValue);
                    attrFuncInfoModel.setFuncName(funcName);
                    attrFuncInfoModel.setRequiredCondition(requiredCondition);
                    attrFuncInfoModel.setType(AttrFuncInfoModel.ATTR_TYPE);
                } else {
                    attrFuncInfoModel.setViewParamType(viewParamType);
                    attrFuncInfoModel.setViewParamName(viewParamName);
                    attrFuncInfoModel.setValueParamType(valueParamType);
                    attrFuncInfoModel.setValueParamName(valueParamName);
                    attrFuncInfoModel.setAttrName(attrValue);
                    attrFuncInfoModel.setFuncName(funcName);
                    attrFuncInfoModel.setType(AttrFuncInfoModel.OVERRIDE_TYPE);
                }
                return;
            } else if (Constants.ON_END_ANNOTATION_CLASS_NAME.equals(methodAnnotationName)) {
                String arg = null;
                boolean afterAdd = false;
                String requiredCondition = "";

                for (int i = 0; i < jcAnnotation.args.size(); i++) {
                    JCTree.JCAssign assign = (JCTree.JCAssign)jcAnnotation.args.get(i);
                    String what = assign.lhs.toString();
                    if ("value".equals(what)) {
                        arg = assign.rhs.toString();
                        if (arg.startsWith("{")) {
                            arg = arg.substring(1, arg.length() - 1);
                        }
                    } else if ("afterAdd".equals(what)) {
                        afterAdd = assign.rhs.toString().equalsIgnoreCase("true");
                    } else if ("requiredCondition".equals(what)) {
                        requiredCondition = assign.rhs.toString();
                        if (requiredCondition.length() > 2) {
                            requiredCondition = requiredCondition.substring(1, requiredCondition.length() - 1);
                        }
                    }
                }
                /*if (jcAnnotation.args.size() > 0) {
                    arg = ((JCTree.JCAssign)jcAnnotation.args.get(0)).rhs.toString();
                    if (arg.startsWith("{")) {
                        arg = arg.substring(1, arg.length() - 1);
                    }
                }*/
                JCTree.JCVariableDecl viewParamVar = jcMethodDecl.params.get(0);
                String viewParamName = viewParamVar.getName().toString();
                String viewParamType = viewParamVar.vartype.type.toString();

                attrFuncInfoModel = new AttrFuncInfoModel();
                attrFuncInfoModel.setViewParamType(viewParamType);
                attrFuncInfoModel.setViewParamName(viewParamName);
                attrFuncInfoModel.setAttrName(attrValue);
                attrFuncInfoModel.setFuncName(funcName);
                attrFuncInfoModel.setFuncBodyContent(replaceViewParamNameByTemp(bodyContent, viewParamName));
                attrFuncInfoModel.setType(AttrFuncInfoModel.ON_END_TYPE);
                attrFuncInfoModel.setAfterAdd(afterAdd);
                attrFuncInfoModel.setRequiredCondition(requiredCondition);
                if (arg != null && arg.length() > 2) {//忽略 "", {}
                    List<String> conditions = new ArrayList<>();
                    String[] onEndConditions = arg.split(",");
                    for (String onEndCondition : onEndConditions) {
                        String attrName = getRealAttrName(onEndCondition.trim());
                        conditions.add(attrName);
                    }
                    attrFuncInfoModel.setOnEndCondition(conditions);
                }
                return;
            }
            super.visitAnnotation(jcAnnotation);
        }

        private static final String REPLACE_1 = " "+Constants.VIEW_PARAM_NAME_TEMP +" ";
        private static final String REPLACE_2 = " "+Constants.VIEW_PARAM_NAME_TEMP +".";
        private static final String REPLACE_3 = " "+Constants.VIEW_PARAM_NAME_TEMP +",";
        private static final String REPLACE_4 = " "+Constants.VIEW_PARAM_NAME_TEMP +")";
        private static final String REPLACE_5 = " "+Constants.VIEW_PARAM_NAME_TEMP +";";
        private static final String REPLACE_6 = "("+Constants.VIEW_PARAM_NAME_TEMP +" ";
        private static final String REPLACE_7 = "("+Constants.VIEW_PARAM_NAME_TEMP +")";
        private static final String REPLACE_8 = "("+Constants.VIEW_PARAM_NAME_TEMP +",";
        private static final String REPLACE_9 = "("+Constants.VIEW_PARAM_NAME_TEMP +".";
        private static final String REPLACE_10 = ")"+Constants.VIEW_PARAM_NAME_TEMP +" ";
        private static final String REPLACE_11 = ")"+Constants.VIEW_PARAM_NAME_TEMP +")";
        private static final String REPLACE_12 = ")"+Constants.VIEW_PARAM_NAME_TEMP +".";
        private static final String REPLACE_13 = ")"+Constants.VIEW_PARAM_NAME_TEMP +",";
        private static final String REPLACE_14 = ")"+Constants.VIEW_PARAM_NAME_TEMP +";";
        private static final String REPLACE_15 = "["+Constants.VIEW_PARAM_NAME_TEMP +".";

        private String replaceViewParamNameByTemp(String funcBody, String viewParamName) {
            return funcBody
                    .replace(" "+viewParamName+" ", REPLACE_1)
                    .replace(" "+viewParamName+".", REPLACE_2)
                    .replace(" "+viewParamName+",", REPLACE_3)
                    .replace(" "+viewParamName+")", REPLACE_4)
                    .replace(" "+viewParamName+";", REPLACE_5)
                    .replace("("+viewParamName+" ", REPLACE_6)
                    .replace("("+viewParamName+")", REPLACE_7)
                    .replace("("+viewParamName+",", REPLACE_8)
                    .replace("("+viewParamName+".", REPLACE_9)
                    .replace(")"+viewParamName+" ", REPLACE_10)
                    .replace(")"+viewParamName+")", REPLACE_11)
                    .replace(")"+viewParamName+".", REPLACE_12)
                    .replace(")"+viewParamName+",", REPLACE_13)
                    .replace(")"+viewParamName+";", REPLACE_14)
                    .replace("["+viewParamName+".", REPLACE_15);
        }

        private static final String VALUE_INFO_REPLACE_1 = " "+Constants.VALUE_INFO_PARAM_NAME_TEMP +" ";
        private static final String VALUE_INFO_REPLACE_2 = " "+Constants.VALUE_INFO_PARAM_NAME_TEMP +".";
        private static final String VALUE_INFO_REPLACE_3 = " "+Constants.VALUE_INFO_PARAM_NAME_TEMP +",";
        private static final String VALUE_INFO_REPLACE_4 = " "+Constants.VALUE_INFO_PARAM_NAME_TEMP +")";
        private static final String VALUE_INFO_REPLACE_5 = " "+Constants.VALUE_INFO_PARAM_NAME_TEMP +";";
        private static final String VALUE_INFO_REPLACE_6 = "("+Constants.VALUE_INFO_PARAM_NAME_TEMP +" ";
        private static final String VALUE_INFO_REPLACE_7 = "("+Constants.VALUE_INFO_PARAM_NAME_TEMP +")";
        private static final String VALUE_INFO_REPLACE_8 = "("+Constants.VALUE_INFO_PARAM_NAME_TEMP +",";
        private static final String VALUE_INFO_REPLACE_9 = "("+Constants.VALUE_INFO_PARAM_NAME_TEMP +".";
        private static final String VALUE_INFO_REPLACE_10 = ")"+Constants.VALUE_INFO_PARAM_NAME_TEMP +" ";
        private static final String VALUE_INFO_REPLACE_11 = ")"+Constants.VALUE_INFO_PARAM_NAME_TEMP +")";
        private static final String VALUE_INFO_REPLACE_12 = ")"+Constants.VALUE_INFO_PARAM_NAME_TEMP +".";
        private static final String VALUE_INFO_REPLACE_13 = ")"+Constants.VALUE_INFO_PARAM_NAME_TEMP +",";
        private static final String VALUE_INFO_REPLACE_14 = ")"+Constants.VALUE_INFO_PARAM_NAME_TEMP +";";

        private String replaceValueInfoParamNameByTemp(String funcBody, String valueInfoParamName) {
            return funcBody
                    .replace(" "+valueInfoParamName+" ", VALUE_INFO_REPLACE_1)
                    .replace(" "+valueInfoParamName+".", VALUE_INFO_REPLACE_2)
                    .replace(" "+valueInfoParamName+",", VALUE_INFO_REPLACE_3)
                    .replace(" "+valueInfoParamName+")", VALUE_INFO_REPLACE_4)
                    .replace(" "+valueInfoParamName+";", VALUE_INFO_REPLACE_5)
                    .replace("("+valueInfoParamName+" ", VALUE_INFO_REPLACE_6)
                    .replace("("+valueInfoParamName+")", VALUE_INFO_REPLACE_7)
                    .replace("("+valueInfoParamName+",", VALUE_INFO_REPLACE_8)
                    .replace("("+valueInfoParamName+".", VALUE_INFO_REPLACE_9)
                    .replace(")"+valueInfoParamName+" ", VALUE_INFO_REPLACE_10)
                    .replace(")"+valueInfoParamName+")", VALUE_INFO_REPLACE_11)
                    .replace(")"+valueInfoParamName+".", VALUE_INFO_REPLACE_12)
                    .replace(")"+valueInfoParamName+",", VALUE_INFO_REPLACE_13)
                    .replace(")"+valueInfoParamName+";", VALUE_INFO_REPLACE_14);
        }

        private static final String BASE_TYPE_REPLACE_1 = " "+Constants.BASE_TYPE_PARAM_NAME_TEMP +" ";
        private static final String BASE_TYPE_REPLACE_2 = " "+Constants.BASE_TYPE_PARAM_NAME_TEMP +".";
        private static final String BASE_TYPE_REPLACE_3 = " "+Constants.BASE_TYPE_PARAM_NAME_TEMP +",";
        private static final String BASE_TYPE_REPLACE_4 = " "+Constants.BASE_TYPE_PARAM_NAME_TEMP +")";
        private static final String BASE_TYPE_REPLACE_5 = " "+Constants.BASE_TYPE_PARAM_NAME_TEMP +";";
        private static final String BASE_TYPE_REPLACE_6 = "("+Constants.BASE_TYPE_PARAM_NAME_TEMP +" ";
        private static final String BASE_TYPE_REPLACE_7 = "("+Constants.BASE_TYPE_PARAM_NAME_TEMP +")";
        private static final String BASE_TYPE_REPLACE_8 = "("+Constants.BASE_TYPE_PARAM_NAME_TEMP +",";
        private static final String BASE_TYPE_REPLACE_9 = "("+Constants.BASE_TYPE_PARAM_NAME_TEMP +".";
        private static final String BASE_TYPE_REPLACE_10 = ")"+Constants.BASE_TYPE_PARAM_NAME_TEMP +" ";
        private static final String BASE_TYPE_REPLACE_11 = ")"+Constants.BASE_TYPE_PARAM_NAME_TEMP +")";
        private static final String BASE_TYPE_REPLACE_12 = ")"+Constants.BASE_TYPE_PARAM_NAME_TEMP +".";
        private static final String BASE_TYPE_REPLACE_13 = ")"+Constants.BASE_TYPE_PARAM_NAME_TEMP +",";
        private static final String BASE_TYPE_REPLACE_14 = ")"+Constants.BASE_TYPE_PARAM_NAME_TEMP +";";
        private static final String BASE_TYPE_REPLACE_15 = "["+Constants.BASE_TYPE_PARAM_NAME_TEMP +".";
        private static final String BASE_TYPE_REPLACE_16 = "["+Constants.BASE_TYPE_PARAM_NAME_TEMP +"]";

        private String replaceBaseTypeParamNameByTemp(String funcBody, String baseTypeParamName) {
            return funcBody
                    .replace(" "+baseTypeParamName+" ", BASE_TYPE_REPLACE_1)
                    .replace(" "+baseTypeParamName+".", BASE_TYPE_REPLACE_2)
                    .replace(" "+baseTypeParamName+",", BASE_TYPE_REPLACE_3)
                    .replace(" "+baseTypeParamName+")", BASE_TYPE_REPLACE_4)
                    .replace(" "+baseTypeParamName+";", BASE_TYPE_REPLACE_5)
                    .replace("("+baseTypeParamName+" ", BASE_TYPE_REPLACE_6)
                    .replace("("+baseTypeParamName+")", BASE_TYPE_REPLACE_7)
                    .replace("("+baseTypeParamName+",", BASE_TYPE_REPLACE_8)
                    .replace("("+baseTypeParamName+".", BASE_TYPE_REPLACE_9)
                    .replace(")"+baseTypeParamName+" ", BASE_TYPE_REPLACE_10)
                    .replace(")"+baseTypeParamName+")", BASE_TYPE_REPLACE_11)
                    .replace(")"+baseTypeParamName+".", BASE_TYPE_REPLACE_12)
                    .replace(")"+baseTypeParamName+",", BASE_TYPE_REPLACE_13)
                    .replace(")"+baseTypeParamName+";", BASE_TYPE_REPLACE_14)
                    .replace("["+baseTypeParamName+".", BASE_TYPE_REPLACE_15)
                    .replace("["+baseTypeParamName+"]", BASE_TYPE_REPLACE_16);
        }
    }

    private static String getRealAttrName(String originAttrName) {
        String[] split = originAttrName.split(".attr.");
        String attrName = split[split.length-1];
        if (originAttrName.startsWith("com.qxml.AndroidRS") || originAttrName.startsWith("AndroidRS")) {
            attrName = "android:"+attrName;
        }
        return attrName;
    }

}
