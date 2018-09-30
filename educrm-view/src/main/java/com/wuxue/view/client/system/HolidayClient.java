package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.junhwa.Holiday;
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
public class HolidayClient extends SystemBaseClient
        implements IFindClient<Holiday,Response<List<Holiday>>>,ISaveClient<Holiday,String>,
        IDeleteClient<Integer,Object>,IFindByPrimaryKeyClient<Holiday,Response<Holiday>> {




    /**
     * 删除
     * @param pkId 主键
     * @return
     */
    @Override
    public String delete(Integer pkId) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),pkId);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 查询
     * @param holiday
     * @return
     */
    @Override
    public Response<List<Holiday>> find(Holiday holiday) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),holiday);
        Response<List<Holiday>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<Holiday>>>(){});
        return response;
    }

    /**
     *保存
     * @param holiday
     * @return
     */
    @Override
    public String save(Holiday holiday){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),holiday);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/holiday";
    }

    /**
     * 主键查询
     * @param holiday
     * @return
     */
    @Override
    public Response<Holiday> findByPrimaryKey(Holiday holiday) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),holiday);
        Response<Holiday> response = JSON.parseObject(responseXml,new TypeReference<Response<Holiday>>(){});
        return response;
    }

    public String saveAll(Holiday holiday) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVEALL),holiday);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;

    }
}
