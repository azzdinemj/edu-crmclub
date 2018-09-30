package com.wuxue.model;

import com.wuxue.base.BusinessPage;

public class StudentInterviewRecords extends BusinessPage {
    private String pkStudentTestPlansScores;

    private String pkStudentInterviewRecords;

    private String questions;

    private String answer;

    private String notes;

    public String getPkStudentTestPlansScores() {
        return pkStudentTestPlansScores;
    }

    public void setPkStudentTestPlansScores(String pkStudentTestPlansScores) {
        this.pkStudentTestPlansScores = pkStudentTestPlansScores;
    }

    public String getPkStudentInterviewRecords() {
        return pkStudentInterviewRecords;
    }

    public void setPkStudentInterviewRecords(String pkStudentInterviewRecords) {
        this.pkStudentInterviewRecords = pkStudentInterviewRecords;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}