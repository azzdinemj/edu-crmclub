package com.wuxue.model.shuttle;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class QrCode extends BusinessPage {
    private String pkQrCode;

    private String pkDomain;

    private String pkStudent;

    private Date deliveryDate;

    private Date startDate;

    private Date endDate;

    private String parameter;

    public String getPkQrCode() {
        return pkQrCode;
    }

    public void setPkQrCode(String pkQrCode) {
        this.pkQrCode = pkQrCode;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}