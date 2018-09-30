package com.wuxue.model;

import com.wuxue.base.BaiscPage;

public class ClassinfoStudentKey extends BaiscPage {
    private String pkClassinfo;

    private String pkStudent;

    public String getPkClassinfo() {
        return pkClassinfo;
    }

    public void setPkClassinfo(String pkClassinfo) {
        this.pkClassinfo = pkClassinfo;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }
}