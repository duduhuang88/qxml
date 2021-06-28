package com.qxml.transform.generate.model

import com.qxml.tools.model.AttrFuncInfoModel
import com.qxml.transform.generate.ViewGenResultInfo

data class GenerateClassInfo(val usedGenInfoMap: HashMap<String, HashMap<String, AttrFuncInfoModel>>       //已经用到的gen信息
                             , val usedOnEndInfoMap: HashMap<String, HashMap<String, AttrFuncInfoModel>>   //已经用到的onEnd信息
                             , val usedStyleInfoMap: HashMap<String, HashMap<String, StyleInfo>>           //已经用到的style
                             , val invalidGenInfoMap: HashMap<String, HashMap<String, Int>>                //没有对应实现的attr属性
                             , val relativeIncludeLayoutMap: HashMap<String, String>                       //关联的include layout
                             , val usedReferenceRMap: HashMap<String, String>                              //使用到的R
                             , val usedImportPackageMap: HashMap<String, String>                           //使用到的import
                             , val genReportInfo: HashMap<String, ViewGenResultInfo>                       //生成报告
                             , val verifyKey: String  //验证修改的key，由layout类型+最后修改时间构成: eg:1615279996650_v24_1615539346106
                             , var methodContent: String) {
}