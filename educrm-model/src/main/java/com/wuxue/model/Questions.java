package com.wuxue.model;

import com.wuxue.base.BaiscPage;

public class Questions extends BaiscPage{
    private String pkQuestions;

    private String type;

    private String img;

    private String title;

    private String url;

    private Integer status;

    private Integer isdel;

    public String getPkQuestions() {
        return pkQuestions;
    }

    public void setPkQuestions(String pkQuestions) {
        this.pkQuestions = pkQuestions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

}