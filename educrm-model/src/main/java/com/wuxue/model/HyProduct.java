package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class HyProduct extends BaiscPage {
    private Integer id;

    private Integer price;

    private String creator;

    private Date creatorDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreatorDate() {
        return creatorDate;
    }

    public void setCreatorDate(Date creatorDate) {
        this.creatorDate = creatorDate;
    }
}