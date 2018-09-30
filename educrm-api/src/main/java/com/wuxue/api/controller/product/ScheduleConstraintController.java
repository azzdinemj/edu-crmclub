package com.wuxue.api.controller.product;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.ScheduleConstraintService;
import com.wuxue.model.ScheduleConstraint;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jie 2018/07/10  排课约束
 */
@RestController
@RequestMapping(value = "api/product/scheduleConstraint")
public class ScheduleConstraintController implements IFindController<ScheduleConstraint>,
        ISaveController<ScheduleConstraint>,IDeleteController<Long>{
    @Autowired
    private ScheduleConstraintService scheduleConstraintService;

    @Override
    public Response Find(@RequestBody Request<ScheduleConstraint> scheduleConstraint) {

        return scheduleConstraintService.find(scheduleConstraint);
    }

    @Override
    public Response Save(@RequestBody Request<ScheduleConstraint> scheduleConstraint) {
        return scheduleConstraintService.save(scheduleConstraint);
    }

    @Override
    public Response Delete(@RequestBody Request<Long> scheduleConstraint) {
        return scheduleConstraintService.delete(scheduleConstraint);
    }


}
