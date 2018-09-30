package com.wuxue.view.client.classInfo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.CourseTeacher;
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
public class CourseTeacherClient extends ClassInfoBaseClient
        implements IFindClient<CourseTeacher,Response<List<CourseTeacher>>>,ISaveClient<CourseTeacher,String>,
        IDeleteClient<CourseTeacher,Object>,IFindByPrimaryKeyClient<CourseTeacher,Response<CourseTeacher>> {




    /**
     * 删除
     * @param pkId 主键
     * @return
     */
    @Override
    public String delete(CourseTeacher pkId) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),pkId);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 查询
     * @param courseTeacher
     * @return
     */
    @Override
    public Response<List<CourseTeacher>> find(CourseTeacher courseTeacher) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),courseTeacher);
        Response<List<CourseTeacher>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<CourseTeacher>>>(){});
        return response;
    }

    /**
     *保存
     * @param courseTeacher
     * @return
     */
    @Override
    public String save(CourseTeacher courseTeacher){
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),courseTeacher);
        //Response<Object> response = JSON.parseObject(responseXml, new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    /**
     * 所属页面名称
     * @return
     */
    @Override
    protected String getPageName() {
        return "/courseTeacher";
    }

    /**
     * 主键查询
     * @param courseTeacher
     * @return
     */
    @Override
    public Response<CourseTeacher> findByPrimaryKey(CourseTeacher courseTeacher) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),courseTeacher);
        Response<CourseTeacher> response = JSON.parseObject(responseXml,new TypeReference<Response<CourseTeacher>>(){});
        return response;
    }

    public Response<List<CourseTeacher>> getTeacherCourse(CourseTeacher courseTeacher) {
        String responseXml = POST(getSendUrl(ActionEnum.GETTEACHERCOURSE),courseTeacher);
        Response<List<CourseTeacher>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<CourseTeacher>>>(){});
        return response;
    }
}
