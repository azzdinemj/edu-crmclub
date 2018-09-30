package com.wuxue.model.junhwa;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * TABLE  t_activity_img
 * MyBatis Generator Create
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ActivityImg implements Serializable {
    /**
     * activity_img_id
     * 活动图片主键
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String activityImgId;

    /**
     * type
     * 类型（班级活动：0 、竞赛：1）
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Integer type;

    /**
     * img_url
     * 图片url
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String imgUrl;

    /**
     * img_order
     * 图片顺序
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Integer imgOrder;

    /**
     * rel_id
     * 关联id(竞赛id or 班级活动id)
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String relId;

    /**
     * creation_date
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Date creationDate;

    /**
     * creator
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String creator;

    /**
     * lastedit_date
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Date lasteditDate;

    /**
     * modifier
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String modifier;

    private static final long serialVersionUID = 1L;

    public String getActivityImgId() {
        return activityImgId;
    }

    public void setActivityImgId(String activityImgId) {
        this.activityImgId = activityImgId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getImgOrder() {
        return imgOrder;
    }

    public void setImgOrder(Integer imgOrder) {
        this.imgOrder = imgOrder;
    }

    public String getRelId() {
        return relId;
    }

    public void setRelId(String relId) {
        this.relId = relId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getLasteditDate() {
        return lasteditDate;
    }

    public void setLasteditDate(Date lasteditDate) {
        this.lasteditDate = lasteditDate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}