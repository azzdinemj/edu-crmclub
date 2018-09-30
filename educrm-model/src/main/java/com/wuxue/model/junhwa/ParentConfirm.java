package com.wuxue.model.junhwa;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * TABLE  parentconfirm
 * MyBatis Generator Create
 */
public class ParentConfirm implements Serializable {
    /**
     * pk_confirm
     * 主键
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String pkConfirm;

    /**
     * pk_linkman
     * 家长主键
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String pkLinkman;

    /**
     * pk_debus_record
     * 下车记录主键
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String pkDebusRecord;

    /**
     * pk_school_bus
     * 车号
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String pkSchoolBus;

    /**
     * pk_student
     * 学生
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */

    private String pkStudent;

    /**
     * status
     * 0 未确认 1 确认
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Integer status;

    /**
     * creation_date
     * 创建时间
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Date creationDate;

    private static final long serialVersionUID = 1L;

    public String getPkConfirm() {
        return pkConfirm;
    }

    public void setPkConfirm(String pkConfirm) {
        this.pkConfirm = pkConfirm;
    }

    public String getPkLinkman() {
        return pkLinkman;
    }

    public void setPkLinkman(String pkLinkman) {
        this.pkLinkman = pkLinkman;
    }

    public String getPkDebusRecord() {
        return pkDebusRecord;
    }

    public void setPkDebusRecord(String pkDebusRecord) {
        this.pkDebusRecord = pkDebusRecord;
    }

    public String getPkSchoolBus() {
        return pkSchoolBus;
    }

    public void setPkSchoolBus(String pkSchoolBus) {
        this.pkSchoolBus = pkSchoolBus;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}