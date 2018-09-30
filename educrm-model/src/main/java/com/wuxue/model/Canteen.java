package com.wuxue.model;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class Canteen extends BusinessPage {
    private String pkCanteen;

    private String pkDomain;

    private String code;

    private String canteenName;

    private Short tableNumber;

    private String adress;

    private String pkEmployee;

    private Integer isvalid;

    private String memo;

    public String getPkCanteen() {
        return pkCanteen;
    }

    public void setPkCanteen(String pkCanteen) {
        this.pkCanteen = pkCanteen;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCanteenName() {
        return canteenName;
    }

    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }

    public Short getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Short tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPkEmployee() {
        return pkEmployee;
    }

    public void setPkEmployee(String pkEmployee) {
        this.pkEmployee = pkEmployee;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}