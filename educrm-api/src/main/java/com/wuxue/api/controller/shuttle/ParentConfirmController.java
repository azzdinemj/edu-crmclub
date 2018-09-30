package com.wuxue.api.controller.shuttle;

import com.wuxue.api.service.junhwa.ParentConfirmService;
import com.wuxue.model.junhwa.ParentConfirm;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 家长确认
 *
 * @author tly
 * @date 2018/07/30
 */
@RestController
@RequestMapping(value = "api/shuttle/confirm")
public class ParentConfirmController {

    @Autowired
    private ParentConfirmService parentConfirmService;

    /**
     * 家长确认接到孩子
     * @param request 确认信息
     * @return
     */
    @RequestMapping(value = "/saveParentConfirmInfo",method = RequestMethod.POST)
    public Response saveParentConfirmInfo(@RequestBody Request<ParentConfirm> request){
        return parentConfirmService.saveParentConfirmInfo(request.getData());
    }

}
