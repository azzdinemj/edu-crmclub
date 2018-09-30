package com.wuxue.view.client.student;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.StudentShift;
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
public class StudentShiftClient extends StudentBaseClient implements
        IFindClient<StudentShift,Response<List<StudentShift>>>,ISaveClient<StudentShift,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<StudentShift,Response<StudentShift>> {


    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/studentShift";
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
     * @param studentCredit
     * @return
     */
    @Override
    public Response<StudentShift> findByPrimaryKey(StudentShift studentCredit) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentCredit);
        Response<StudentShift> response = JSON.parseObject(responseXml,new TypeReference<Response<StudentShift>>(){});
        return response;
    }

    /**
     * 查询列表
     * @param studentCredit
     * @return
     */
    @Override
    public Response<List<StudentShift>> find(StudentShift studentCredit) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),studentCredit);
        Response<List<StudentShift>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<StudentShift>>>(){});
        return response;

    }

    /**
     *保存
     * @param studentCredit
     * @return
     */
    @Override
    public String save(StudentShift studentCredit){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),studentCredit);
       /* Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});*/
        return responseXml;
    }
}
