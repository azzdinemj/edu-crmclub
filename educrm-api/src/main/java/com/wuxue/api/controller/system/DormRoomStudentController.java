package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.*;
import com.wuxue.api.service.DormRoomStudentService;
import com.wuxue.model.DormRoomStudent;
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
@RequestMapping(value = "api/system/dormroomstudent")
public class DormRoomStudentController implements
        ISaveController<DormRoomStudent>,IDeleteController<DormRoomStudent>,IFindController<DormRoomStudent> {
    @Autowired
    private DormRoomStudentService dormRoomStudentService;


    @Override
    public Response Save(@RequestBody Request<DormRoomStudent> dormRoomStudent) {
        return dormRoomStudentService.save(dormRoomStudent);
    }
    @Override

    public Response Delete(@RequestBody Request<DormRoomStudent> dormRoomStudent) {
        return dormRoomStudentService.delete(dormRoomStudent);
    }


    @Override
    public Response Find(@RequestBody Request<DormRoomStudent> dormRoomStudent) {
        return dormRoomStudentService.find(dormRoomStudent);
    }
}
