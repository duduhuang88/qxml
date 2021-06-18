package com.qxml.annotationprocess.info;

public class LayoutFileInfo {

    public String layoutDirName;
    public String layoutName;
    public String layoutXmlName;
    public String layoutAbsolutePath;
    public long lastModified;

    public LayoutFileInfo(String layoutDirName, String layoutName, String layoutXmlName, String layoutAbsolutePath, long lastModified) {
        this.layoutDirName = layoutDirName;
        this.layoutName = layoutName;
        this.layoutXmlName = layoutXmlName;
        this.layoutAbsolutePath = layoutAbsolutePath;
        this.lastModified = lastModified;
    }
}
