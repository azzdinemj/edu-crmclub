package com.wuxue.api.controller.student;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.StudentSignup;
import com.wuxue.api.service.StudentSignupService;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/01/02.
 */
@RestController
@RequestMapping(value = "api/student/studentSignup")
public class StudentSignupController implements IFindController<StudentSignup>,
        ISaveController<StudentSignup>,IDeleteController<String>,ISubmitController<StudentSignup>,
        IAuditController<StudentSignup>,ICancelController<StudentSignup> {

    @Autowired
    private StudentSignupService studentSignupService;

    @Override
    public Response Find(@RequestBody Request<StudentSignup> studentSignup) {
        return studentSignupService.find(studentSignup);
    }

    @Override
    public Response Save(@RequestBody Request<StudentSignup> studentSignup) {
        return studentSignupService.save(studentSignup);
    }

    @Override
    public Response Delete(@RequestBody Request<String> studentSignup) {
        return studentSignupService.delete(studentSignup);

    }

    @Override
    public Response Audit(@RequestBody Request<StudentSignup> studentSignup) {
        return studentSignupService.audit(studentSignup);
    }

    @Override
    public Response Submit(@RequestBody Request<StudentSignup> studentSignup) {
        return studentSignupService.submit(studentSignup);
    }

    @RequestMapping(value = "/retreat",method = RequestMethod.POST)
    public Response Retreat(@RequestBody Request<StudentSignup> studentSignup){
        return studentSignupService.retreat(studentSignup);
    }

    @Override
    public Response Cancel(@RequestBody Request<StudentSignup> studentSignup) {
        return studentSignupService.cancel(studentSignup);
    }

    @RequestMapping(value = "/getnotplacement",method = RequestMethod.POST)
    public Response GetNotPlacement(@RequestBody Request<StudentSignup> studentSignup){
        return studentSignupService.getNotPlacement(studentSignup);
    }


    @RequestMapping(value = "/updatestatus",method = RequestMethod.POST)
    public Response updateStatus(@RequestBody Request<StudentSignup> studentSignup){
        return studentSignupService.updateStatus(studentSignup);
    }

    /**
     * 退学获取报名费用
     * @param studentSignup
     * @return
     */
    @RequestMapping(value = "/getcost",method = RequestMethod.POST)
    public Response getCost(@RequestBody Request<StudentSignup> studentSignup){
        return studentSignupService.getCost(studentSignup);
    }
}
