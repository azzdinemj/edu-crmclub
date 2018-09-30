package com.wuxue.model.card;

import com.wuxue.base.CardBase;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 微信部门
 */
@Data
public class Consumption extends CardBase implements Serializable{

    private static final long serialVersionUID = 1L;
    private String accountDay;

    private String t_Date;

    private String a_Number;

    private String transType;

    private String a_Name;

    private Integer w_ID;

    private Integer a_Accounts;

    private Integer pos_ID;

    private Integer transNo;

    private Integer c_Number;

    private Integer transFlag;

    private BigDecimal t_Money;

    public String getAccountDay() {
        return accountDay;
    }

    public void setAccountDay(String accountDay) {
        this.accountDay = accountDay;
    }

    public String getT_Date() {
        return t_Date;
    }

    public void setT_Date(String t_Date) {
        this.t_Date = t_Date;
    }

    public String getA_Number() {
        return a_Number;
    }

    public void setA_Number(String a_Number) {
        this.a_Number = a_Number;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getA_Name() {
        return a_Name;
    }

    public void setA_Name(String a_Name) {
        this.a_Name = a_Name;
    }

    public Integer getW_ID() {
        return w_ID;
    }

    public void setW_ID(Integer w_ID) {
        this.w_ID = w_ID;
    }

    public Integer getA_Accounts() {
        return a_Accounts;
    }

    public void setA_Accounts(Integer a_Accounts) {
        this.a_Accounts = a_Accounts;
    }

    public Integer getPos_ID() {
        return pos_ID;
    }

    public void setPos_ID(Integer pos_ID) {
        this.pos_ID = pos_ID;
    }

    public Integer getTransNo() {
        return transNo;
    }

    public void setTransNo(Integer transNo) {
        this.transNo = transNo;
    }

    public Integer getC_Number() {
        return c_Number;
    }

    public void setC_Number(Integer c_Number) {
        this.c_Number = c_Number;
    }

    public Integer getTransFlag() {
        return transFlag;
    }

    public void setTransFlag(Integer transFlag) {
        this.transFlag = transFlag;
    }

    public BigDecimal getT_Money() {
        return t_Money;
    }

    public void setT_Money(BigDecimal t_Money) {
        this.t_Money = t_Money;
    }
}
