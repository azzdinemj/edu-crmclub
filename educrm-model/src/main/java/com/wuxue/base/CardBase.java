package com.wuxue.base;

import lombok.Data;

/**
 * 微信部门
 */
@Data
public class CardBase extends Page{

    //账号
    private Integer a_Accounts;

    //工作站号
    private Integer w_ID;

    //机号
    private Integer pos_ID;

    //页数
    private Integer page;

    //每页大小  最大100
    private Integer pageSize = 100;

    //开始时间     格式：yyyy-MM-dd HH:mm:ss
    private String beginDate;

    //结束时间     格式：yyyy-MM-dd HH:mm:ss
    private String endDate;

    public Integer getA_Accounts() {
        return a_Accounts;
    }

    public void setA_Accounts(Integer a_Accounts) {
        this.a_Accounts = a_Accounts;
    }

    public Integer getW_ID() {
        return w_ID;
    }

    public void setW_ID(Integer w_ID) {
        this.w_ID = w_ID;
    }

    public Integer getPos_ID() {
        return pos_ID;
    }

    public void setPos_ID(Integer pos_ID) {
        this.pos_ID = pos_ID;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
