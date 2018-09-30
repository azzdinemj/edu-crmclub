package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.SchoolBusStudentNumService;
import com.wuxue.model.SchoolBusStudentNum;
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
@RequestMapping(value = "api/system/schoolbusstudentnum")
public class SchoolBusStudentNumController implements IFindController<SchoolBusStudentNum>,
        ISaveController<SchoolBusStudentNum>{
    @Autowired
    private SchoolBusStudentNumService schoolBusStudentNumService;

    @Override
    public Response Find(@RequestBody Request<SchoolBusStudentNum> schoolBusStudentNum) {
        return schoolBusStudentNumService.find(schoolBusStudentNum);
    }

    @Override
    public Response Save(@RequestBody Request<SchoolBusStudentNum> schoolBusStudentNum) {
        return schoolBusStudentNumService.save(schoolBusStudentNum);
    }

   
}
