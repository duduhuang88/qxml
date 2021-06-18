package com.qxml.transform.generate

import com.google.common.io.Files
import com.google.gson.Gson
import com.qxml.QxmlConfigExtension
import com.qxml.constant.Constants
import com.qxml.tools.GenClassNameTool
import com.qxml.tools.LayoutTypeNameCorrect
import com.qxml.tools.log.LogUtil
import com.qxml.transform.pool.PoolManager
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.MethodSpec
import com.squareup.javapoet.TypeName
import javassist.CtMethod
import javassist.CtNewMethod
import java.lang.StringBuilder
import javax.lang.model.element.Modifier

private object MethodCreateClassHolder {
    val viewClass = ClassName.bestGuess(Constants.ANDROID_VIEW_CLASS_NAME)!!
    val contextClass = ClassName.bestGuess(Constants.ANDROID_CONTEXT_CLASS_NAME)!!
    val inflateClass = ClassName.bestGuess(Constants.ANDROID_INFLATER_CLASS_NAME)!!
    val viewGroupClass = ClassName.bestGuess(Constants.ANDROID_VIEW_GROUP_CLASS_NAME)!!
}

interface CreateClassFile {

    /**
     * 生成class文件并缓存
     */
    fun createClassFile(layoutName: String, classGenCacheInfo: ClassGenCacheInfo, packageName: String, qxmlExtension: QxmlConfigExtension, gson: Gson) {
        if (!classGenCacheInfo.cacheValid) {
            val className = GenClassNameTool.genClassName(layoutName)
            val ctClass = PoolManager.pool.makeClass(className)
            val methodBuilder = MethodSpec.methodBuilder(Constants.GENERATE_METHOD_NAME)
            val stringBuilder = StringBuilder()
            stringBuilder.append("generate success type: \n")
            classGenCacheInfo.successLayoutTypeGenInfoList.forEach {
                stringBuilder.append(LayoutTypeNameCorrect.toDisplayText(it.layoutType)).append(" ")
            }
            stringBuilder.append("\n")
            if (classGenCacheInfo.failedLayoutTypeGenInfoList.isNotEmpty()) {
                stringBuilder.append("generate failed type: \n")
                classGenCacheInfo.failedLayoutTypeGenInfoList.forEach {
                    stringBuilder.append(LayoutTypeNameCorrect.toDisplayText(it.layoutType)).append(" ")
                }
                stringBuilder.append("\n")
            }
            methodBuilder.addJavadoc("generated code of ${layoutName}.xml\n$stringBuilder")
            methodBuilder.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            methodBuilder.returns(MethodCreateClassHolder.viewClass)
            methodBuilder.addParameter(MethodCreateClassHolder.inflateClass, Constants.GEN_PARAM_INFLATE_NAME)
            methodBuilder.addParameter(MethodCreateClassHolder.contextClass, Constants.GEN_PARAM_CONTEXT_NAME)
            methodBuilder.addParameter(MethodCreateClassHolder.viewGroupClass, Constants.GEN_PARAM_VIEW_GROUP_ROOT_NAME)
            methodBuilder.addParameter(TypeName.BOOLEAN, Constants.GEN_PARAM_ATTACH_TO_NAME)

            methodBuilder.addStatement("${Constants.ANDROID_VIEW_GROUP_LAYOUT_PARAM_CLASS_NAME} ${Constants.GEN_FIELD_LAYOUT_PARAMS_ROOT} = null")
            methodBuilder.addStatement("${Constants.ANDROID_VIEW_GROUP_LAYOUT_PARAM_CLASS_NAME} ${Constants.GEN_FIELD_LAYOUT_PARAMS} = null")
            methodBuilder.addStatement("${Constants.QXML_VALUE_INFO_CLASS_NAME} ${Constants.GEN_FIELD_VALUE_INFO_NAME} = new ${Constants.QXML_VALUE_INFO_CLASS_NAME}()")
            if (classGenCacheInfo.layoutTypeAmount != 1 || classGenCacheInfo.generateClassInfo.methodContent.contains(Constants.GEN_FIELD_TYPED_VALUE_NAME)) {
                methodBuilder.addStatement("${Constants.ANDROID_TYPED_VALUE_CLASS_NAME} ${Constants.GEN_FIELD_TYPED_VALUE_NAME} = new ${Constants.ANDROID_TYPED_VALUE_CLASS_NAME}()")
            }
            //methodBuilder.addStatement("${Constants.ANDROID_TYPED_VALUE_CLASS_NAME} ${Constants.GEN_FIELD_TYPED_VALUE_NAME} = new ${Constants.ANDROID_TYPED_VALUE_CLASS_NAME}()")
            methodBuilder.addStatement("${Constants.ANDROID_RESOURCE_CLASS_NAME} ${Constants.GEN_FIELD_RESOURCE} = ${Constants.GEN_PARAM_CONTEXT_NAME}.getResources()")

            if (qxmlExtension.useFactory) {
                methodBuilder.addStatement("${Constants.ANDROID_LAYOUT_INFLATE_FACTORY_CLASS_NAME} ${Constants.GEN_FIELD_FACTORY} = ${Constants.GEN_PARAM_INFLATE_NAME}.getFactory()")
                methodBuilder.addStatement("${Constants.ANDROID_LAYOUT_INFLATE_FACTORY2_CLASS_NAME} ${Constants.GEN_FIELD_FACTORY_2} = ${Constants.GEN_PARAM_INFLATE_NAME}.getFactory2()")
                methodBuilder.addStatement("${Constants.ANDROID_ACTIVITY_CLASS_NAME} ${Constants.GEN_FIELD_CUR_CONTEXT_ACT} = null")
                methodBuilder.beginControlFlow("if (${Constants.GEN_PARAM_CONTEXT_NAME} instanceof ${Constants.ANDROID_ACTIVITY_CLASS_NAME})")
                methodBuilder.addStatement("${Constants.GEN_FIELD_CUR_CONTEXT_ACT} = (${Constants.ANDROID_ACTIVITY_CLASS_NAME}) ${Constants.GEN_PARAM_CONTEXT_NAME}")
                methodBuilder.endControlFlow()
            }

            //layout不止一种type
            if (classGenCacheInfo.layoutTypeAmount != 1) {
                methodBuilder.addStatement("${Constants.GEN_FIELD_RESOURCE}.getValue(${packageName}.R.layout.${layoutName}, ${Constants.GEN_FIELD_TYPED_VALUE_NAME}, true)")
                //默认为layout -> "".hashCode = 0
                val suffixLength = layoutName.length + 5 // '.' + '.xml'
                methodBuilder.addComment("默认为layout")
                methodBuilder.addStatement("int ${Constants.GEN_FIELD_LAYOUT_TYPE_HASHCODE_NAME} = 0")
                methodBuilder.addStatement("CharSequence ${Constants.GEN_FIELD_LAYOUT_TYPE_STRING_NAME} = ${Constants.GEN_FIELD_TYPED_VALUE_NAME}.string")
                methodBuilder.beginControlFlow("if(${Constants.GEN_FIELD_LAYOUT_TYPE_STRING_NAME}.charAt(10) == '-')")
                methodBuilder.addStatement("${Constants.GEN_FIELD_LAYOUT_TYPE_HASHCODE_NAME} = ${Constants.GEN_FIELD_LAYOUT_TYPE_STRING_NAME}.subSequence(11, ${Constants.GEN_FIELD_LAYOUT_TYPE_STRING_NAME}.length() - $suffixLength).toString().hashCode()")

                //methodBuilder.addStatement("android.util.Log.e(\"touch\", \"f layout type info: \"+___layoutTypeHashCode+\" \"+___layoutTypeStr)")

                methodBuilder.endControlFlow()
            }

            //用到了size
            if (classGenCacheInfo.generateFieldInfoMap.allSizeMap.isNotEmpty()) {
                methodBuilder.addStatement("android.util.DisplayMetrics ___displayMetrics = ${Constants.GEN_FIELD_RESOURCE}.getDisplayMetrics()")
                methodBuilder.addStatement("float ${Constants.GEN_FIELD_DENSITY} = ___displayMetrics.density")
                methodBuilder.addStatement("float ${Constants.GEN_FIELD_SCALE_DENSITY} = ___displayMetrics.scaledDensity")
                methodBuilder.addStatement("float ${Constants.GEN_FIELD_X_DPI} = ___displayMetrics.xdpi")
            }

            methodBuilder.addStatement(classGenCacheInfo.generateClassInfo.methodContent)

            //cacheInfoFile.writeText(gson.toJson(classGenInfo))
            val methodContent = methodBuilder.build().toString()
            classGenCacheInfo.generateClassInfo.methodContent = ""

            //LogUtil.pl("make class suc: "+layoutName)
            //LogUtil.pl("make class "+layoutName +" \n"+methodContent)
            //LogUtil.pl("make class "+layoutName +" \n"+classResult.classCacheFile.absolutePath)
            try {
                ctClass.addMethod(CtNewMethod.make(methodContent, ctClass))
            } catch (e: Exception) {
                classGenCacheInfo.generateClassInfo.methodContent = methodContent
                LogUtil.pl("make class err:\n"+layoutName+"\n"+e)
                throw e
            }

            classGenCacheInfo.classCacheFile.also { cacheFile->
                Files.createParentDirs(cacheFile)
                cacheFile.createNewFile()
                cacheFile.writeBytes(ctClass.toBytecode())
            }
            classGenCacheInfo.cacheInfoFile.parentFile.resolve(Constants.QXML_METHOD_CONTENT_CACHE_FILE_NAME).also { cacheFile->
                Files.createParentDirs(cacheFile)
                cacheFile.createNewFile()
                cacheFile.writeText(methodContent)
            }
            classGenCacheInfo.cacheInfoFile.also { cacheInfoFile ->
                Files.createParentDirs(cacheInfoFile)
                cacheInfoFile.createNewFile()
                cacheInfoFile.writeText(gson.toJson(classGenCacheInfo.generateClassInfo))
            }
            ctClass.defrost()
            ctClass.detach()
        }

        //添加到pool
        PoolManager.pool.appendClassPath(classGenCacheInfo.cacheRootDir.absolutePath)
    }

    fun createClassFileWithCache(layoutName: String, classGenCacheInfo: ClassGenCacheInfo) {
        if (classGenCacheInfo.cacheValid) {
            val className = GenClassNameTool.genClassName(layoutName)
            val ctClass = PoolManager.pool.makeClass(className)

            //cacheInfoFile.writeText(gson.toJson(classGenInfo))
            val methodContent = classGenCacheInfo.generateClassInfo.methodContent
            classGenCacheInfo.generateClassInfo.methodContent = ""

            //LogUtil.pl("make class suc: "+layoutName)
            //LogUtil.pl("make class "+layoutName +" \n"+methodContent)
            //LogUtil.pl("make class "+layoutName +" \n"+classResult.classCacheFile.absolutePath)
            try {
                ctClass.addMethod(CtNewMethod.make(methodContent, ctClass))
            } catch (e: Exception) {
                classGenCacheInfo.generateClassInfo.methodContent = methodContent
                LogUtil.pl("make class err:\n"+layoutName+"\n"+e)
                throw e
            }

            classGenCacheInfo.classCacheFile.also { cacheFile->
                Files.createParentDirs(cacheFile)
                cacheFile.createNewFile()
                cacheFile.writeBytes(ctClass.toBytecode())
            }
            ctClass.defrost()
            ctClass.detach()
        }

        //添加到pool
        PoolManager.pool.appendClassPath(classGenCacheInfo.cacheRootDir.absolutePath)
    }

}