package com.wuxue.view.client;

public abstract class CourseBaseClient extends BaseClient {

    /**
     * 所属模块
     * @return
     */
    @Override
    protected String getModuleName() {
        return "/course";
    }
}
