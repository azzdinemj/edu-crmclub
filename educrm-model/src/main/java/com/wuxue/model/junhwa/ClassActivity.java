package com.wuxue.model.junhwa;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wuxue.base.Page;

import java.util.Date;
import java.util.List;

/**
 *
 * TABLE  t_class_activity
 * MyBatis Generator Create
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ClassActivity extends Page {
    /**
     * activity_id
     * 活动主键
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String activityId;

    /**
     * activity_name
     * 活动名称
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String activityName;

    /**
     * activity_time
     * 活动时间
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Date activityTime;

    /**
     * 活动时间String 类型 用于接收页面字符串时间
     */
    private String activityTimes;

    /**
     * description
     * 活动说明
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String description;

    /**
     * pk_classinfo
     * 班级id
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String pkClassinfo;

    /**
     * creation_date
     *
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Date creationDate;

    /**
     * creator
     *
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String creator;

    /**
     * lastedit_date
     *
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Date lasteditDate;

    /**
     * modifier
     *
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String modifier;

    //班级名称
    private String className;

    //图片集
    private List<ActivityImg> activityImgList;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ActivityImg> getActivityImgList() {
        return activityImgList;
    }

    public void setActivityImgList(List<ActivityImg> activityImgList) {
        this.activityImgList = activityImgList;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPkClassinfo() {
        return pkClassinfo;
    }

    public void setPkClassinfo(String pkClassinfo) {
        this.pkClassinfo = pkClassinfo;
    }

    public String getActivityTimes() {
        return activityTimes;
    }

    public void setActivityTimes(String activityTimes) {
        this.activityTimes = activityTimes;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getLasteditDate() {
        return lasteditDate;
    }

    public void setLasteditDate(Date lasteditDate) {
        this.lasteditDate = lasteditDate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}