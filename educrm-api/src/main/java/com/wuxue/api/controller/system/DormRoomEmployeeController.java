package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.*;
import com.wuxue.api.service.DormRoomEmployeeService;
import com.wuxue.model.DormRoomEmployee;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/01/02.
 */
@RestController
@RequestMapping(value = "api/system/dormroomemployee")
public class DormRoomEmployeeController implements IFindController<DormRoomEmployee>,
        ISaveController<DormRoomEmployee>,IDeleteController<DormRoomEmployee> {
    @Autowired
    private DormRoomEmployeeService dormRoomEmployeeService;

    @Override
    public Response Find(@RequestBody Request<DormRoomEmployee> dormRoomEmployee) {
        return dormRoomEmployeeService.find(dormRoomEmployee);
    }

    @Override
    public Response Save(@RequestBody Request<DormRoomEmployee> dormRoomEmployee) {
        return dormRoomEmployeeService.save(dormRoomEmployee);
    }
    @Override

    public Response Delete(@RequestBody Request<DormRoomEmployee> dormRoomEmployee) {
        return dormRoomEmployeeService.delete(dormRoomEmployee);
    }



}
