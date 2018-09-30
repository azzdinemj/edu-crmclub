package com.wuxue.model;

import com.wuxue.base.BusinessPage;

import java.math.BigDecimal;

public class ParentOrderEdit extends BusinessPage {
    private String pkOrderEdit;

    private String parentOrder;

    private String pkStudent;

    private String pkLinkman;

    private BigDecimal oldCost;

    private BigDecimal cost;

    private String oldMealId;

    private String newMealId;

    private Integer type;

    public String getOldMealId() {
        return oldMealId;
    }

    public void setOldMealId(String oldMealId) {
        this.oldMealId = oldMealId;
    }

    public String getNewMealId() {
        return newMealId;
    }

    public void setNewMealId(String newMealId) {
        this.newMealId = newMealId;
    }

    public String getPkOrderEdit() {
        return pkOrderEdit;
    }

    public void setPkOrderEdit(String pkOrderEdit) {
        this.pkOrderEdit = pkOrderEdit;
    }

    public String getParentOrder() {
        return parentOrder;
    }

    public void setParentOrder(String parentOrder) {
        this.parentOrder = parentOrder;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getPkLinkman() {
        return pkLinkman;
    }

    public void setPkLinkman(String pkLinkman) {
        this.pkLinkman = pkLinkman;
    }

    public BigDecimal getOldCost() {
        return oldCost;
    }

    public void setOldCost(BigDecimal oldCost) {
        this.oldCost = oldCost;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}