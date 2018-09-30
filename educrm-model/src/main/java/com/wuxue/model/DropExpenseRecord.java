package com.wuxue.model;

import com.wuxue.base.BusinessPage;

import java.math.BigDecimal;
import java.util.Date;

public class DropExpenseRecord extends BusinessPage{
    private Long pkDropExpenseRecord;

    private String proportionId;

    private BigDecimal cost;

    private BigDecimal discount;

    private BigDecimal receivableCost;

    private BigDecimal receipts;

    private Integer businessType;

    private BigDecimal businessCost;

    private String businessId;

    private String schoolYear;

    private String pkStudent;


    public Long getPkDropExpenseRecord() {
        return pkDropExpenseRecord;
    }

    public void setPkDropExpenseRecord(Long pkDropExpenseRecord) {
        this.pkDropExpenseRecord = pkDropExpenseRecord;
    }

    public String getProportionId() {
        return proportionId;
    }

    public void setProportionId(String proportionId) {
        this.proportionId = proportionId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getReceivableCost() {
        return receivableCost;
    }

    public void setReceivableCost(BigDecimal receivableCost) {
        this.receivableCost = receivableCost;
    }

    public BigDecimal getReceipts() {
        return receipts;
    }

    public void setReceipts(BigDecimal receipts) {
        this.receipts = receipts;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public BigDecimal getBusinessCost() {
        return businessCost;
    }

    public void setBusinessCost(BigDecimal businessCost) {
        this.businessCost = businessCost;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

}