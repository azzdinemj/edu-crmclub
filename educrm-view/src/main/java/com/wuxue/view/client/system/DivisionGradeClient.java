package com.wuxue.view.client.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.DivisionGrade;
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
public class DivisionGradeClient extends SystemBaseClient
        implements IFindClient<DivisionGrade,Response<List<DivisionGrade>>>,ISaveClient<DivisionGrade,String>,
        IDeleteClient<Integer,Object>,IFindByPrimaryKeyClient<DivisionGrade,Response<DivisionGrade>> {




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
     * @param divisionGrade
     * @return
     */
    @Override
    public Response<List<DivisionGrade>> find(DivisionGrade divisionGrade) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),divisionGrade);
        Response<List<DivisionGrade>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<DivisionGrade>>>(){});
        return response;
    }

    /**
     *保存
     * @param divisionGrade
     * @return
     */
    @Override
    public String save(DivisionGrade divisionGrade){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),divisionGrade);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/divisionGrade";
    }

    /**
     * 主键查询
     * @param divisionGrade
     * @return
     */
    @Override
    public Response<DivisionGrade> findByPrimaryKey(DivisionGrade divisionGrade) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),divisionGrade);
        Response<DivisionGrade> response = JSON.parseObject(responseXml,new TypeReference<Response<DivisionGrade>>(){});
        return response;
    }
}
