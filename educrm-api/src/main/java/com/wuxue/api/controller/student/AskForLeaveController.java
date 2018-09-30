package com.wuxue.api.controller.student;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.AskForLeaveService;
import com.wuxue.model.AskForLeave;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 学生、老师请假管理
 */
@RestController
@RequestMapping(value = "api/student/askForLeave")
public class AskForLeaveController implements IFindController<AskForLeave>,
        ISaveController<AskForLeave>,IDeleteController<String> {

    @Autowired
    AskForLeaveService askForLeaveService;

   @Override
    public Response Find(@RequestBody Request<AskForLeave> askForLeaveRequest) {
        return askForLeaveService.find(askForLeaveRequest);
    }

    @Override
    public Response Save(@RequestBody Request<AskForLeave> askForLeaveRequest) {
        return askForLeaveService.save(askForLeaveRequest);
    }

    @Override
    public Response Delete(@RequestBody Request<String> stringRequest) {
        return null;
    }
}