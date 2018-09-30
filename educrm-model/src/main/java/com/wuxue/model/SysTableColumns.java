package com.wuxue.model;

import com.wuxue.base.Page;

public class SysTableColumns extends Page {
    private String pkSysTableColumns;

    private String pkSysTableColumn;

    private String caption;

    private String data;

    private Integer visible;

    private Integer display;

    private Integer sort;

    private Integer width;

    private String render;

    private String textAlign;

    private String captionEng;

    public String getPkSysTableColumns() {
        return pkSysTableColumns;
    }

    public void setPkSysTableColumns(String pkSysTableColumns) {
        this.pkSysTableColumns = pkSysTableColumns;
    }

    public String getPkSysTableColumn() {
        return pkSysTableColumn;
    }

    public void setPkSysTableColumn(String pkSysTableColumn) {
        this.pkSysTableColumn = pkSysTableColumn;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getRender() {
        return render;
    }

    public void setRender(String render) {
        this.render = render;
    }

    public String getTextAlign() {
        return textAlign;
    }

    public void setTextAlign(String textAlign) {
        this.textAlign = textAlign;
    }

    public String getCaptionEng() {
        return captionEng;
    }

    public void setCaptionEng(String captionEng) {
        this.captionEng = captionEng;
    }
}