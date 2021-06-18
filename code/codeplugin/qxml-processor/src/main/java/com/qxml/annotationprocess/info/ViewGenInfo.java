package com.qxml.annotationprocess.info;

public class ViewGenInfo {

    public String annotationAttrName;
    public long annotationAttrValue;
    public String filedName;
    public String viewType;
    public com.sun.tools.javac.code.Type type;

    public ViewGenInfo(String annotationAttrName, long annotationAttrValue, String filedName, String viewType, com.sun.tools.javac.code.Type type) {
        this.annotationAttrName = annotationAttrName;
        this.annotationAttrValue = annotationAttrValue;
        this.filedName = filedName;
        this.viewType = viewType;
        this.type = type;
    }

    @Override
    public String toString() {
        return "ViewGenInfo{" +
                "annotationAttrName='" + annotationAttrName + '\'' +
                ", annotationAttrValue=" + annotationAttrValue +
                ", filedName='" + filedName + '\'' +
                ", viewType='" + viewType + '\'' +
                ", type=" + type +
                '}';
    }
}
