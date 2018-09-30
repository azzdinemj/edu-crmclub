package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.Notice;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.SystemBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeClient extends SystemBaseClient
        implements IFindClient<Notice,Response<List<Notice>>>,ISaveClient<Notice,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<Notice,Response<Notice>> {




    /**
     * 删除
     * @param pkId 主键
     * @return
     */
    @Override
    public String delete(String pkId) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),pkId);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 查询
     * @param notice
     * @return
     */
    @Override
    public Response<List<Notice>> find(Notice notice) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),notice);
        Response<List<Notice>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Notice>>>(){});
        return response;
    }

    /**
     *保存
     * @param notice
     * @return
     */
    @Override
    public String save(Notice notice){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),notice);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/notice";
    }

    /**
     * 主键查询
     * @param notice
     * @return
     */
    @Override
    public Response<Notice> findByPrimaryKey(Notice notice) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),notice);
        Response<Notice> response = JSON.parseObject(responseXml,new TypeReference<Response<Notice>>(){});
        return response;
    }
}
