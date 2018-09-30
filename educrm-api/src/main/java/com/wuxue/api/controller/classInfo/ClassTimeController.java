package com.wuxue.api.controller.classInfo;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.ClassTime;
import com.wuxue.api.service.ClassTimeService;
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
@RequestMapping(value = "api/classinfo/classTime")
public class ClassTimeController implements IFindController<ClassTime>,
        ISaveController<ClassTime>,IDeleteController<String> {
    @Autowired
    private ClassTimeService classTimeService;

    @Override
    public Response Find(@RequestBody Request<ClassTime> classTime) {
        return classTimeService.find(classTime);
    }

    @Override
    public Response Save(@RequestBody Request<ClassTime> classTime) {
        return classTimeService.save(classTime);
    }
    @Override

    public Response Delete(@RequestBody Request<String> classTime) {
        return classTimeService.delete(classTime);

    }
}
