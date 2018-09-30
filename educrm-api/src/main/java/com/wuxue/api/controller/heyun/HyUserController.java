package com.wuxue.api.controller.heyun;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.HyUserService;
import com.wuxue.model.HyOrder;
import com.wuxue.model.HyUser;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 出行人
 */
@RestController
@RequestMapping(value = "api/heyun/hyUser")
public class HyUserController implements IFindController<HyUser>,
        ISaveController<HyUser>,IDeleteController<Integer> {

    @Autowired
    HyUserService hyUserService;

   @Override
    public Response Find(@RequestBody Request<HyUser> askForLeaveRequest) {
        return hyUserService.find(askForLeaveRequest);
    }

    @Override
    public Response Save(@RequestBody Request<HyUser> askForLeaveRequest) {
        return hyUserService.save(askForLeaveRequest);
    }

    @Override
    public Response Delete(@RequestBody Request<Integer> stringRequest) {
        return null;
    }

    @RequestMapping("/countby")
    public Response CoutBy(@RequestBody Request<HyUser> askForLeaveRequest){
        return hyUserService.countBy(askForLeaveRequest);
    }
}