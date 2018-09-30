package com.wuxue.model;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class Schedule extends BusinessPage {
    private String pkSchedule;

    private String pkDomain;

    private String pkProduct;

    private String pkStudent;

    private String pkEmployee;

    private String pkClassRoom;

    private String pkClassTime;

    private Date startTime;

    private String startTimes;

    private Date endTime;

    private String endTimes;

    private String notes;

    private String notesEng;

    private String creator;

    private Date creationDate;

    private String modifier;

    private Date lasteditDate;

    private Integer status;
    private Integer type;
    private Integer isdel;

    private Integer sort;

    private Integer lectureType;
    private Integer cycle;

    private Date fromTime;

    private Date toTime;

    private Integer isfeedback;

    private Integer  productInclass;    //第几节课

    private String  pkTalkCloudRoom;    //拓客云教室

    private Integer productUseclasshours;//这节课使用的课时

    private String nationality;      // 查询条件：该排课信息下老师的国籍

    private String classinfoList;      // 查询条件：该排课信息下老师的国籍

    private String pkClassinfo;//班级主键

    private Integer weekType;


    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }


    public Integer getProductUseclasshours() {
        return productUseclasshours;
    }

    public void setProductUseclasshours(Integer productUseclasshours) {
        this.productUseclasshours = productUseclasshours;
    }



    public Integer getProductInclass() {
        return productInclass;
    }

    public void setProductInclass(Integer productInclass) {
        this.productInclass = productInclass;
    }

    public String getPkTalkCloudRoom() {
        return pkTalkCloudRoom;
    }

    public void setPkTalkCloudRoom(String pkTalkCloudRoom) {
        this.pkTalkCloudRoom = pkTalkCloudRoom;
    }

    public String getPkSchedule() {
        return pkSchedule;
    }

    public void setPkSchedule(String pkSchedule) {
        this.pkSchedule = pkSchedule;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getPkProduct() {
        return pkProduct;
    }

    public void setPkProduct(String pkProduct) {
        this.pkProduct = pkProduct;
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

    public String getPkClassRoom() {
        return pkClassRoom;
    }

    public void setPkClassRoom(String pkClassRoom) {
        this.pkClassRoom = pkClassRoom;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotesEng() {
        return notesEng;
    }

    public void setNotesEng(String notesEng) {
        this.notesEng = notesEng;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getLasteditDate() {
        return lasteditDate;
    }

    public void setLasteditDate(Date lasteditDate) {
        this.lasteditDate = lasteditDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getType() {
        return type;
    }

    public void setgetType(Integer type) {
        this.type = type;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public String getPkClassTime() {
        return pkClassTime;
    }

    public void setPkClassTime(String pkClassTime) {
        this.pkClassTime = pkClassTime;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
    public String getStartTimes() {
        return startTimes;
    }

    public void setStartTimes(String startTimes) {
        this.startTimes = startTimes;
    }

    public String getEndTimes() {
        return endTimes;
    }

    public void setEndTimes(String endTimes) {
        this.endTimes = endTimes;
    }

    public Integer getLectureType() {
        return lectureType;
    }

    public void setLectureType(Integer lectureType) {
        this.lectureType = lectureType;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date fromTime) {
        this.fromTime = fromTime;
    }

    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date toTime) {
        this.toTime = toTime;
    }

    public Integer getIsfeedback() {
        return isfeedback;
    }

    public void setIsfeedback(Integer isfeedback) {
        this.isfeedback = isfeedback;
    }

    public String getClassinfoList() {
        return classinfoList;
    }

    public void setClassinfoList(String classinfoList) {
        this.classinfoList = classinfoList;
    }

    public String getPkClassinfo() {
        return pkClassinfo;
    }

    public void setPkClassinfo(String pkClassinfo) {
        this.pkClassinfo = pkClassinfo;
    }

    public Integer getWeekType() {
        return weekType;
    }

    public void setWeekType(Integer weekType) {
        this.weekType = weekType;
    }
}