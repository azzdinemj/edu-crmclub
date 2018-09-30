package com.wuxue.view.client.course;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.wuxue.model.Course;
import com.wuxue.model.Review;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.CourseBaseClient;
import com.wuxue.view.enums.ActionEnum;
import com.wuxue.view.interfaces.IAuditClient;
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
public class ReviewClient extends CourseBaseClient implements IFindByPrimaryKeyClient<Review,Response<Review>>,IAuditClient<Review,Response<Review>> {

    /**
     * 查询
     * @param review
     * @return
     */
    public Response<PageInfo<Review>> find(Review review) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),review);
        Response<PageInfo<Review>> response = JSON.parseObject(responseXml,new TypeReference<Response<PageInfo<Review>>>(){});
        return response;
    }

    @Override
    public Response<Review> findByPrimaryKey(Review review) {
        String responseXml = POST(getSendUrl(ActionEnum.FIND),review);
        Response<Review> response = JSON.parseObject(responseXml,new TypeReference<Response<Review>>(){});
        return response;
    }

    /**
     * 所属页面
     * @return
     */
    @Override
    protected String getPageName() { return "/review"; }


    @Override
    public Response<Review> audit(Review review) {
        String responseXml = POST(getSendUrl(ActionEnum.AUDIT),review);
        Response<Review> response = JSON.parseObject(responseXml,new TypeReference<Response<Review>>(){});
        return response;
    }
}

