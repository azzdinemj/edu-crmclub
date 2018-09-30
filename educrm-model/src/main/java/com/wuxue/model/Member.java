package com.wuxue.model;

import com.wuxue.base.BaiscPage;
import com.wuxue.base.Page;

public class Member extends BaiscPage {
    private String pkMember;

    private String caption;

    private String phone;

    private String contnet;

    private Integer sex;

    private String remarks;

    private Integer isdel;

    private Integer status;

    public String getPkMember() {
        return pkMember;
    }

    public void setPkMember(String pkMember) {
        this.pkMember = pkMember;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContnet() {
        return contnet;
    }

    public void setContnet(String contnet) {
        this.contnet = contnet;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}