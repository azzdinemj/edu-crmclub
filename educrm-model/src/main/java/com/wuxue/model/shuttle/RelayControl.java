package com.wuxue.model.shuttle;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class RelayControl extends BusinessPage {
    private String pkRelayControl;

    private String pkDomain;

    private String qrCode;

    private String personId;

    private String notice;

    private Date deliveryDate;

    private Integer status;

    private Integer type;

    private String deliveryApplicationId;

    public String getPkRelayControl() {
        return pkRelayControl;
    }

    public void setPkRelayControl(String pkRelayControl) {
        this.pkRelayControl = pkRelayControl;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDeliveryApplicationId() {
        return deliveryApplicationId;
    }

    public void setDeliveryApplicationId(String deliveryApplicationId) {
        this.deliveryApplicationId = deliveryApplicationId;
    }
}