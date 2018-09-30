package com.wuxue.view.client.course;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.Course;
import com.wuxue.model.CourseEvaluate;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.CourseBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IDeleteClient;
import com.wuxue.view.interfaces.IFindByPrimaryKeyClient;
import com.wuxue.view.interfaces.IFindClient;
import com.wuxue.view.interfaces.ISaveClient;
import org.springframework.stereotype.Service;

/**
* @Description:  课程评论
 * 访问api服务类
* @author wanghao
* @date  13:42 2018/3/13
* @version V1.0
*/
@Service
public class CourseTalkClient extends CourseBaseClient {

    /**
     * 查询
     * @param courseEvaluate
     * @return
     */
    public Response<PageInfo<CourseEvaluate>> find(CourseEvaluate courseEvaluate) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),courseEvaluate);
        Response<PageInfo<CourseEvaluate>> response = JSON.parseObject(responseXml,new TypeReference<Response<PageInfo<CourseEvaluate>>>(){});
        return response;
    }

    /**
     * 所属页面
     * @return
     */
    @Override
    protected String getPageName() { return "/courseEvaluate"; }


}

