package com.wuxue.model;

import com.wuxue.model.shuttle.SchoolbusLine;

import java.util.List;

public class LinkmanList {

    private List<Linkman> man;

    private List<StudentScores> scores;

    private List<String> ids;

    private List<SchoolbusLine> lines;

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

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public List<SchoolbusLine> getLines() {
        return lines;
    }

    public void setLines(List<SchoolbusLine> lines) {
        this.lines = lines;
    }
}
