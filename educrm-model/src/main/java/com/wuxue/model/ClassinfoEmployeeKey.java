package com.wuxue.model;

import com.wuxue.base.BaiscPage;

public class ClassinfoEmployeeKey extends BaiscPage {
    private String pkClassinfo;

    private String pkEmployee;

    public String getPkClassinfo() {
        return pkClassinfo;
    }

    public void setPkClassinfo(String pkClassinfo) {
        this.pkClassinfo = pkClassinfo;
    }

    public String getPkEmployee() {
        return pkEmployee;
    }

    public void setPkEmployee(String pkEmployee) {
        this.pkEmployee = pkEmployee;
    }
}