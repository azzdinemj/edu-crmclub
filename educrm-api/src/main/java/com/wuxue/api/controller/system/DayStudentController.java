package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.DayStudentService;
import com.wuxue.model.junhwa.DayStudent;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/01/03.
 */
@RestController
@RequestMapping(value = "api/system/dayStudent")
public class DayStudentController implements IFindController<DayStudent>,
        ISaveController<DayStudent>,IDeleteController<Integer>{
    @Autowired
    private DayStudentService dayStudentService;

    @Override
    public Response Find(@RequestBody Request<DayStudent> dayStudent) {
        return dayStudentService.find(dayStudent);
    }

    @Override
    public Response Save(@RequestBody Request<DayStudent> dayStudent) {
        return dayStudentService.save(dayStudent);
    }

    @RequestMapping(value = "/saveall",method = RequestMethod.POST)
    public Response saveAll(@RequestBody Request<DayStudent> dayStudent) {
        return dayStudentService.saveAll(dayStudent);
    }

    @Override
    public Response Delete(@RequestBody Request<Integer> dayStudent) {
        return dayStudentService.delete(dayStudent);
    }
}
