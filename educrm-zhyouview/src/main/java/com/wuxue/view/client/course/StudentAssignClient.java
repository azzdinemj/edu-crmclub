package com.wuxue.view.client.course;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.Course;
import com.wuxue.model.StudentAssign;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.CourseBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.stereotype.Service;

/**
* @Description:  学生个人课程
 * 访问api服务类
* @author wanghao
* @date  13:42 2018/3/13
* @version V1.0
*/
@Service
public class StudentAssignClient extends CourseBaseClient implements
        IFindClient<StudentAssign,Response>,ISaveClient<StudentAssign,String>{


    @Override
    public Response find(StudentAssign s) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),s);
        Response<PageInfo<StudentAssign>> response = JSON.parseObject(responseXml, new TypeReference<Response<PageInfo<StudentAssign>>>(){});
        return response;
    }

    /**
     * 所属页面
     * @return
     */
    @Override
    protected String getPageName() { return "/studentAssign"; }


    @Override
    public String save(StudentAssign studentAssign) {
        String responseXml = POST(getSendUrl(ActionEnum.SAVE),studentAssign);
        return responseXml;
    }

}

