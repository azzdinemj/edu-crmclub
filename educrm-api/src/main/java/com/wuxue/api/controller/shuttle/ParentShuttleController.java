package com.wuxue.api.controller.shuttle;

import com.wuxue.api.service.junhwa.ParentShuttleService;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 放学时间
 *
 * @author tly
 * @date 2018/07/30
 */
@RestController
@RequestMapping(value = "api/shuttle/time")
public class ParentShuttleController {

    @Autowired
    private ParentShuttleService parentShuttleService;

    /**
     * 获取学生放学时间
     * @param request
     * @return
     */
    @RequestMapping(value = "/getStudentHomeTime",method = RequestMethod.POST)
    public Response getStudentHomeTime(@RequestBody  Request<String> request) {
        return parentShuttleService.getStudentHomeTime(request);
    }

}
