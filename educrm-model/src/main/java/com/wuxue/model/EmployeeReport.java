package com.wuxue.model;

import com.wuxue.base.Page;

public class EmployeeReport extends Page{
    private String departmentname;

    private String dictname;

    private Long gg;

    private Long mm;

    private Long countp;

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getDictname() {
        return dictname;
    }

    public void setDictname(String dictname) {
        this.dictname = dictname;
    }

    public Long getGg() {
        return gg;
    }

    public void setGg(Long gg) {
        this.gg = gg;
    }

    public Long getMm() {
        return mm;
    }

    public void setMm(Long mm) {
        this.mm = mm;
    }

    public Long getCountp() {
        return countp;
    }

    public void setCountp(Long countp) {
        this.countp = countp;
    }
}