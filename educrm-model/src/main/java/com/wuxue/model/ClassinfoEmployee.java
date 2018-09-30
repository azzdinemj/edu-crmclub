package com.wuxue.model;

public class ClassinfoEmployee extends ClassinfoEmployeeKey {
    private String pkDepartment;

    private Integer isvalid;

    private Integer kind;

    private String memo;

    private String caption;

    public String getPkDepartment() {
        return pkDepartment;
    }

    public void setPkDepartment(String pkDepartment) {
        this.pkDepartment = pkDepartment;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}