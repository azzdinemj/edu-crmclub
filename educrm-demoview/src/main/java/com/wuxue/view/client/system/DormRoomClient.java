package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.DormRoom;
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
public class DormRoomClient extends SystemBaseClient implements
        IFindClient<DormRoom,Response<List<DormRoom>>>,ISaveClient<DormRoom,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<DormRoom,Response<DormRoom>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/dormRoom";
    }

    /**
     * 删除
     * @param s
     * @return
     */
    @Override
    public String delete(String s) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),s);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 根据主键查询
     * @param dormRoom
     * @return
     */
    @Override
    public Response<DormRoom> findByPrimaryKey(DormRoom dormRoom) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),dormRoom);
        Response<DormRoom> response = JSON.parseObject(responseXml,new TypeReference<Response<DormRoom>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param dormRoom
     * @return
     */
    @Override
    public Response<List<DormRoom>> find(DormRoom dormRoom) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),dormRoom);
        Response<List<DormRoom>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<DormRoom>>>(){});
        return response;

    }

    /**
     *保存
     * @param dormRoom
     * @return
     */
    @Override
    public String save(DormRoom dormRoom){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),dormRoom);
       /* Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});*/
        return responseXml;
    }
}
