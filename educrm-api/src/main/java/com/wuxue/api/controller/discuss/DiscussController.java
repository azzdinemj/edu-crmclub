package com.wuxue.api.controller.discuss;

import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.service.junhwa.DiscussService;
import com.wuxue.base.ResultEntity;
import com.wuxue.model.junhwa.Discuss;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 留言板
 *
 * @author tly
 * @date 2018-08-12
 */
@RestController
@RequestMapping(value = "api/discuss/discuss")
public class DiscussController implements IFindController<ResultEntity>{
    @Autowired
    private DiscussService discussService;

    @Override
    public Response Find(@RequestBody Request<ResultEntity> tParams) {
        return discussService.find(tParams);
    }

    /**
     * 家長留言
     *
     * @param discuss 留言bean
     * @return
     */
    @RequestMapping(value = "/parentLeaveMessage", method = RequestMethod.POST)
    public Response parentLeaveMessage(@RequestBody Discuss discuss) {
        return discussService.parentLeaveMessage(discuss);
    }

    /**
     * 老师回复
     *
     * @param request 留言bean
     * @return
     */
    @RequestMapping(value = "/theacherreply", method = RequestMethod.POST)
    public Response theacherReply(@RequestBody Request<Discuss> request) {
        return discussService.theacherReply(request.getData());
    }

    /**
     * 小程序端查询留言及回复列表(条件：家长id,学生id,类型)
     *
     * @param discuss
     * @return
     */
    @RequestMapping(value = "/getDiscussListForMobile", method = RequestMethod.POST)
    public Response getDiscussListForMobile(@RequestBody Discuss discuss) {
        return discussService.getDiscussListForMobile(discuss);
    }

    /**
     * PC端统计学生家长留言记录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getdiscusscountlistforpc", method = RequestMethod.POST)
    public Response getDiscussCountListForPC(@RequestBody Request<ResultEntity> request) {
        return discussService.getDiscussCountListForPC(request.getData());
    }

    /**
     * 查询家长最新一条留言记录
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getParentNewDiscuss", method = RequestMethod.POST)
    public Response getParentNewDiscuss(@RequestBody Request<Discuss> request) {
        return discussService.getParentNewDiscuss(request.getData());
    }

    /**
     * PC端查询留言及回复列表
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getdiscusslistforpc", method = RequestMethod.POST)
    public Response getDiscussListForPC(@RequestBody Request<Discuss> request) {

        return discussService.getDiscussListForPC(request.getData());
    }


}
