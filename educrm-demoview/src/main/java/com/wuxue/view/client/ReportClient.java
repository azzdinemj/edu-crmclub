package com.wuxue.view.client;

public abstract class ReportClient extends BaseClient {

    /**
     * 所属模块
     * @return
     */
    @Override
    protected String getModuleName() {
        return "/report";
    }
}
