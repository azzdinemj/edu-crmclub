package com.wuxue.model;

import com.wuxue.base.Page;

public class ClassinfoStudentReport extends Page{
    private String classname;

    private String headteachername;

    private String secondteachername;

    private Long stuboardnum;

    private Long mm;

    private Long gg;

    private Long number;

    private Long busStu;

    private String pkClassinfo;

    private String headTeacher;

    private String secondTeacher;

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getHeadteachername() {
        return headteachername;
    }

    public void setHeadteachername(String headteachername) {
        this.headteachername = headteachername;
    }

    public String getSecondteachername() {
        return secondteachername;
    }

    public void setSecondteachername(String secondteachername) {
        this.secondteachername = secondteachername;
    }

    public Long getStuboardnum() {
        return stuboardnum;
    }

    public void setStuboardnum(Long stuboardnum) {
        this.stuboardnum = stuboardnum;
    }

    public Long getMm() {
        return mm;
    }

    public void setMm(Long mm) {
        this.mm = mm;
    }

    public Long getGg() {
        return gg;
    }

    public void setGg(Long gg) {
        this.gg = gg;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getPkClassinfo() {
        return pkClassinfo;
    }

    public void setPkClassinfo(String pkClassinfo) {
        this.pkClassinfo = pkClassinfo;
    }

    public String getHeadTeacher() {
        return headTeacher;
    }

    public void setHeadTeacher(String headTeacher) {
        this.headTeacher = headTeacher;
    }

    public String getSecondTeacher() {
        return secondTeacher;
    }

    public void setSecondTeacher(String secondTeacher) {
        this.secondTeacher = secondTeacher;

    }

    public Long getBusStu() {
        return busStu;
    }

    public void setBusStu(Long busStu) {
        this.busStu = busStu;
    }
}