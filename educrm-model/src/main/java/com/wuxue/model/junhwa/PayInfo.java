package com.wuxue.model.junhwa;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * TABLE  pay_info
 * MyBatis Generator Create
 */
@Data
public class PayInfo implements Serializable {
    private static final long serialVersionUID = -2996934164680668804L;
    List<String> orderIdList;//订单id
    BigDecimal payableAmount;//应付金额

    public List<String> getOrderIdList() {
        return orderIdList;
    }

    public void setOrderIdList(List<String> orderIdList) {
        this.orderIdList = orderIdList;
    }

    public BigDecimal getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(BigDecimal payableAmount) {
        this.payableAmount = payableAmount;
    }
}