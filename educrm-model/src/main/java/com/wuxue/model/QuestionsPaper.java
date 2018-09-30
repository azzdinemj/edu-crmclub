package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class QuestionsPaper extends BaiscPage {
    private String id;

    private String type;

    private Integer projectId;

    private String questionsSubjectId;

    private String title;

    private String explain;

    private Integer takes;

    private String year;

    private Float score;

    private Integer passscore;

    private Date regdate;

    private Date modifydate;

    private Integer status;

    private Integer version;

    private Integer isdel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getQuestionsSubjectId() {
        return questionsSubjectId;
    }

    public void setQuestionsSubjectId(String questionsSubjectId) {
        this.questionsSubjectId = questionsSubjectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public Integer getTakes() {
        return takes;
    }

    public void setTakes(Integer takes) {
        this.takes = takes;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getPassscore() {
        return passscore;
    }

    public void setPassscore(Integer passscore) {
        this.passscore = passscore;
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public Date getModifydate() {
        return modifydate;
    }

    public void setModifydate(Date modifydate) {
        this.modifydate = modifydate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }
}