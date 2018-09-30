package com.wuxue.model;

import com.wuxue.base.BusinessPage;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SchoolBus extends BusinessPage {
    private String pkSchoolBus;

    private String pkDomain;

    private String caption;

    private String busCode;

    private Integer num;

    private Integer currentNum;

    private Integer sort;

    private Integer isvalid;

    private BigDecimal expenses;

    private String kind;

    private String memo;

    private Date schoolDepartDate; //校内发车时间

    private String schoolDepartDateTime;

    private String schoolDepartPlace;  //校内发车地点

    private Date offschDepartDate;      //校外发车时间

    private String offschDepartDateTime;

    private String offschDepartPlace;  //校外发车地点

    private List<Student> studens;

    private String driver;  //司机

    private String guardianTeacher;   //随车监护老师



    public List<Student> getStudens() {
        return studens;
    }

    public void setStudens(List<Student> studens) {
        this.studens = studens;
    }

    public String getPkSchoolBus() {
        return pkSchoolBus;
    }

    public void setPkSchoolBus(String pkSchoolBus) {
        this.pkSchoolBus = pkSchoolBus;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getBusCode() {
        return busCode;
    }

    public void setBusCode(String busCode) {
        this.busCode = busCode;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(Integer currentNum) {
        this.currentNum = currentNum;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(Integer isvalid) {
        this.isvalid = isvalid;
    }

    public BigDecimal getExpenses() {
        return expenses;
    }

    public void setExpenses(BigDecimal expenses) {
        this.expenses = expenses;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getSchoolDepartDate() {
        return schoolDepartDate;
    }

    public void setSchoolDepartDate(Date schoolDepartDate) {
        this.schoolDepartDate = schoolDepartDate;
    }

    public String getSchoolDepartPlace() {
        return schoolDepartPlace;
    }

    public void setSchoolDepartPlace(String schoolDepartPlace) {
        this.schoolDepartPlace = schoolDepartPlace;
    }

    public Date getOffschDepartDate() {
        return offschDepartDate;
    }

    public void setOffschDepartDate(Date offschDepartDate) {
        this.offschDepartDate = offschDepartDate;
    }

    public String getOffschDepartPlace() {
        return offschDepartPlace;
    }

    public void setOffschDepartPlace(String offschDepartPlace) {
        this.offschDepartPlace = offschDepartPlace;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getGuardianTeacher() {
        return guardianTeacher;
    }

    public void setGuardianTeacher(String guardianTeacher) {
        this.guardianTeacher = guardianTeacher;
    }

    public String getSchoolDepartDateTime() {
        return schoolDepartDateTime;
    }

    public void setSchoolDepartDateTime(String schoolDepartDateTime) {
        this.schoolDepartDateTime = schoolDepartDateTime;
    }

    public String getOffschDepartDateTime() {
        return offschDepartDateTime;
    }

    public void setOffschDepartDateTime(String offschDepartDateTime) {
        this.offschDepartDateTime = offschDepartDateTime;
    }
}