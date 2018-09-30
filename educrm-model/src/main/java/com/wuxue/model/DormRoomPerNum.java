package com.wuxue.model;

import com.wuxue.base.BusinessPage;

public class DormRoomPerNum extends BusinessPage{
    private String pkDomain;

    private String pkDormRoom;

    private Long stunum;

    private Integer sex;

    private Integer kind;

    private Integer currentNum;

    private Long empnum;

    private Long num;

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getPkDormRoom() {
        return pkDormRoom;
    }

    public void setPkDormRoom(String pkDormRoom) {
        this.pkDormRoom = pkDormRoom;
    }

    public Long getStunum() {
        return stunum;
    }

    public void setStunum(Long stunum) {
        this.stunum = stunum;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public Integer getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(Integer currentNum) {
        this.currentNum = currentNum;
    }

    public Long getEmpnum() {
        return empnum;
    }

    public void setEmpnum(Long empnum) {
        this.empnum = empnum;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }
}