package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class StudentShift extends BaiscPage{
    private String pkStudentShift;

    private String pkStudent;

    private String pkParentClassinfo;

    private String pkClassinfo;

    private Integer isvalid;

    public String getPkStudentShift() {
        return pkStudentShift;
    }

    public void setPkStudentShift(String pkStudentShift) {
        this.pkStudentShift = pkStudentShift;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getPkParentClassinfo() {
        return pkParentClassinfo;
    }

    public void setPkParentClassinfo(String pkParentClassinfo) {
        this.pkParentClassinfo = pkParentClassinfo;
    }

    public String getPkClassinfo() {
        return pkClassinfo;
    }

    public void setPkClassinfo(String pkClassinfo) {
        this.pkClassinfo = pkClassinfo;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

}