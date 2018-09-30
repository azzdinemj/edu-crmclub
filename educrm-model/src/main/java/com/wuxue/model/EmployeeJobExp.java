package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class EmployeeJobExp extends BaiscPage {
    private String pkEmployeeJobExp;

    private String pkEmployee;

    private Date startdate;

    private Date enddate;

    private String startdateTime;
    private String enddateTime;

    private String jobUnit;

    private String jobTitle;

    private String witness;

    private String witnessPhone;

    private String reasonLeaving;

    private String memo;

    public String getPkEmployeeJobExp() {
        return pkEmployeeJobExp;
    }

    public void setPkEmployeeJobExp(String pkEmployeeJobExp) {
        this.pkEmployeeJobExp = pkEmployeeJobExp;
    }

    public String getPkEmployee() {
        return pkEmployee;
    }

    public void setPkEmployee(String pkEmployee) {
        this.pkEmployee = pkEmployee;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
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

    public String getWitness() {
        return witness;
    }

    public void setWitness(String witness) {
        this.witness = witness;
    }

    public String getWitnessPhone() {
        return witnessPhone;
    }

    public void setWitnessPhone(String witnessPhone) {
        this.witnessPhone = witnessPhone;
    }

    public String getReasonLeaving() {
        return reasonLeaving;
    }

    public void setReasonLeaving(String reasonLeaving) {
        this.reasonLeaving = reasonLeaving;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getStartdateTime() {
        return startdateTime;
    }

    public void setStartdateTime(String startdateTime) {
        this.startdateTime = startdateTime;
    }

    public String getEnddateTime() {
        return enddateTime;
    }

    public void setEnddateTime(String enddateTime) {
        this.enddateTime = enddateTime;
    }
}