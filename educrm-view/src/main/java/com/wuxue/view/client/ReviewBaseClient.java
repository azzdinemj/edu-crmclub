package com.wuxue.view.client;

public abstract class ReviewBaseClient extends BaseClient {

    /**
     * 所属模块
     * @return
     */
    @Override
    protected String getModuleName() {
        return "/course";
    }
}
