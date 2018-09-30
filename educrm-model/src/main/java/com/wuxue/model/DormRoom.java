package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.math.BigDecimal;

public class DormRoom extends BaiscPage {
    private String pkDormRoom;

    private String pkDomain;

    private String pkClass;

    private String code;

    private String caption;

    private Integer kind;

    private Integer num;

    private Integer currentNum;

    private Integer sort;

    private String memo;

    private Integer isvalid;

    private Integer sex;

    private BigDecimal expenses;

    public String getPkDormRoom() {
        return pkDormRoom;
    }

    public void setPkDormRoom(String pkDormRoom) {
        this.pkDormRoom = pkDormRoom;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getPkClass() {
        return pkClass;
    }

    public void setPkClass(String pkClass) {
        this.pkClass = pkClass;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(Integer currentNum) {
        this.currentNum = currentNum;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public BigDecimal getExpenses() {
        return expenses;
    }

    public void setExpenses(BigDecimal expenses) {
        this.expenses = expenses;
    }
}