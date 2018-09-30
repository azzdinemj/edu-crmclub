package com.wuxue.view.client.activity;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.junhwa.RaceRecord;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.ActivityBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RaceRecordClient extends ActivityBaseClient
        implements IFindClient<RaceRecord,Response<List<RaceRecord>>>,ISaveClient<RaceRecord,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<RaceRecord,Response<RaceRecord>>{




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
     * @param raceRecord
     * @return
     */
    @Override
    public Response<List<RaceRecord>> find(RaceRecord raceRecord) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDLIST),raceRecord);
        Response<List<RaceRecord>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<RaceRecord>>>(){});
        return response;
    }

    /**
     *保存
     * @param raceRecord
     * @return
     */
    @Override
    public String save(RaceRecord raceRecord){
        String responseXml = POST(getSendUrl(ActionEnum.SAVEORUPDATE),raceRecord);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/raceRecord";
    }

    /**
     * 主键查询
     * @param raceRecord
     * @return
     */
    public Response<RaceRecord> findRaceRecordInfoById(RaceRecord  raceRecord) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDINFO),raceRecord);
        Response<RaceRecord> response = JSON.parseObject(responseXml,new TypeReference<Response<RaceRecord>>(){});
        return response;
    }
    @Override
    public Response<RaceRecord> findByPrimaryKey(RaceRecord  raceRecordId) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDINFO),raceRecordId);
        Response<RaceRecord> response = JSON.parseObject(responseXml,new TypeReference<Response<RaceRecord>>(){});
        return response;
    }
}
