package com.wuxue.api.controller.smallroutine.client;

public abstract class EmployeeBaseClient extends BaseClient {

    /**
     * 所属模块
     * @return
     */
    @Override
    protected String getModuleName() {
        return "/system";
    }
}
