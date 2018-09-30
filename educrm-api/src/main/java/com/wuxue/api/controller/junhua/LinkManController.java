package com.wuxue.api.controller.junhua;

import com.wuxue.api.service.LinkmanService;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 联系人
 * @author tly
 * @data 2018-08-06
 */
@RestController
@RequestMapping(value = "api/junhua/linkman")
public class LinkManController {
    @Autowired
    private LinkmanService linkmanService;

    /**
     * 找出学生的联系人们(家长和班主任信息)
     * @param request
     * @return
     */
    @RequestMapping(value = "/getStudentLinkMansInfo",method = RequestMethod.POST)
    public Response getStudentLinkMansInfo(@RequestBody  Request<String> request) {
        return linkmanService.getStudentLinkMansInfo(request);
    }
}
