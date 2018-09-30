package com.wuxue.view.client.student;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.DropExpenseRecord;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.StudentBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DropExpenseRecordClient extends StudentBaseClient implements
        IFindClient<DropExpenseRecord,Response<List<DropExpenseRecord>>>,ISaveClient<DropExpenseRecord,String>,
        IDeleteClient<Long,Object>,IFindByPrimaryKeyClient<DropExpenseRecord,Response<DropExpenseRecord>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/dropExpenseRecord";
    }

    /**
     * 删除
     * @param s
     * @return
     */
    @Override
    public String delete(Long s) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),s);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 根据主键查询
     * @param dropExpenseRecord
     * @return
     */
    @Override
    public Response<DropExpenseRecord> findByPrimaryKey(DropExpenseRecord dropExpenseRecord) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),dropExpenseRecord);
        Response<DropExpenseRecord> response = JSON.parseObject(responseXml,new TypeReference<Response<DropExpenseRecord>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param dropExpenseRecord
     * @return
     */
    @Override
    public Response<List<DropExpenseRecord>> find(DropExpenseRecord dropExpenseRecord) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),dropExpenseRecord);
        Response<List<DropExpenseRecord>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<DropExpenseRecord>>>(){});
        return response;

    }

    /**
     *保存
     * @param dropExpenseRecord
     * @return
     */
    @Override
    public String save(DropExpenseRecord dropExpenseRecord){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),dropExpenseRecord);
       /* Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});*/
        return responseXml;
    }
}
