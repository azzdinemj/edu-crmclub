package com.wuxue.api.controller.classInfo;

import com.wuxue.api.interfaces.*;
import com.wuxue.api.service.ActivityStudentService;
import com.wuxue.model.ActivityStudent;
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
@RequestMapping(value = "api/classinfo/activityStudent")
public class ActivityStudentController implements IFindController<ActivityStudent>,
        ISaveController<ActivityStudent>,IDeleteController<String> {
    @Autowired
    private ActivityStudentService activityStudentService;

    @Override
    public Response Find(@RequestBody Request<ActivityStudent> activityStudent) {
        return activityStudentService.find(activityStudent);
    }

    @Override
    public Response Save(@RequestBody Request<ActivityStudent> activityStudent) {
        return activityStudentService.save(activityStudent);
    }

    @Override
    public Response Delete(@RequestBody Request<String> activityStudent) {
        return activityStudentService.delete(activityStudent);
    }
}
