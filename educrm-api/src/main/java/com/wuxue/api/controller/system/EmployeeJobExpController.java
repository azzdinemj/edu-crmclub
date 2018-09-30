package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.EmployeeJobExp;
import com.wuxue.api.service.EmployeeJobExpService;
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
@RequestMapping(value = "api/system/employeeJobExp")
public class EmployeeJobExpController implements IFindController<EmployeeJobExp>,
        ISaveController<EmployeeJobExp>,IDeleteController<String> {
    @Autowired
    private EmployeeJobExpService employeeJobExpService;

    @Override
    public Response Find(@RequestBody Request<EmployeeJobExp> employeeJobExp) {
        return employeeJobExpService.find(employeeJobExp);
    }

    @Override
    public Response Save(@RequestBody Request<EmployeeJobExp> employeeJobExp) {
        return employeeJobExpService.save(employeeJobExp);
    }
    @Override

    public Response Delete(@RequestBody Request<String> employeeJobExp) {
        return employeeJobExpService.delete(employeeJobExp);

    }
}
