package com.wuxue.model.junhwa;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * TABLE  shuttle_time
 * MyBatis Generator Create
 */
public class ShuttleTime implements Serializable {
    /**
     * pk_shuttle_time
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String pkShuttleTime;

    /**
     * pk_domain
     * 校区
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String pkDomain;

    /**
     * pk_classinfo
     * 班级
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String pkClassinfo;

    /**
     * date_type
     * 时间类型周一到周日
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Integer dateType;

    /**
     * start_time
     * 开始时间
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Date startTime;

    /**
     * end_time
     * 结束时间
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Date endTime;

    /**
     * shuttle_type
     * 状态 0校车接送  1家长接送 2住宿家长接送  3住宿校车接送
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Integer shuttleType;

    private static final long serialVersionUID = 1L;

    public String getPkShuttleTime() {
        return pkShuttleTime;
    }

    public void setPkShuttleTime(String pkShuttleTime) {
        this.pkShuttleTime = pkShuttleTime;
    }

    public String getPkDomain() {
        return pkDomain;
    }

    public void setPkDomain(String pkDomain) {
        this.pkDomain = pkDomain;
    }

    public String getPkClassinfo() {
        return pkClassinfo;
    }

    public void setPkClassinfo(String pkClassinfo) {
        this.pkClassinfo = pkClassinfo;
    }

    public Integer getDateType() {
        return dateType;
    }

    public void setDateType(Integer dateType) {
        this.dateType = dateType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getShuttleType() {
        return shuttleType;
    }

    public void setShuttleType(Integer shuttleType) {
        this.shuttleType = shuttleType;
    }
}