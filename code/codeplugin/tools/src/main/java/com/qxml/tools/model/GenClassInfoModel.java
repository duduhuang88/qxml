package com.qxml.tools.model;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenClassInfoModel implements Comparable<GenClassInfoModel> {

    private int versionCode;
    private List<String> viewParseList;
    private List<String> viewReplaceList;
    private Map<String, ViewGenClassModel> viewGenClassModelMap;
    private Map<String, ViewGenClassModel> interfaceModelMap;
    private Map<String, String> genClassNameMap;
    //viewGen类不在这里
    private Map<String, String> parentClassMap;
    private Map<String, LocalVarInfoModel> localVarMap;
    private Map<String, CompatViewInfoModel> compatViewInfoModelMap;
    //key: View Class Name , value: initBloc
    private Map<String, String> layoutParamInitMap;
    //key: View Class Name , value: callOnFinishInflate
    private Map<String, Boolean> callOnFinishInflateMap;

    public Map<String, Boolean> getCallOnFinishInflateMap() {
        return callOnFinishInflateMap;
    }

    public void setCallOnFinishInflateMap(Map<String, Boolean> callOnFinishInflateMap) {
        this.callOnFinishInflateMap = callOnFinishInflateMap;
    }

    private String sign;
    //用于本地三方ViewGen验证
    private String validCode;

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Map<String, String> getLayoutParamInitMap() {
        return layoutParamInitMap;
    }

    public void setLayoutParamInitMap(Map<String, String> layoutParamInitMap) {
        this.layoutParamInitMap = layoutParamInitMap;
    }

    public Map<String, CompatViewInfoModel> getCompatViewInfoModelMap() {
        return compatViewInfoModelMap;
    }

    public void setCompatViewInfoModelMap(Map<String, CompatViewInfoModel> compatViewInfoModelMap) {
        this.compatViewInfoModelMap = compatViewInfoModelMap;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public List<String> getViewParseList() {
        return viewParseList;
    }

    public void setViewParseList(List<String> viewParseList) {
        this.viewParseList = viewParseList;
    }

    public List<String> getViewReplaceList() {
        return viewReplaceList;
    }

    public void setViewReplaceList(List<String> viewReplaceList) {
        this.viewReplaceList = viewReplaceList;
    }

    public Map<String, ViewGenClassModel> getViewGenClassModelMap() {
        return viewGenClassModelMap;
    }

    public void setViewGenClassModelMap(Map<String, ViewGenClassModel> viewGenClassModelMap) {
        this.viewGenClassModelMap = viewGenClassModelMap;
    }

    public Map<String, String> getGenClassNameMap() {
        return genClassNameMap;
    }

    public void setGenClassNameMap(Map<String, String> genClassNameMap) {
        this.genClassNameMap = genClassNameMap;
    }

    public Map<String, ViewGenClassModel> getInterfaceModelMap() {
        return interfaceModelMap;
    }

    public void setInterfaceModelMap(Map<String, ViewGenClassModel> interfaceModelMap) {
        this.interfaceModelMap = interfaceModelMap;
    }

    public Map<String, String> getParentClassMap() {
        return parentClassMap;
    }

    public void setParentClassMap(Map<String, String> parentClassMap) {
        this.parentClassMap = parentClassMap;
    }

    public Map<String, LocalVarInfoModel> getLocalVarMap() {
        return localVarMap;
    }

    public void setLocalVarMap(Map<String, LocalVarInfoModel> localVarMap) {
        this.localVarMap = localVarMap;
    }

    @Override
    public int compareTo(@NotNull GenClassInfoModel genClassInfoModel) {
        return versionCode - genClassInfoModel.versionCode;
    }
}
