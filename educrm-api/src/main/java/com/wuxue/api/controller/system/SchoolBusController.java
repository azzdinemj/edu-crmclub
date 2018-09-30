package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.SchoolBusService;
import com.wuxue.base.ResultEntity;
import com.wuxue.model.SchoolBus;
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
@RequestMapping(value = "api/system/schoolbus")
public class SchoolBusController implements IFindController<SchoolBus>,
        ISaveController<SchoolBus>,IDeleteController<String> {
    @Autowired
    private SchoolBusService schoolBusService;

    @Override
    public Response Find(@RequestBody Request<SchoolBus> schoolBus) {
        return schoolBusService.find(schoolBus);
    }

    @Override
    public Response Save(@RequestBody Request<SchoolBus> schoolBus) {
        return schoolBusService.save(schoolBus);
    }

    @Override
    public Response Delete(@RequestBody Request<String> schoolBus) {
        return schoolBusService.delete(schoolBus);

    }

    /**
     * 根据监车老师id查询对应校车id
     *
     * @param schoolBus
     * @return
     */
    @RequestMapping(value = "/findBusIdByUserId", method = RequestMethod.POST)
    public Response findBusIdByUserId(@RequestBody SchoolBus schoolBus) {
        return schoolBusService.findBusIdByUserId(schoolBus);
    }

    /**
     * 根据校车id查询校车对应的所有学生信息
     * @param resultEntity
     * @return
     */
    @RequestMapping(value = "/findStudentInfosByBusId",method = RequestMethod.POST)
    public Response findStudentInfosByBusId(@RequestBody ResultEntity resultEntity){
        return schoolBusService.findStudentInfosByBusId(resultEntity);
    }
}
