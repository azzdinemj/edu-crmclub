package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class EmployeeCertificate extends BaiscPage {
    private String pkEmployeeCertificate;

    private String pkEmployee;

    private Date date;

    private String dateTime;

    private String certificateName;

    private String memo;

    public String getPkEmployeeCertificate() {
        return pkEmployeeCertificate;
    }

    public void setPkEmployeeCertificate(String pkEmployeeCertificate) {
        this.pkEmployeeCertificate = pkEmployeeCertificate;
    }

    public String getPkEmployee() {
        return pkEmployee;
    }

    public void setPkEmployee(String pkEmployee) {
        this.pkEmployee = pkEmployee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
