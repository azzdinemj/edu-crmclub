package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

/**
 *
 * description: 产品（课程）收藏表
 * @auther: wh
 * @date: 2018/6/13 14:04
 */
public class TkProductMark extends BaiscPage{
    private String pkProductMark;

    private String pkStudent;

    private String pkProduct;

    private Date creationDate;

    public String getPkProductMark() {
        return pkProductMark;
    }

    public void setPkProductMark(String pkProductMark) {
        this.pkProductMark = pkProductMark;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getPkProduct() {
        return pkProduct;
    }

    public void setPkProduct(String pkProduct) {
        this.pkProduct = pkProduct;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}