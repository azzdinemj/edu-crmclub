package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.math.BigDecimal;
import java.util.Date;

public class Recharge extends BaiscPage {
    private Integer pkRecharge;

    private BigDecimal money;

    private Date creationDate;

    private Integer type;

    private Integer userType;

    private Integer tag;

    private String pkStu;

    private String stuNo;

    private Integer status;

    public Integer getPkRecharge() {
        return pkRecharge;
    }

    public void setPkRecharge(Integer pkRecharge) {
        this.pkRecharge = pkRecharge;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }

    public String getPkStu() {
        return pkStu;
    }

    public void setPkStu(String pkStu) {
        this.pkStu = pkStu;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}