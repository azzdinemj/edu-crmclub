package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.HolidayService;
import com.wuxue.model.junhwa.Holiday;
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
@RequestMapping(value = "api/system/holiday")
public class HolidayController implements IFindController<Holiday>,
        ISaveController<Holiday>,IDeleteController<Integer>{
    @Autowired
    private HolidayService holidayService;

    @Override
    public Response Find(@RequestBody Request<Holiday> holiday) {
        return holidayService.find(holiday);
    }

    @Override
    public Response Save(@RequestBody Request<Holiday> holiday) {
        return holidayService.save(holiday);
    }

    @RequestMapping(value = "/saveall",method = RequestMethod.POST)
    public Response saveAll(@RequestBody Request<Holiday> holiday) {
        return holidayService.saveAll(holiday);
    }

    @Override
    public Response Delete(@RequestBody Request<Integer> holiday) {
        return holidayService.delete(holiday);
    }
}
