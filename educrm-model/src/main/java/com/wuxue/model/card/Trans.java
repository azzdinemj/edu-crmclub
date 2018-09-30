package com.wuxue.model.card;

import com.wuxue.base.CardBase;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 微信部门
 */
@Data
public class Trans extends CardBase implements Serializable{

    private static final long serialVersionUID = 1L;
    //    交易类型 2充值
    private Integer transType;

    //    交易途径 0默认
    private Integer transTag;

    //    用户类型
    private Integer userType;

    //    交易流水号
    private Integer transNo;

    //    交易金额
    private BigDecimal money;

    //    消费时间   格式：yyyy-MM-dd HH:mm:ss
    private String transDate;

    //    账号编号
    private String accountNo;

    public Integer getTransType() {
        return transType;
    }

    public void setTransType(Integer transType) {
        this.transType = transType;
    }

    public Integer getTransTag() {
        return transTag;
    }

    public void setTransTag(Integer transTag) {
        this.transTag = transTag;
    }

    public Integer getTransNo() {
        return transNo;
    }

    public void setTransNo(Integer transNo) {
        this.transNo = transNo;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
}
