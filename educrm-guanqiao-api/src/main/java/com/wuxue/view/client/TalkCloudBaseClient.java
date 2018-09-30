package com.wuxue.view.client;

/**
 * 
* @Title: TalkCloudBaseClient.java  - 拓课云
* @Package com.wuxue.view.client   
* @author admin  
* @date 2018年5月30日
 */
public abstract class TalkCloudBaseClient extends BaseClient {

    /**
     * 所属模块
     * @return
     */
    @Override
    protected String getModuleName() {
        return "/talkCloud";
    }
}
