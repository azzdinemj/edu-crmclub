package com.wuxue.api.controller.classInfo;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.ClassinfoActivity;
import com.wuxue.api.service.ClassinfoActivityService;
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
@RequestMapping(value = "api/classinfo/classinfoActivity")
public class ClassinfoActivityController implements IFindController<ClassinfoActivity>,
        ISaveController<ClassinfoActivity>,IDeleteController<String> {
    @Autowired
    private ClassinfoActivityService classinfoActivityService;

    @Override
    public Response Find(@RequestBody Request<ClassinfoActivity> classinfoActivity) {
        return classinfoActivityService.find(classinfoActivity);
    }

    @Override
    public Response Save(@RequestBody Request<ClassinfoActivity> classinfoActivity) {
        return classinfoActivityService.save(classinfoActivity);
    }
    @Override

    public Response Delete(@RequestBody Request<String> classinfoActivity) {
        return classinfoActivityService.delete(classinfoActivity);

    }
}
