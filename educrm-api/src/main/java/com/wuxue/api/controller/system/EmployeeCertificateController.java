package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.EmployeeCertificate;
import com.wuxue.api.service.EmployeeCertificateService;
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
@RequestMapping(value = "api/system/employeeCertificate")
public class EmployeeCertificateController implements IFindController<EmployeeCertificate>,
        ISaveController<EmployeeCertificate>,IDeleteController<String> {
    @Autowired
    private EmployeeCertificateService employeeCertificateService;

    @Override
    public Response Find(@RequestBody Request<EmployeeCertificate> employeeCertificate) {
        return employeeCertificateService.find(employeeCertificate);
    }

    @Override
    public Response Save(@RequestBody Request<EmployeeCertificate> employeeCertificate) {
        return employeeCertificateService.save(employeeCertificate);
    }
    @Override

    public Response Delete(@RequestBody Request<String> employeeCertificate) {
        return employeeCertificateService.delete(employeeCertificate);

    }
}
