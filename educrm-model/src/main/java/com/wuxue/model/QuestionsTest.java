package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class QuestionsTest extends BaiscPage {
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    private Integer type;
    private String id;

    private String pkStudent;

    private String pkQuestionsItem;

    public Integer getIstrue() {
        return istrue;
    }

    public void setIstrue(Integer istrue) {
        this.istrue = istrue;
    }

    private Integer istrue;

    private String answer;

    private String testid;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getPkQuestionsItem() {
        return pkQuestionsItem;
    }

    public void setPkQuestionsItem(String pkQuestionsItem) {
        this.pkQuestionsItem = pkQuestionsItem;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTestid() {
        return testid;
    }

    public void setTestid(String testid) {
        this.testid = testid;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}