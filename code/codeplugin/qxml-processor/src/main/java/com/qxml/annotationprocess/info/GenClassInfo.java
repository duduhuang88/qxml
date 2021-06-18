package com.qxml.annotationprocess.info;

public class GenClassInfo {
    public String className;
    public String viewParamTypeStr;
    public boolean isInterface;

    public GenClassInfo(String className, String viewParamTypeStr, boolean isInterface) {
        this.viewParamTypeStr = viewParamTypeStr;
        this.className = className;
        this.isInterface = isInterface;
    }

    @Override
    public String toString() {
        return "GenClassInfo{" +
                "className='" + className + '\'' +
                ", viewParamTypeStr='" + viewParamTypeStr + '\'' +
                ", isInterface=" + isInterface +
                '}';
    }
}
