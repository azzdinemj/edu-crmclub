package com.wuxue.model;

public class SysMenuButton extends SysMenuButtonKey {
    private String caption;

    public String getCaptionEng() {
        return captionEng;
    }

    public void setCaptionEng(String captionEng) {
        this.captionEng = captionEng;
    }

    private String captionEng;

    private String img;

    private Integer hasline;

    private Integer display;

    private Integer showmsg;

    private Integer hasstate;

    private Integer width;

    private Integer height;

    private Integer shorts;

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getHasline() {
        return hasline;
    }

    public void setHasline(Integer hasline) {
        this.hasline = hasline;
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public Integer getShowmsg() {
        return showmsg;
    }

    public void setShowmsg(Integer showmsg) {
        this.showmsg = showmsg;
    }

    public Integer getHasstate() {
        return hasstate;
    }

    public void setHasstate(Integer hasstate) {
        this.hasstate = hasstate;
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

    public Integer getShorts() {
        return shorts;
    }

    public void setShorts(Integer shorts) {
        this.shorts = shorts;
    }
}