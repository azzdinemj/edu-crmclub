package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.Department;
import com.wuxue.api.service.DepartmentService;
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
@RequestMapping(value = "api/system/department")
public class DepartmentController implements IFindController<Department>,
        ISaveController<Department>,IDeleteController<String> {
    @Autowired
    private DepartmentService departmentService;

    @Override
    public Response Find(@RequestBody Request<Department> department) {
        return departmentService.find(department);
    }

    @Override
    public Response Save(@RequestBody Request<Department> department) {
        return departmentService.save(department);
    }
    @Override

    public Response Delete(@RequestBody Request<String> department) {
        return departmentService.delete(department);

    }
}
