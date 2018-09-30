package com.wuxue.view.client.classInfo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.ClassRoom;
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
public class ClassRoomClient extends ClassInfoBaseClient
        implements IFindClient<ClassRoom,Response<List<ClassRoom>>>,ISaveClient<ClassRoom,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<ClassRoom,Response<ClassRoom>> {




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
     * @param classRoom
     * @return
     */
    @Override
    public Response<List<ClassRoom>> find(ClassRoom classRoom) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),classRoom);
        Response<List<ClassRoom>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<ClassRoom>>>(){});
        return response;
    }

    /**
     *保存
     * @param classRoom
     * @return
     */
    @Override
    public String save(ClassRoom classRoom){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),classRoom);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/classRoom";
    }

    /**
     * 主键查询
     * @param classRoom
     * @return
     */
    @Override
    public Response<ClassRoom> findByPrimaryKey(ClassRoom classRoom) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),classRoom);
        Response<ClassRoom> response = JSON.parseObject(responseXml,new TypeReference<Response<ClassRoom>>(){});
        return response;
    }
}
