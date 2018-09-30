package com.wuxue.model;

import com.wuxue.base.BusinessPage;

import java.math.BigDecimal;
import java.util.Date;

public class StudentSignup extends BusinessPage {
    private String pkStudentSignup;

    private String pkDomain;

    private String pkStudent;

    private String pkEmployee;

    private Date date;

    private String dateTime;

    private String code;

    private String oldSchool;

    private String oldGrade;

    private String idResident;

    private String program;

    private String grade;

    private String className;

    private String pkClassinfo;

    private String paymentMethod;

    private String bankAccountCaption;

    private String bankAccount;

    private String bankCaption;

    private String bankAddress;

    private BigDecimal mny;

    private BigDecimal mnyUsd;

    private BigDecimal discount;

    private BigDecimal money;

    private BigDecimal moneyUsd;

    private String responsiblePerson;

    private String caption;

    private String auditOpinion;

    public String getHasClassInfo() {
        return hasClassInfo;
    }

    public void setHasClassInfo(String hasClassInfo) {
        this.hasClassInfo = hasClassInfo;
    }

    private String hasClassInfo;

    private String memo;

    private Integer status;

    private String studentName;

    private String signupType;

    private Integer reviewStatus;//0未提交 1已提交 2审核中 3审核成功 4驳回成功

    private String schoolYear;

    public String getPkStudentSignup() {
        return pkStudentSignup;
    }

    public void setPkStudentSignup(String pkStudentSignup) {
        this.pkStudentSignup = pkStudentSignup;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOldSchool() {
        return oldSchool;
    }

    public void setOldSchool(String oldSchool) {
        this.oldSchool = oldSchool;
    }

    public String getOldGrade() {
        return oldGrade;
    }

    public void setOldGrade(String oldGrade) {
        this.oldGrade = oldGrade;
    }

    public String getIdResident() {
        return idResident;
    }

    public void setIdResident(String idResident) {
        this.idResident = idResident;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPkClassinfo() {
        return pkClassinfo;
    }

    public void setPkClassinfo(String pkClassinfo) {
        this.pkClassinfo = pkClassinfo;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getBankAccountCaption() {
        return bankAccountCaption;
    }

    public void setBankAccountCaption(String bankAccountCaption) {
        this.bankAccountCaption = bankAccountCaption;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankCaption() {
        return bankCaption;
    }

    public void setBankCaption(String bankCaption) {
        this.bankCaption = bankCaption;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public BigDecimal getMny() {
        return mny;
    }

    public void setMny(BigDecimal mny) {
        this.mny = mny;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
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

    public BigDecimal getMnyUsd() {
        return mnyUsd;
    }

    public void setMnyUsd(BigDecimal mnyUsd) {
        this.mnyUsd = mnyUsd;
    }

    public BigDecimal getMoneyUsd() {
        return moneyUsd;
    }

    public void setMoneyUsd(BigDecimal moneyUsd) {
        this.moneyUsd = moneyUsd;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getSignupType() {
        return signupType;
    }

    public void setSignupType(String signupType) {
        this.signupType = signupType;
    }

    public String getAuditOpinion() {
        return auditOpinion;
    }

    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion;
    }

    public Integer getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(Integer reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }
}