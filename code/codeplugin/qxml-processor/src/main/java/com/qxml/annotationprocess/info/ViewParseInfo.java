package com.qxml.annotationprocess.info;

public class ViewParseInfo implements Comparable<ViewParseInfo>{
    public String ViewGenClassName;
    public int priority;

    public ViewParseInfo(String viewGenClassName, int priority) {
        ViewGenClassName = viewGenClassName;
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "ViewParseInfo{" +
                "ViewGenClassName='" + ViewGenClassName + '\'' +
                ", priority=" + priority +
                '}';
    }

    @Override
    public int compareTo(ViewParseInfo viewParseInfo) {
        return viewParseInfo.priority - priority;
    }
}
