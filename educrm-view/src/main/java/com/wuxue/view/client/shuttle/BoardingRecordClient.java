package com.wuxue.view.client.shuttle;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.shuttle.BoardingRecord;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.ShuttleBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardingRecordClient extends ShuttleBaseClient
        implements IFindClient<BoardingRecord,Response<List<BoardingRecord>>>,ISaveClient<BoardingRecord,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<BoardingRecord,Response<BoardingRecord>> {




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
     * @param department
     * @return
     */
    @Override
    public Response<List<BoardingRecord>> find(BoardingRecord department) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),department);
        Response<List<BoardingRecord>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<BoardingRecord>>>(){});
        return response;
    }

    /**
     *保存
     * @param department
     * @return
     */
    @Override
    public String save(BoardingRecord department){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),department);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/boardingRecord";
    }

    /**
     * 主键查询
     * @param department
     * @return
     */
    @Override
    public Response<BoardingRecord> findByPrimaryKey(BoardingRecord department) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),department);
        Response<BoardingRecord> response = JSON.parseObject(responseXml,new TypeReference<Response<BoardingRecord>>(){});
        return response;
    }
}
