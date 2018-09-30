package com.wuxue;


import com.wuxue.view.client.BaseClient;

public abstract class DiscussBaseClient extends BaseClient {


    @Override
    protected String getModuleName() {
        return "/discuss";
    }
}
