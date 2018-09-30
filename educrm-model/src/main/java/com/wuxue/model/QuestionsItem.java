package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class QuestionsItem extends BaiscPage {
    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    private String pkStudent;

    private String id;

    private Integer partnum;

    private String rank;

    private String pkSysDictValues;

    private String questionsSubjectId;

    private String questionsSubjectName;

    private Short type;

    private String tags;

    private String answer;

    private String analysis;

    private Integer iscoherent;

    private Integer pid;

    private Integer rightnum;

    private Integer wrongnum;

    private Integer finishnum;

    private Integer ischild;

    private Integer sort;

    private Date regdate;

    private Date modifydate;

    private Integer status;

    private Integer isdel;

    private String title;

    private String option;

    public String getPkSysDictValues() {
        return pkSysDictValues;
    }

    public void setPkSysDictValues(String pkSysDictValues) {
        this.pkSysDictValues = pkSysDictValues;
    }

    public String getQuestionsSubjectName() {
        return questionsSubjectName;
    }

    public void setQuestionsSubjectName(String questionsSubjectName) {
        this.questionsSubjectName = questionsSubjectName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPartnum() {
        return partnum;
    }

    public void setPartnum(Integer partnum) {
        this.partnum = partnum;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getQuestionsSubjectId() {
        return questionsSubjectId;
    }

    public void setQuestionsSubjectId(String questionsSubjectId) {
        this.questionsSubjectId = questionsSubjectId;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public Integer getIscoherent() {
        return iscoherent;
    }

    public void setIscoherent(Integer iscoherent) {
        this.iscoherent = iscoherent;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getRightnum() {
        return rightnum;
    }

    public void setRightnum(Integer rightnum) {
        this.rightnum = rightnum;
    }

    public Integer getWrongnum() {
        return wrongnum;
    }

    public void setWrongnum(Integer wrongnum) {
        this.wrongnum = wrongnum;
    }

    public Integer getFinishnum() {
        return finishnum;
    }

    public void setFinishnum(Integer finishnum) {
        this.finishnum = finishnum;
    }

    public Integer getIschild() {
        return ischild;
    }

    public void setIschild(Integer ischild) {
        this.ischild = ischild;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }
}