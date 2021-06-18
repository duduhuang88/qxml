package com.qxml.tools.model;

public class CompatViewInfoModel {

    String compatViewClassName;
    String[] compatCondition;
    String viewClassName;

    public String getViewClassName() {
        return viewClassName;
    }

    public void setViewClassName(String viewClassName) {
        this.viewClassName = viewClassName;
    }

    public String getCompatViewClassName() {
        return compatViewClassName;
    }

    public void setCompatViewClassName(String compatViewClassName) {
        this.compatViewClassName = compatViewClassName;
    }

    public String[] getCompatCondition() {
        return compatCondition;
    }

    public void setCompatCondition(String[] compatCondition) {
        this.compatCondition = compatCondition;
    }
}
