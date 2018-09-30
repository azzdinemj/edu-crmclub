package com.wuxue.view.utils;

import com.wuxue.model.Linkman;
import com.wuxue.model.StudentScores;

import java.util.List;

public class LinkmanList {

    private List<Linkman> man;

    private List<StudentScores> scores;

    public LinkmanList(List<Linkman> man) {
        this.man = man;
    }

    public LinkmanList() {
    }

    public List<Linkman> getMan() {
        return man;
    }

    public void setMan(List<Linkman> man) {
        this.man = man;
    }

    public List<StudentScores> getScores() {
        return scores;
    }

    public void setScores(List<StudentScores> scores) {
        this.scores = scores;
    }
}
