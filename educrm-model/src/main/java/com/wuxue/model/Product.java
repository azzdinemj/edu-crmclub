package com.wuxue.model;

import com.wuxue.base.BusinessPage;

import java.util.Date;

public class Product extends BusinessPage {
    private String pkProduct;

    private String pkDomain;

    private String subject;

    private String caption;

    private String notes;

    private Date comfirmDate;

    private String creator;

    private Date creationDate;

    private String modifier;

    private Date lasteditDate;

    private Integer type;

    private Integer status;

    private Integer isdel;

    private Integer productNum;      //课程（产品）人数
    private String  productType;     //课程类型
    private String  productLevel;    //课程等级
    private Integer  productTotalclass;    //总节数
    private Integer  productTypetalkcloudroom; // 0 一对一 3 一对多

    public Integer getProductClasshours() {
        return productClasshours;
    }

    public void setProductClasshours(Integer productClasshours) {
        this.productClasshours = productClasshours;
    }

    private Integer  productClasshours; //总课时

    private String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    private String memo;




    public Integer getProductTypetalkcloudroom() {
        return productTypetalkcloudroom;
    }

    public void setProductTypetalkcloudroom(Integer productTypetalkcloudroom) {
        this.productTypetalkcloudroom = productTypetalkcloudroom;
    }

    public Integer getProductTotalclass() {
        return productTotalclass;
    }

    public void setProductTotalclass(Integer productTotalclass) {
        this.productTotalclass = productTotalclass;
    }

    public String getProductLevel() {
        return productLevel;
    }

    public void setProductLevel(String productLevel) {
        this.productLevel = productLevel;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }


    public String getPkProduct() {
        return pkProduct;
    }

    public void setPkProduct(String pkProduct) {
        this.pkProduct = pkProduct;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getComfirmDate() {
        return comfirmDate;
    }

    public void setComfirmDate(Date comfirmDate) {
        this.comfirmDate = comfirmDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getLasteditDate() {
        return lasteditDate;
    }

    public void setLasteditDate(Date lasteditDate) {
        this.lasteditDate = lasteditDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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