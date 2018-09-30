package com.wuxue.view.client.classInfo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.TeacherComment;
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
public class TeacherCommentClient extends ClassInfoBaseClient
        implements IFindClient<TeacherComment,Response<List<TeacherComment>>>,ISaveClient<TeacherComment,String>,
        IDeleteClient<String,Object>,IFindByPrimaryKeyClient<TeacherComment,Response<TeacherComment>> {




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
     * @param teacherComment
     * @return
     */
    @Override
    public Response<List<TeacherComment>> find(TeacherComment teacherComment) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),teacherComment);
        Response<List<TeacherComment>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<TeacherComment>>>(){});
        return response;
    }

    /**
     *保存
     * @param teacherComment
     * @return
     */
    @Override
    public String save(TeacherComment teacherComment){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),teacherComment);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/teacherComment";
    }

    /**
     * 主键查询
     * @param teacherComment
     * @return
     */
    @Override
    public Response<TeacherComment> findByPrimaryKey(TeacherComment teacherComment) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),teacherComment);
        Response<TeacherComment> response = JSON.parseObject(responseXml,new TypeReference<Response<TeacherComment>>(){});
        return response;
    }
}
