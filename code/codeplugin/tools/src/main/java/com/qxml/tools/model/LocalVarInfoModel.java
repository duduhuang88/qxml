package com.qxml.tools.model;

public class LocalVarInfoModel {

    public LocalVarInfoModel(String initBlock, String resetBlock, String parentName, String varType, String varName) {
        this.initBlock = initBlock;
        this.resetBlock = resetBlock;
        this.parentName = parentName;
        this.varType = varType;
        this.varName = varName;
        this.changeStr = parentName+".";
        this.replaceStr = parentName+"_";
    }

    private String initBlock;
    private String resetBlock;
    private String parentName;
    private String changeStr;
    private String replaceStr;
    private String varType;
    private String varName;

    public String getResetBlock() {
        return resetBlock;
    }

    public void setResetBlock(String resetBlock) {
        this.resetBlock = resetBlock;
    }

    public String getChangeStr() {
        return changeStr;
    }

    public void setChangeStr(String changeStr) {
        this.changeStr = changeStr;
    }

    public String getReplaceStr() {
        return replaceStr;
    }

    public void setReplaceStr(String replaceStr) {
        this.replaceStr = replaceStr;
    }

    public String getInitBlock() {
        return initBlock;
    }

    public void setInitBlock(String initBlock) {
        this.initBlock = initBlock;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getVarType() {
        return varType;
    }

    public void setVarType(String varType) {
        this.varType = varType;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
    }
}
