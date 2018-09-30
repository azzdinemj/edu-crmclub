package com.wuxue.model.card;

import com.wuxue.base.CardBase;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 微信部门
 */
@Data
public class Student extends CardBase implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer accountId;

//    部门id
    private Integer departmentId;

//    一卡通卡号
    private Integer cardId;

//    编号
    private String accountNo;

//    姓名
    private String name;

//    性别
    private String gender;

//    生日
    private String birthday;

//    家长姓名
    private String paterfamilias;

//    银行卡号
    private String bank;

//    身份证号
    private String pin;

//    住址
    private String address;

//    密码
    private String password;

//    手机号
    private String mobile;

    //主錢包（一般使用）
    private BigDecimal money;

    //小錢包
    private BigDecimal money2;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPaterfamilias() {
        return paterfamilias;
    }

    public void setPaterfamilias(String paterfamilias) {
        this.paterfamilias = paterfamilias;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getMoney2() {
        return money2;
    }

    public void setMoney2(BigDecimal money2) {
        this.money2 = money2;
    }
}
