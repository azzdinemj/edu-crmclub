package com.wuxue.view.client.shuttle;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.shuttle.SchoolbusLine;
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
public class SchoolbusLineClient extends ShuttleBaseClient
        implements IFindClient<SchoolbusLine,Response<List<SchoolbusLine>>>,ISaveClient<SchoolbusLine,String>,
        IDeleteClient<SchoolbusLine,Object>,IFindByPrimaryKeyClient<SchoolbusLine,Response<SchoolbusLine>> {




    /**
     * 删除
     * @param schoolbusLine
     * @return
     */
    @Override
    public String delete(SchoolbusLine schoolbusLine) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),schoolbusLine);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 查询
     * @param department
     * @return
     */
    @Override
    public Response<List<SchoolbusLine>> find(SchoolbusLine department) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),department);
        Response<List<SchoolbusLine>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<SchoolbusLine>>>(){});
        return response;
    }

    /**
     *保存所有站点
     * @param lines
     * @return
     */
    public String saveAll(List<SchoolbusLine> lines){
        String responseXml = POST(getSendUrl(ActionEnum.SAVEALL),lines);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 保存单个站点
     * @param schoolbusLine
     * @return
     */
    public String save(SchoolbusLine schoolbusLine){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),schoolbusLine);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/schoolBusLine";
    }

    /**
     * 主键查询
     * @param department
     * @return
     */
    @Override
    public Response<SchoolbusLine> findByPrimaryKey(SchoolbusLine department) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),department);
        Response<SchoolbusLine> response = JSON.parseObject(responseXml,new TypeReference<Response<SchoolbusLine>>(){});
        return response;
    }

    public Response<List<SchoolbusLine>> findByBusId(SchoolbusLine schoolbusLine) {
        String responseXml = POST(getSendUrl(ActionEnum.FINDBYBUSID),schoolbusLine);
        Response<List<SchoolbusLine>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<SchoolbusLine>>>(){});
        return response;
    }

    public Response reverseLines(SchoolbusLine schoolbusLine) {
        String responseXml = POST(getSendUrl(ActionEnum.REVERSELINES),schoolbusLine);
        Response<List<SchoolbusLine>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<SchoolbusLine>>>(){});
        return response;
    }
}
