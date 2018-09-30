package com.wuxue.api.controller.heyun;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.HyLinkmanService;
import com.wuxue.model.HyLinkman;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 联系人
 */
@RestController
@RequestMapping(value = "api/heyun/hyLinkman")
public class HyLinkmanController implements IFindController<HyLinkman>,
        ISaveController<HyLinkman>,IDeleteController<Integer> {

    @Autowired
    HyLinkmanService hyLinkmanService;

   @Override
    public Response Find(@RequestBody Request<HyLinkman> askForLeaveRequest) {
        return hyLinkmanService.find(askForLeaveRequest);
    }

    @Override
    public Response Save(@RequestBody Request<HyLinkman> askForLeaveRequest) {
        return hyLinkmanService.save(askForLeaveRequest);
    }

    @Override
    public Response Delete(@RequestBody Request<Integer> stringRequest) {
        return null;
    }
}