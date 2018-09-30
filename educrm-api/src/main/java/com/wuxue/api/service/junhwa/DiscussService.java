package com.wuxue.api.service.junhwa;

import com.wuxue.base.ResultEntity;
import com.wuxue.model.junhwa.Discuss;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

/**
 * 留言板service
 * @author tly
 * @data 2018-08-13
 */
public interface DiscussService {
    /**
     * 家长留言
     * @param discuss
     * @return
     */
    Response parentLeaveMessage(Discuss discuss);

    /**
     * 老师回复
     * @param discuss
     * @return
     */
    Response theacherReply(Discuss discuss);

    /**
     * 程序端查询留言及回复列表
     * @param discuss
     * @return
     */
    Response getDiscussListForMobile(Discuss discuss);

    /**
     * PC端统计家长留言
     * @param entity
     * @return 学生家长留言列表
     */
    Response getDiscussCountListForPC(ResultEntity entity);

    /**
     * 获取家长最新一条留言记录
     * @param discuss
     * @return
     */
    Response getParentNewDiscuss(Discuss discuss);

    /**
     * 获取学生家长全部留言和回复记录
     * @param discuss
     * @return
     */
    Response getDiscussListForPC(Discuss discuss);

    Response find(Request<ResultEntity> tParams);
}
