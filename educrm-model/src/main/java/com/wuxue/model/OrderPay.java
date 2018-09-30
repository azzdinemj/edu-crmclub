package com.wuxue.model;

public class OrderPay {
    private String pkOrderPay;

    private String pkOrderid;

    private String pkParentPay;
    private String pkParentPayList;

    public String getPkOrderPay() {
        return pkOrderPay;
    }

    public void setPkOrderPay(String pkOrderPay) {
        this.pkOrderPay = pkOrderPay;
    }

    public String getPkOrderid() {
        return pkOrderid;
    }

    public void setPkOrderid(String pkOrderid) {
        this.pkOrderid = pkOrderid;
    }

    public String getPkParentPay() {
        return pkParentPay;
    }

    public void setPkParentPay(String pkParentPay) {
        this.pkParentPay = pkParentPay;
    }

    public String getPkParentPayList() {
        return pkParentPayList;
    }

    public void setPkParentPayList(String pkParentPayList) {
        this.pkParentPayList = pkParentPayList;
    }
}