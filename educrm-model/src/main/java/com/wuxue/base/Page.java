package com.wuxue.base;

import java.util.HashMap;
import java.util.Map;

public class Page {

    private Map<String, Object> map = new HashMap<>();

    public Map<String,Object> getMap(){
        return map;
    }

    public Object put(String key,Object value){
        return map.put(key,value);
    }
    public Object get(String key){
        return map.get(key);
    }

    // 每页显示条数
    private Integer pageSize=10;
    // 页码 ，从1开始
    private Integer pageNo=1;
    // 当前页的其实记录数，从0开始
    private Integer start;
    //总记录数
    private Integer itemsCount = 0;

//    一卡通
    private Integer count;
    private Integer page;
    private Integer totalCount;
    private Integer totalPage;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(Integer itemsCount) {
        this.itemsCount = itemsCount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}