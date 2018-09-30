package com.wuxue.api.controller.smallroutine.client;

public abstract class WxOpenidBaseClient extends BaseClient {

    /**
     * 所属模块
     * @return
     */
    @Override
    protected String getModuleName() {
        return "/wxopenid";
    }
}
