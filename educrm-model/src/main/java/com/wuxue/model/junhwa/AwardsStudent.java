package com.wuxue.model.junhwa;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 *
 * TABLE  t_awards_student
 * MyBatis Generator Create
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AwardsStudent {
    /**
     * awards_student_id
     * 奖项学生关联主键
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String awardsStudentId;

    /**
     * awards_id
     * 奖项id
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String awardsId;

    /**
     * student_id
     * 学生id
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String studentId;

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

    //分持久字段
    private String studentName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getAwardsStudentId() {
        return awardsStudentId;
    }

    public void setAwardsStudentId(String awardsStudentId) {
        this.awardsStudentId = awardsStudentId;
    }

    public String getAwardsId() {
        return awardsId;
    }

    public void setAwardsId(String awardsId) {
        this.awardsId = awardsId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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