package com.wuxue.view.client.course;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.OptionalCourse;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.CourseBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description:  课程评论
 * 访问api服务类
* @author wanghao
* @date  13:42 2018/3/13
* @version V1.0
*/
@Service
public class OptionalCourseClient extends CourseBaseClient implements IFindClient<OptionalCourse,Response<List<OptionalCourse>>>,ISaveClient<OptionalCourse,String>,
        IDeleteClient<OptionalCourse,Object>,IFindByPrimaryKeyClient<OptionalCourse,Response<OptionalCourse>> {

    /**
     * 查询
     * @param optionalCourse
     * @return
     */
    @Override
    public Response<List<OptionalCourse>> find(OptionalCourse optionalCourse) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),optionalCourse);
        Response<List<OptionalCourse>> response = JSON.parseObject(responseXml,new TypeReference<Response<List<OptionalCourse>>>(){});
        return response;
    }

    /**
     * 所属页面
     * @return
     */
    @Override
    protected String getPageName() { return "/optionalcourse"; }


    @Override
    public Object delete(OptionalCourse s) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),s);
//        Response<Object> response = JSON.parseObject(responseXml,new TypeReference<Response<Object>>(){});
        return responseXml;
    }

    @Override
    public Response<OptionalCourse> findByPrimaryKey(OptionalCourse optionalCourse) {
        return null;
    }



    @Override
    public String save(OptionalCourse optionalCourse) {
        return null;
    }
}

