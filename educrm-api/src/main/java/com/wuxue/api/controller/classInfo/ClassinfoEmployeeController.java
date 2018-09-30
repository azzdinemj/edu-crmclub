package com.wuxue.api.controller.classInfo;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.ClassinfoEmployeeService;
import com.wuxue.model.ClassinfoEmployee;
import com.wuxue.model.ClassinfoEmployeeKey;
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
@RequestMapping(value = "api/classinfo/classinfoEmployee")
public class ClassinfoEmployeeController implements IFindController<ClassinfoEmployee>,
        ISaveController<ClassinfoEmployee>,IDeleteController<ClassinfoEmployee> {
    @Autowired
    private ClassinfoEmployeeService classinfoEmployeeService;

    @Override
    public Response Find(@RequestBody Request<ClassinfoEmployee> classinfoEmployee) {
        return classinfoEmployeeService.find(classinfoEmployee);
    }

    @Override
    public Response Save(@RequestBody Request<ClassinfoEmployee> classinfoEmployee) {
        return classinfoEmployeeService.save(classinfoEmployee);
    }
    @Override
    public Response Delete(@RequestBody Request<ClassinfoEmployee> classinfoEmployee) {
        return classinfoEmployeeService.delete(classinfoEmployee);

    }

    @RequestMapping(value = "/saveall",method = RequestMethod.POST)
    public Response saveAll(@RequestBody Request<ClassinfoEmployee> classinfoEmployee) {
        return classinfoEmployeeService.saveAll(classinfoEmployee);

    }
    @RequestMapping(value = "/findteacher",method = RequestMethod.POST)
    public Response findTeacher(@RequestBody Request<ClassinfoEmployee> classinfoEmployee) {
        return classinfoEmployeeService.findTeacher(classinfoEmployee);

    }

    @RequestMapping(value = "/findclassinfoteacher",method = RequestMethod.POST)
    public Response findClassinfoTeacher(@RequestBody Request<ClassinfoEmployee> classinfoEmployee) {
        return classinfoEmployeeService.findClassinfoTeacher(classinfoEmployee);

    }
}
