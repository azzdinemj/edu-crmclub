package com.wuxue.utils.contract;


public class Request<T>{
    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private Head head;
    private T data;

    private String currentUser;

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    private String currendDomain;

    public String getCurrendDomain() {
        return currendDomain;
    }

    public void setCurrendDomain(String currendDomain) {
        this.currendDomain = currendDomain;
    }

    /*************************************       分页start      **********************************/

        // 每页显示条数
        private Integer pageSize;
        // 页码 ，从1开始
        private Integer pageNo;
        // 当前页的其实记录数，从0开始
        private Integer start;
        //总记录数
        private Integer itemsCount = 0;

    public static final int DEFAULT_PAGESIZE = 20;
    private static final int DEFAULT_PAGENO = 1;

    public Request() {
        this.pageSize = DEFAULT_PAGESIZE;
        this.pageNo = DEFAULT_PAGENO;
        this.start = (this.pageNo - 1) * this.pageSize;

    }
    public Request(Integer itemsCount) {
        this.pageSize = DEFAULT_PAGESIZE;
        this.pageNo = DEFAULT_PAGENO;
        this.start = (this.pageNo - 1) * this.pageSize;
        this.itemsCount = itemsCount;
    }

    public Integer getPageSize() {
        return this.pageSize;
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
        this.start = (this.pageNo - 1) * this.pageSize;
        return start;
    }

    public Integer getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(Integer itemsCount) {
        this.itemsCount = itemsCount;
    }

    public void changeItemsCount(int itemsCount) {

        if (this.itemsCount > itemsCount) {
            // reset to pageNo
            this.pageNo = DEFAULT_PAGENO;
        }
        this.setItemsCount(itemsCount);

    }

    public Integer getPageCount() {
        if(this.itemsCount > 2){
            Integer recordCount = this.itemsCount / this.pageSize;
            if(this.itemsCount % this.pageSize == 0){
                return recordCount;
            } else {
                return recordCount + 1;
            }
        } else {
            return 1;
        }

    }


    /*************************************       分页end      **********************************/
}
