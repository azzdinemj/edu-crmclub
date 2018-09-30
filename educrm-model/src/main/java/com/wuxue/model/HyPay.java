package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class HyPay extends BaiscPage {
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;
    private Integer  orderId;
    private Integer id;

    private Integer amount;

    private String payment;

    private String paymentType;

    private Date ceratorDate;

    private String creator;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Date getCeratorDate() {
        return ceratorDate;
    }

    public void setCeratorDate(Date ceratorDate) {
        this.ceratorDate = ceratorDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}