package com.wuxue.model.junhwa;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wuxue.base.Page;

import java.util.Date;
import java.util.List;

/**
 * TABLE  t_race_record@RequestBody  RaceRecord raceRecord
 * MyBatis Generator Create
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RaceRecord extends Page {
    /**
     * race_id
     * 竞赛主键
     * <p>
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String raceId;

    /**
     * race_name
     * 竞赛名称
     * <p>
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String raceName;

    /**
     * sponsor
     * 主办单位
     * <p>
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String sponsor;

    /**
     * race_date
     * 竞赛日期
     * <p>
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Date raceDate;

    /**
     * race_date
     * 竞赛日期String
     */
    private String raceDateTime;

    /**
     * max_honor
     * 最大荣誉
     * <p>
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String maxHonor;

    /**
     * description
     * 竞赛描述
     * <p>
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String description;

    /**
     * pk_classinfo
     * 班级id
     * <p>
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String pkClassinfo;

    /**
     * creation_date
     * <p>
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Date creationDate;

    /**
     * creator
     * <p>
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String creator;

    /**
     * lastedit_date
     * <p>
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Date lasteditDate;

    /**
     * modifier
     * <p>
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String modifier;

    //班级名称
    private String className;

    //奖项集
    private List<RaceAwards> raceAwardsList;

    //图片集
    private List<ActivityImg> activityImgList;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<RaceAwards> getRaceAwardsList() {
        return raceAwardsList;
    }

    public void setRaceAwardsList(List<RaceAwards> raceAwardsList) {
        this.raceAwardsList = raceAwardsList;
    }

    public List<ActivityImg> getActivityImgList() {
        return activityImgList;
    }

    public void setActivityImgList(List<ActivityImg> activityImgList) {
        this.activityImgList = activityImgList;
    }

    public String getRaceId() {
        return raceId;
    }

    public void setRaceId(String raceId) {
        this.raceId = raceId;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public Date getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(Date raceDate) {
        this.raceDate = raceDate;
    }

    public String getMaxHonor() {
        return maxHonor;
    }

    public void setMaxHonor(String maxHonor) {
        this.maxHonor = maxHonor;
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

    public String getRaceDateTime() {
        return raceDateTime;
    }

    public void setRaceDateTime(String raceDateTime) {
        this.raceDateTime = raceDateTime;
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