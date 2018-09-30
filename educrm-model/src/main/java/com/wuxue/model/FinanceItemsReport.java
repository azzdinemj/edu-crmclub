package com.wuxue.model;

import com.wuxue.base.BusinessPage;

import java.math.BigDecimal;

public class FinanceItemsReport extends BusinessPage {
    private String pkExpenseItem;

    private BigDecimal sum;

    public String getPkExpenseItem() {
        return pkExpenseItem;
    }

    public void setPkExpenseItem(String pkExpenseItem) {
        this.pkExpenseItem = pkExpenseItem;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}