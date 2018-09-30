package com.wuxue.model;

import java.util.Date;

public class Datavalid {
    private String id;

    private String phone;

    private String pkempstu;

    private Integer type;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPkempstu() {
        return pkempstu;
    }

    public void setPkempstu(String pkempstu) {
        this.pkempstu = pkempstu;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}