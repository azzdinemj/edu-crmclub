package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;
/**
 *
 * description: 学习记录表
 * @auther: wh
 * @date: 2018/6/13 14:03
 */
public class TkLearnRecords  extends BaiscPage{
    private String pkLearnRecords;

    private String pkStudent;

    private String pkSchedule;

    private Integer productType;

    private String type;

    private String creator;

    private Date creationDate;

    private String modifier;

    private Date lasteditDate;

    public String getPkLearnRecords() {
        return pkLearnRecords;
    }

    public void setPkLearnRecords(String pkLearnRecords) {
        this.pkLearnRecords = pkLearnRecords;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getPkSchedule() {
        return pkSchedule;
    }

    public void setPkSchedule(String pkSchedule) {
        this.pkSchedule = pkSchedule;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}