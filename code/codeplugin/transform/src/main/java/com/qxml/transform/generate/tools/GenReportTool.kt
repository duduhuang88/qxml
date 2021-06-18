package com.qxml.transform.generate.tools

import com.qxml.tools.LayoutTypeNameCorrect
import com.qxml.transform.generate.GenResult
import com.qxml.transform.generate.ViewGenResultInfo
import java.io.File
import java.lang.StringBuilder

object GenReportTool {

    private const val TABLE_HEAD = "<style>\n" +
            "    .table11_6 table {\n" +
            "        width:100%;\n" +
            "        margin:15px 0;\n" +
            "        border:0;\n" +
            "    }\n" +
            "    .table11_6 th {\n" +
            "        background-color:#96C7ED;\n" +
            "        color:#000000\n" +
            "    }\n" +
            "    .table11_6,.table11_6 th,.table11_6 td {\n" +
            "        font-size:0.95em;\n" +
            "        text-align:center;\n" +
            "        padding:4px;\n" +
            "        border-collapse:collapse;\n" +
            "    }\n" +
            "    .table11_6 th,.table11_6 td {\n" +
            "        border: 1px solid #73b4e7;\n" +
            "        border-width:1px 0 1px 0;\n" +
            "        border:2px inset #ffffff;\n" +
            "    }\n" +
            "    .table11_6 tr {\n" +
            "        border: 1px solid #ffffff;\n" +
            "    }\n" +
            "</style>\n" +
            "<table class=table11_6>\n" +
            "<tr>\n" +
            "        <th>LayoutName</th><th>LayoutType</th><th>Info</th>\n" +
            "    </tr>"


    @JvmStatic
    fun genReport(reportFile: File, genResultMap: Map<String, HashMap<String, ViewGenResultInfo>>) {
        if (!reportFile.parentFile.exists()) {
            reportFile.parentFile.mkdirs()
        }
        if (!reportFile.exists()) {
            reportFile.createNewFile()
        }
        val stringBuilder = StringBuilder()
        stringBuilder.append(TABLE_HEAD).append("\n")
        genResultMap.forEach { (layoutName, hashMap) ->
            var firstLine = true
            hashMap.forEach { (layoutType, viewGenResultInfo) ->
                val firstClo = if (firstLine) {
                    firstLine = false
                    "<td rowspan=\"${hashMap.size}\">${layoutName}</td>"
                } else ""
                val bgColor = if (viewGenResultInfo.result == GenResult.SUCCESS) "#ffffff" else "#fe2047"
                stringBuilder.append("<tr >\n" +
                        "        ${firstClo}<td bgcolor=\"${bgColor}\" >${LayoutTypeNameCorrect.toDisplayText(layoutType)}</td><td>${viewGenResultInfo.info}</td>\n" +
                        "    </tr>\n")
            }
        }
        stringBuilder.append("</table>")
        reportFile.writeText(stringBuilder.toString())
    }

}