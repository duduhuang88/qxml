package com.qxml.tools.model;

import java.util.HashMap;
import java.util.List;

public class AttrFuncInfoModel implements Cloneable {

    public static final int ATTR_TYPE = 0, OVERRIDE_TYPE = 1, ON_END_TYPE = 2;

    private int type;
    private String viewParamType;
    private String viewParamName;
    private String valueParamType;
    private String valueParamName;
    private String funcName;
    private String funcBodyContent;
    private String attrName;
    private String belongViewName;
    private List<String> onEndCondition;
    private boolean afterAdd;
    private HashMap<String, String> usedLocalVarMap;

    public String cacheSignKey;

    public void generateKey() {
        if (cacheSignKey == null) {
            cacheSignKey = funcBodyContent.hashCode()+"_"+viewParamType.hashCode()+"_"+(valueParamType != null ? valueParamType.hashCode() : 0);
        }
    }

    @Override
    public AttrFuncInfoModel clone() throws CloneNotSupportedException {
        return (AttrFuncInfoModel) super.clone();
    }

    public boolean isChange(AttrFuncInfoModel other) {
        if (other.cacheSignKey == null) {
            other.generateKey();
        }
        if (cacheSignKey == null) {
            generateKey();
        }
        return !cacheSignKey.equals(other.cacheSignKey) || !funcBodyContent.equals(other.funcBodyContent);
    }

    public boolean isAfterAdd() {
        return afterAdd;
    }

    public void setAfterAdd(boolean afterAdd) {
        this.afterAdd = afterAdd;
    }

    public String getCacheSignKey() {
        return cacheSignKey;
    }

    public void setCacheSignKey(String cacheSignKey) {
        this.cacheSignKey = cacheSignKey;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getViewParamType() {
        return viewParamType;
    }

    public void setViewParamType(String viewParamType) {
        this.viewParamType = viewParamType;
    }

    public String getViewParamName() {
        return viewParamName;
    }

    public void setViewParamName(String viewParamName) {
        this.viewParamName = viewParamName;
    }

    public String getValueParamType() {
        return valueParamType;
    }

    public void setValueParamType(String valueParamType) {
        this.valueParamType = valueParamType;
    }

    public String getValueParamName() {
        return valueParamName;
    }

    public void setValueParamName(String valueParamName) {
        this.valueParamName = valueParamName;
    }

    public String getFuncSignInfo() {
        return funcName + "_" + viewParamType;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getFuncBodyContent() {
        return funcBodyContent;
    }

    public void setFuncBodyContent(String funcBodyContent) {
        this.funcBodyContent = funcBodyContent;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getBelongViewName() {
        return belongViewName;
    }

    public void setBelongViewName(String belongViewName) {
        this.belongViewName = belongViewName;
    }

    public List<String> getOnEndCondition() {
        return onEndCondition;
    }

    public void setOnEndCondition(List<String> onEndCondition) {
        this.onEndCondition = onEndCondition;
    }

    public HashMap<String, String> getUsedLocalVarMap() {
        return usedLocalVarMap;
    }

    public void setUsedLocalVarMap(HashMap<String, String> usedLocalVarMap) {
        this.usedLocalVarMap = usedLocalVarMap;
    }

    @Override
    public String toString() {
        return "AttrFuncInfoModel{" +
                "type=" + type +
                ", viewParamType='" + viewParamType + '\'' +
                ", viewParamName='" + viewParamName + '\'' +
                ", valueParamType='" + valueParamType + '\'' +
                ", valueParamName='" + valueParamName + '\'' +
                ", funcName='" + funcName + '\'' +
                ", funcBodyContent='" + funcBodyContent + '\'' +
                ", attrName='" + attrName + '\'' +
                ", usedLocalVarMap='" + usedLocalVarMap + '\'' +
                ", belongViewName='" + belongViewName + '\'' +
                ", onEndCondition='" + onEndCondition + '\'' +
                ", key='" + cacheSignKey + '\'' +
                '}';
    }
}
