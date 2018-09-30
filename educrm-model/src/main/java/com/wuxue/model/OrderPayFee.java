package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.math.BigDecimal;

public class OrderPayFee extends BaiscPage {
    private Integer pkOrderPayFee;

    private String pkOrderid;

    private BigDecimal fee;

    private Integer type;

    public Integer getPkOrderPayFee() {
        return pkOrderPayFee;
    }

    public void setPkOrderPayFee(Integer pkOrderPayFee) {
        this.pkOrderPayFee = pkOrderPayFee;
    }

    public String getPkOrderid() {
        return pkOrderid;
    }

    public void setPkOrderid(String pkOrderid) {
        this.pkOrderid = pkOrderid;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}