package com.wuxue.model;

import com.wuxue.base.Page;

public class MarketReport extends Page{
    private String program;

    private String caption;

    private Long mm;

    private Long gg;

    private Long numbers;

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Long getMm() {
        return mm;
    }

    public void setMm(Long mm) {
        this.mm = mm;
    }

    public Long getGg() {
        return gg;
    }

    public void setGg(Long gg) {
        this.gg = gg;
    }

    public Long getNumbers() {
        return numbers;
    }

    public void setNumbers(Long numbers) {
        this.numbers = numbers;
    }
}