package com.wuxue.model.card;

import com.wuxue.base.CardBase;
import com.wuxue.base.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 微信部门
 */
@Data
public class Attendance extends CardBase implements Serializable{

    private static final long serialVersionUID = 1L;
    private Date cardDate;

    private String inOutFlag;

    private String a_Number;

    private String a_Name;

    private Integer w_ID;

    private Integer a_Accounts;

    private Integer pos_ID;

    private Integer c_Number;

    public Date getCardDate() {
        return cardDate;
    }

    public void setCardDate(Date cardDate) {
        this.cardDate = cardDate;
    }

    public String getInOutFlag() {
        return inOutFlag;
    }

    public void setInOutFlag(String inOutFlag) {
        this.inOutFlag = inOutFlag;
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

    public Integer getC_Number() {
        return c_Number;
    }

    public void setC_Number(Integer c_Number) {
        this.c_Number = c_Number;
    }
}
