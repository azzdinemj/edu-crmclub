package com.wuxue.model.shuttle;

import com.wuxue.base.BusinessPage;

public class DeliveryStudent extends BusinessPage {
    private String pkDeliveryStudent;

    private String pkStudent;

    private String pkDelivery;

    public String getPkDeliveryStudent() {
        return pkDeliveryStudent;
    }

    public void setPkDeliveryStudent(String pkDeliveryStudent) {
        this.pkDeliveryStudent = pkDeliveryStudent;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getPkDelivery() {
        return pkDelivery;
    }

    public void setPkDelivery(String pkDelivery) {
        this.pkDelivery = pkDelivery;
    }
}