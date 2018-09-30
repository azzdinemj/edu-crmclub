package com.wuxue.model;

import com.wuxue.base.BaiscPage;

public class EmployeeHomeInfo extends BaiscPage {
    private String pkEmployeeHomeInfo;

    private String pkEmployee;

    private String name;

    private String relationship;

    private String jobUnit;

    private String jobTitle;

    private String phone;

    private String memo;

    public String getPkEmployeeHomeInfo() {
        return pkEmployeeHomeInfo;
    }

    public void setPkEmployeeHomeInfo(String pkEmployeeHomeInfo) {
        this.pkEmployeeHomeInfo = pkEmployeeHomeInfo;
    }

    public String getPkEmployee() {
        return pkEmployee;
    }

    public void setPkEmployee(String pkEmployee) {
        this.pkEmployee = pkEmployee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getJobUnit() {
        return jobUnit;
    }

    public void setJobUnit(String jobUnit) {
        this.jobUnit = jobUnit;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

}