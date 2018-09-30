package com.wuxue.model;

import com.wuxue.base.Page;

import java.util.List;

public class SysMenu extends Page {
    private String pkSysMenu;

    private String pkParent;

    private String caption;

    private String url;

    private Integer width;

    private Integer height;

    private Integer runmode;

    private Integer haschild;

    private String img;

    private Integer sort;

    private Integer isdisplay;

    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }

    public List<SysMenu> children;

    public String getCaptionEng() {
        return captionEng;
    }

    public void setCaptionEng(String captionEng) {
        this.captionEng = captionEng;
    }

    private String captionEng;

    public String getPkSysMenu() {
        return pkSysMenu;
    }

    public void setPkSysMenu(String pkSysMenu) {
        this.pkSysMenu = pkSysMenu;
    }

    public String getPkParent() {
        return pkParent;
    }

    public void setPkParent(String pkParent) {
        this.pkParent = pkParent;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getRunmode() {
        return runmode;
    }

    public void setRunmode(Integer runmode) {
        this.runmode = runmode;
    }

    public Integer getHaschild() {
        return haschild;
    }

    public void setHaschild(Integer haschild) {
        this.haschild = haschild;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsdisplay() {
        return isdisplay;
    }

    public void setIsdisplay(Integer isdisplay) {
        this.isdisplay = isdisplay;
    }
}