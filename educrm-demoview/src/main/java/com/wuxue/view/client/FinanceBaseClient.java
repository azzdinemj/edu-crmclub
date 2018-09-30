package com.wuxue.view.client;

public abstract class FinanceBaseClient extends BaseClient {

    /**
     * 所属模块
     * @return
     */
    @Override
    protected String getModuleName() {
        return "/finance";
    }
}
