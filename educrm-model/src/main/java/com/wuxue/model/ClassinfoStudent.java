package com.wuxue.model;

import java.util.Date;

public class ClassinfoStudent extends ClassinfoStudentKey {
    private Integer kind;

    private String memo;

    private Integer isvalid;

    private String pkStudentSignup;

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public String getPkStudentSignup() {
        return pkStudentSignup;
    }

    public void setPkStudentSignup(String pkStudentSignup) {
        this.pkStudentSignup = pkStudentSignup;
    }
}