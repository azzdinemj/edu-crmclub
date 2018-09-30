package com.wuxue.model;

import com.wuxue.base.Page;

public class StudentStatusReport extends Page{
    private Integer istype;

    private Long number;

    public Integer getIstype() {
        return istype;
    }

    public void setIstype(Integer istype) {
        this.istype = istype;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}