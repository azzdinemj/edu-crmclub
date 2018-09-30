package com.wuxue.model;

import com.wuxue.base.BaiscPage;

public class JhAnswerdetail extends BaiscPage {
    private String pkAnswerdetail;

    private String pkAnswer;

    private String pkExamination;

    private String pkQuestion;

    private String option;

    private String other;

    public String getPkAnswerdetail() {
        return pkAnswerdetail;
    }

    public void setPkAnswerdetail(String pkAnswerdetail) {
        this.pkAnswerdetail = pkAnswerdetail;
    }

    public String getPkAnswer() {
        return pkAnswer;
    }

    public void setPkAnswer(String pkAnswer) {
        this.pkAnswer = pkAnswer;
    }

    public String getPkExamination() {
        return pkExamination;
    }

    public void setPkExamination(String pkExamination) {
        this.pkExamination = pkExamination;
    }

    public String getPkQuestion() {
        return pkQuestion;
    }

    public void setPkQuestion(String pkQuestion) {
        this.pkQuestion = pkQuestion;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}