package com.wuxue.view.client.classInfo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.ClassTime;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.ClassInfoBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassTimeClient extends ClassInfoBaseClient
        implements IFindClient<ClassTime,Response<List<ClassTime>>>,ISaveClient<ClassTime,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<ClassTime,Response<ClassTime>> {




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
     * @param classTime
     * @return
     */
    @Override
    public Response<List<ClassTime>> find(ClassTime classTime) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),classTime);
        Response<List<ClassTime>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<ClassTime>>>(){});
        return response;
    }

    /**
     *保存
     * @param classTime
     * @return
     */
    @Override
    public String save(ClassTime classTime){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),classTime);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/classTime";
    }

    /**
     * 主键查询
     * @param classTime
     * @return
     */
    @Override
    public Response<ClassTime> findByPrimaryKey(ClassTime classTime) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),classTime);
        Response<ClassTime> response = JSON.parseObject(responseXml,new TypeReference<Response<ClassTime>>(){});
        return response;
    }
}
