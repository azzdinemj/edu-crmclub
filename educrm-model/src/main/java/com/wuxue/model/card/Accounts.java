package com.wuxue.model.card;

import com.wuxue.base.CardBase;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 微信部门
 */
@Data
public class Accounts extends CardBase implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer a_Accounts;

    private Integer dg_Id;

    private Integer d_Id;

    private Integer c_Number;

    private Integer c_Flag;

    private Integer a_flag;

    private Integer c_Password;

    private String a_Number;

    private String a_Name;

    private String a_Sex;

    private String dg_Name;

    private String d_Name;

    private String c_PhysicalNo;

    private String pin;

    private String bankAccount;

    private String nameSpell;

    private String mobile;

    private BigDecimal c_LastMoney;

    private BigDecimal c_LastMoney2;

    public Integer getA_Accounts() {
        return a_Accounts;
    }

    public void setA_Accounts(Integer a_Accounts) {
        this.a_Accounts = a_Accounts;
    }

    public Integer getDg_Id() {
        return dg_Id;
    }

    public void setDg_Id(Integer dg_Id) {
        this.dg_Id = dg_Id;
    }

    public Integer getD_Id() {
        return d_Id;
    }

    public void setD_Id(Integer d_Id) {
        this.d_Id = d_Id;
    }

    public Integer getC_Number() {
        return c_Number;
    }

    public void setC_Number(Integer c_Number) {
        this.c_Number = c_Number;
    }

    public Integer getC_Flag() {
        return c_Flag;
    }

    public void setC_Flag(Integer c_Flag) {
        this.c_Flag = c_Flag;
    }

    public Integer getA_flag() {
        return a_flag;
    }

    public void setA_flag(Integer a_flag) {
        this.a_flag = a_flag;
    }

    public Integer getC_Password() {
        return c_Password;
    }

    public void setC_Password(Integer c_Password) {
        this.c_Password = c_Password;
    }

    public String getA_Number() {
        return a_Number;
    }

    public void setA_Number(String a_Number) {
        this.a_Number = a_Number;
    }

    public String getA_Name() {
        return a_Name;
    }

    public void setA_Name(String a_Name) {
        this.a_Name = a_Name;
    }

    public String getA_Sex() {
        return a_Sex;
    }

    public void setA_Sex(String a_Sex) {
        this.a_Sex = a_Sex;
    }

    public String getDg_Name() {
        return dg_Name;
    }

    public void setDg_Name(String dg_Name) {
        this.dg_Name = dg_Name;
    }

    public String getD_Name() {
        return d_Name;
    }

    public void setD_Name(String d_Name) {
        this.d_Name = d_Name;
    }

    public String getC_PhysicalNo() {
        return c_PhysicalNo;
    }

    public void setC_PhysicalNo(String c_PhysicalNo) {
        this.c_PhysicalNo = c_PhysicalNo;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getNameSpell() {
        return nameSpell;
    }

    public void setNameSpell(String nameSpell) {
        this.nameSpell = nameSpell;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getC_LastMoney() {
        return c_LastMoney;
    }

    public void setC_LastMoney(BigDecimal c_LastMoney) {
        this.c_LastMoney = c_LastMoney;
    }

    public BigDecimal getC_LastMoney2() {
        return c_LastMoney2;
    }

    public void setC_LastMoney2(BigDecimal c_LastMoney2) {
        this.c_LastMoney2 = c_LastMoney2;
    }
}
