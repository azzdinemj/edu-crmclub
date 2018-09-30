package com.wuxue.api.controller.classInfo;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.ClassinfoStudent;
import com.wuxue.api.service.ClassinfoStudentService;
import com.wuxue.model.ClassinfoStudentKey;
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
@RequestMapping(value = "api/classinfo/classinfoStudent")
public class ClassinfoStudentController implements IFindController<ClassinfoStudent>,
        ISaveController<ClassinfoStudent>,IDeleteController<ClassinfoStudentKey> {
    @Autowired
    private ClassinfoStudentService classinfoStudentService;

    @Override
    public Response Find(@RequestBody Request<ClassinfoStudent> classinfoStudent) {
        return classinfoStudentService.find(classinfoStudent);
    }

    @Override
    public Response Save(@RequestBody Request<ClassinfoStudent> classinfoStudent) {
        return classinfoStudentService.save(classinfoStudent);
    }
    @Override

    public Response Delete(@RequestBody Request<ClassinfoStudentKey> classinfoStudent) {
        return classinfoStudentService.delete(classinfoStudent);

    }
    @RequestMapping(value = "deletebyclassandstu",method = RequestMethod.POST)
    public Response deleteByClassAndStu(@RequestBody Request<String> pkClassinfo) {
        return classinfoStudentService.deleteByClassAndStu(pkClassinfo);

    }

    @RequestMapping(value = "/studentreturnclass",method = RequestMethod.POST)
    public Response StudentReturnClass(@RequestBody Request<ClassinfoStudent> classinfoStudent) {
        return classinfoStudentService.studentReturnClass(classinfoStudent);
    }
}
