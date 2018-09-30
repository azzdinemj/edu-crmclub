package com.wuxue.utils.contract;

/**
 * Created by Jamie on 2018/3/11.
 */
public class Pager {
    /**
     * 当前页码
     */
    private int pageIndex = 1;
    /**
     * 每页显示多少个
     */
    private int pageSize = 10;
    /**
     * 多少页
     */
    private int totalPage;


    public int getPageIndex() {
        return pageIndex;
    }
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getTotalPage() {
        return totalPage;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
