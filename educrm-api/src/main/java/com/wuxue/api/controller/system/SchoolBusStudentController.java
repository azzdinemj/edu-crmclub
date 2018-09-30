package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.*;
import com.wuxue.api.service.SchoolBusStudentService;
import com.wuxue.model.SchoolBusStudent;
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
@RequestMapping(value = "api/system/schoolbusstudent")
public class SchoolBusStudentController implements IFindController<SchoolBusStudent>,
        ISaveController<SchoolBusStudent>,IDeleteController<SchoolBusStudent>,IFindAllController<SchoolBusStudent>,IAuditController<SchoolBusStudent> {
    @Autowired
    private SchoolBusStudentService schoolBusStudentService;

    @Override
    public Response Find(@RequestBody Request<SchoolBusStudent> schoolBusStudent) {
        return schoolBusStudentService.find(schoolBusStudent);
    }

    @Override
    public Response Save(@RequestBody Request<SchoolBusStudent> schoolBusStudent) {
        return schoolBusStudentService.save(schoolBusStudent);
    }
    @Override

    public Response Delete(@RequestBody Request<SchoolBusStudent> schoolBusStudent) {
        return schoolBusStudentService.delete(schoolBusStudent);
    }


    @Override
    public Response FindAll(@RequestBody Request<SchoolBusStudent> schoolBusStudent) {
        return schoolBusStudentService.findAll(schoolBusStudent);
    }

    @Override
    public Response Audit(@RequestBody Request<SchoolBusStudent> schoolBusStudent) {
        return schoolBusStudentService.audit(schoolBusStudent);
    }







}
