package com.wuxue.model;

import com.wuxue.base.BaiscPage;

public class CourseTeacherKey extends BaiscPage {
    private String pkEmployee;

    private String pkSysDictValues;

    public String getPkEmployee() {
        return pkEmployee;
    }

    public void setPkEmployee(String pkEmployee) {
        this.pkEmployee = pkEmployee;
    }

    public String getPkSysDictValues() {
        return pkSysDictValues;
    }

    public void setPkSysDictValues(String pkSysDictValues) {
        this.pkSysDictValues = pkSysDictValues;
    }
}