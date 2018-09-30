package com.wuxue.view.client.course;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.Course;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.CourseBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IAuditClient;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

/**
* @Description:  课程
 * 访问api服务类
* @author wanghao
* @date  13:42 2018/3/13
* @version V1.0
*/
@Service
public class CourseClient extends CourseBaseClient implements
        IDeleteClient<String,String>,ISaveClient<Course,String>,
        IFindByPrimaryKeyClient<Course,Response<Course>>,IAuditClient<Course,String> {

    /**
     * 查询
     * @param course
     * @return
     */
    public Response<PageInfo<Course>> find(Course course) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),course);
        Response<PageInfo<Course>> response = JSON.parseObject(responseXml,new TypeReference<Response<PageInfo<Course>>>(){});
        return response;
    }

    @Override
    public Response<Course> findByPrimaryKey(Course course) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),course);
        Response<Course> response = JSON.parseObject(responseXml,new TypeReference<Response<Course>>(){});
        return response;
    }
    /**
     * 删除
     * @param s
     * @return
     */
    @Override
    public String delete(String s) {
        String responseXml = POST(getSendUrl(ActionEnum.DELETE),s);
      //  Response response = JSON.parseObject(responseXml,new TypeReference<Response>(){});
        return responseXml;
    }

    @Override
    public String save(Course course) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),course);
        return responseXml;
    }


    //添加审核对象
    @Override
    public String audit(Course course) {
        String responseXml = POST(getSendUrl(ActionEnum.AUDIT),course);
        return responseXml;
    }

    /**
     * 所属页面
     * @return
     */
    @Override
    protected String getPageName() { return "/course"; }



}

