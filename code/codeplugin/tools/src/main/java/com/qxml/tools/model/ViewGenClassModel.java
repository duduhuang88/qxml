package com.qxml.tools.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ViewGenClassModel {

    private LinkedHashMap<String, AttrFuncInfoModel> funcInfoModelHashMap = new LinkedHashMap<>();
    private List<AttrFuncInfoModel> overrideFuncInfoModelList = new ArrayList<>();
    private Map<String, AttrFuncInfoModel> onEndFuncInfoModelMap = new LinkedHashMap<>();
    private Map<String, String> importPackageMap = new HashMap<>();

    public Map<String, String> getImportPackageMap() {
        return importPackageMap;
    }

    public void setImportPackageMap(Map<String, String> importPackageMap) {
        this.importPackageMap = importPackageMap;
    }

    public LinkedHashMap<String, AttrFuncInfoModel> getFuncInfoModelHashMap() {
        return funcInfoModelHashMap;
    }

    public void setFuncInfoModelHashMap(LinkedHashMap<String, AttrFuncInfoModel> funcInfoModelHashMap) {
        this.funcInfoModelHashMap = funcInfoModelHashMap;
    }

    public List<AttrFuncInfoModel> getOverrideFuncInfoModelList() {
        return overrideFuncInfoModelList;
    }

    public void setOverrideFuncInfoModelList(List<AttrFuncInfoModel> overrideFuncInfoModelList) {
        this.overrideFuncInfoModelList = overrideFuncInfoModelList;
    }

    public Map<String, AttrFuncInfoModel> getOnEndFuncInfoModelMap() {
        return onEndFuncInfoModelMap;
    }

    public void setOnEndFuncInfoModelMap(Map<String, AttrFuncInfoModel> onEndFuncInfoModelMap) {
        this.onEndFuncInfoModelMap = onEndFuncInfoModelMap;
    }

    @Override
    public String toString() {
        return "ViewGenClassModel{" +
                "funcInfoModelHashMap=" + funcInfoModelHashMap + '\'' +
                ", onEndFuncInfoModelMap=" + onEndFuncInfoModelMap + '\'' +
                ", overrideFuncInfoModel=" + overrideFuncInfoModelList +
                '}';
    }
}
