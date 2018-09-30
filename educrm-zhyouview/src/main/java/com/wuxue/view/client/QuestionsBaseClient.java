package com.wuxue.view.client;

import com.wuxue.view.client.BaseClient;

public abstract class QuestionsBaseClient extends BaseClient {

    /**
     * 所属模块
     * @return
     */
    @Override
    protected String getModuleName() {
        return "/course";
    }
}
