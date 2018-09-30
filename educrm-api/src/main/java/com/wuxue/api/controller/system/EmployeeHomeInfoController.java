package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.EmployeeHomeInfoService;
import com.wuxue.model.EmployeeHomeInfo;
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
@RequestMapping(value = "api/system/employeeHomeInfo")
public class EmployeeHomeInfoController implements IFindController<EmployeeHomeInfo>,
        ISaveController<EmployeeHomeInfo>,IDeleteController<String> {
    @Autowired
    private EmployeeHomeInfoService employeeHomeInfoService;

    @Override
    public Response Find(@RequestBody Request<EmployeeHomeInfo> employeeHomeInfo) {
        return employeeHomeInfoService.find(employeeHomeInfo);
    }

    @Override
    public Response Save(@RequestBody Request<EmployeeHomeInfo> employeeHomeInfo) {
        return employeeHomeInfoService.save(employeeHomeInfo);
    }
    @Override

    public Response Delete(@RequestBody Request<String> employeeHomeInfo) {
        return employeeHomeInfoService.delete(employeeHomeInfo);

    }
}
