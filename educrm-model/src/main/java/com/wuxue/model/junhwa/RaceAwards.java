package com.wuxue.model.junhwa;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wuxue.base.Page;

import java.util.Date;
import java.util.List;

/**
 *
 * TABLE  t_race_awards
 * MyBatis Generator Create
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RaceAwards extends Page {
    /**
     * awards_id
     * 奖项主键
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String awardsId;

    /**
     * awards_name
     * 奖项名称
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String awardsName;

    /**
     * description
     * 奖项说明
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String description;

    /**
     * race_id
     * 竞赛id
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String raceId;

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

    //学生集
    private List<AwardsStudent> awardsStudentList;

    public List<AwardsStudent> getAwardsStudentList() {
        return awardsStudentList;
    }

    private String studentIds;

    public void setAwardsStudentList(List<AwardsStudent> awardsStudentList) {
        this.awardsStudentList = awardsStudentList;
    }

    public String getAwardsId() {
        return awardsId;
    }

    public void setAwardsId(String awardsId) {
        this.awardsId = awardsId;
    }

    public String getAwardsName() {
        return awardsName;
    }

    public void setAwardsName(String awardsName) {
        this.awardsName = awardsName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRaceId() {
        return raceId;
    }

    public void setRaceId(String raceId) {
        this.raceId = raceId;
    }

    public String getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(String studentIds) {
        this.studentIds = studentIds;
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