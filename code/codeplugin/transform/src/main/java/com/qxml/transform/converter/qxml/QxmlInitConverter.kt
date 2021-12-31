package com.qxml.transform.converter.qxml

import com.google.common.io.Files
import com.qxml.constant.Constants
import com.qxml.tools.log.LogUtil
import com.qxml.transform.pool.PoolManager
import javassist.CtClass
import javassist.bytecode.Descriptor
import java.io.File
import java.lang.StringBuilder

object QxmlInitConverter {

    private const val PARAM_LAYOUT_INFLATER = "\$1"
    private const val PARAM_LAYOUT_ID = "\$2"
    private const val PARAM_ROOT = "\$3"
    private const val PARAM_ATTACH_TO = "\$4"
    private val viewClass by lazy { PoolManager.pool["android.view.View"] }
    private val inflaterClass by lazy { PoolManager.pool["android.view.LayoutInflater"] }
    private val viewGroupClass by lazy { PoolManager.pool["android.view.ViewGroup"] }
    private val layoutIdAndNameList = arrayListOf<LayoutIdAndName>()

    //TODO ASM
    fun convert(ctClass: CtClass, genClassInfoList: List<String>, packageName: String, layoutIdMap: Map<String, Int>, cacheFile: File) {
        layoutIdAndNameList.clear()
        genClassInfoList.forEach {
            layoutIdAndNameList.add(LayoutIdAndName(it, layoutIdMap[it]?:0))
        }
        layoutIdAndNameList.sort()
        val stringBuilder = StringBuilder()
        //stringBuilder.append("android.util.Log.e(\"touch\", \"f layout id \"+$PARAM_LAYOUT_ID+\" \"+$PARAM_ROOT);\n")
        if (layoutIdAndNameList.isNotEmpty()) {
            processId(packageName, layoutIdAndNameList, 0, layoutIdAndNameList.size - 1, stringBuilder, 0)
        }
        stringBuilder.append("\nreturn $PARAM_LAYOUT_INFLATER.inflate($PARAM_LAYOUT_ID, $PARAM_ROOT, $PARAM_ATTACH_TO);")
        ctClass.getMethod("generate", Descriptor.ofMethod(viewClass, arrayOf(inflaterClass, CtClass.intType, viewGroupClass, CtClass.booleanType))).also {
            it.setBody("{ $stringBuilder }")
        }
        if (LogUtil.debug) {
            Files.createParentDirs(cacheFile)
            if (!cacheFile.exists()) {
                cacheFile.createNewFile()
            }
            cacheFile.writeText(stringBuilder.toString().replace(PARAM_LAYOUT_ID, "resId")
                .replace(PARAM_LAYOUT_INFLATER, Constants.GEN_PARAM_INFLATE_NAME).replace(PARAM_ROOT, Constants.GEN_PARAM_VIEW_GROUP_ROOT_NAME)
                .replace(PARAM_ATTACH_TO, Constants.GEN_PARAM_ATTACH_TO_NAME))
        }
    }

    //二分查找
    private fun processId(packageName: String, idNameList: ArrayList<LayoutIdAndName>, startIndex: Int, endIndex: Int, stringBuilder: StringBuilder, loopCount: Int) {
        val diffIndex = endIndex - startIndex
        val middleIndex = (startIndex + endIndex)/2
        when(diffIndex) {
            0 -> {
                val startIdName = idNameList[startIndex]
                stringBuilder.append("if($PARAM_LAYOUT_ID == ${startIdName.id}) { //$packageName.R.layout.${startIdName.name}\n")
                stringBuilder.append(makeReturn(startIdName.name))
                stringBuilder.append("}\n")
            }
            1 -> {
                val startIdName = idNameList[startIndex]
                val endIdName = idNameList[endIndex]

                stringBuilder.append("if($PARAM_LAYOUT_ID == ${startIdName.id}) { //$packageName.R.layout.${startIdName.name}\n")
                stringBuilder.append(makeReturn(startIdName.name))
                stringBuilder.append("} else if ($PARAM_LAYOUT_ID == ${endIdName.id}) { //$packageName.R.layout.${endIdName.name}\n")
                stringBuilder.append(makeReturn(endIdName.name))
                stringBuilder.append("}\n")
            }
            else -> {
                val middleIdName = idNameList[middleIndex]
                stringBuilder.append("if($PARAM_LAYOUT_ID <= ${middleIdName.id}) { //$packageName.R.layout.${middleIdName.name}\n")
                processId(packageName, idNameList, startIndex, middleIndex, stringBuilder, loopCount + 1)
                stringBuilder.append("} else {\n")
                processId(packageName, idNameList, middleIndex + 1, endIndex, stringBuilder, loopCount + 1)
                stringBuilder.append("}\n")
            }
        }
    }

    private fun makeReturn(name: String): String {
        return "return ${Constants.GEN_CLASS_PATH_PREFIX}${name}.generate($PARAM_LAYOUT_INFLATER, ${PARAM_LAYOUT_INFLATER}.getContext(), $PARAM_ROOT, $PARAM_ATTACH_TO);\n"
    }
}

private class LayoutIdAndName(val name: String, val id: Int): Comparable<LayoutIdAndName> {
    override fun compareTo(other: LayoutIdAndName): Int {
        return id - other.id
    }

    override fun toString(): String {
        return "$name:$id"
    }
}