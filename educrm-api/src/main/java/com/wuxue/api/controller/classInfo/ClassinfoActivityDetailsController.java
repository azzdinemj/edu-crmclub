package com.wuxue.api.controller.classInfo;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.ClassinfoActivityDetails;
import com.wuxue.api.service.ClassinfoActivityDetailsService;
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
@RequestMapping(value = "api/classinfo/classinfoActivityDetails")
public class ClassinfoActivityDetailsController implements IFindController<ClassinfoActivityDetails>,
        ISaveController<ClassinfoActivityDetails>,IDeleteController<String> {
    @Autowired
    private ClassinfoActivityDetailsService classinfoActivityDetailsService;

    @Override
    public Response Find(@RequestBody Request<ClassinfoActivityDetails> classinfoActivityDetails) {
        return classinfoActivityDetailsService.find(classinfoActivityDetails);
    }

    @Override
    public Response Save(@RequestBody Request<ClassinfoActivityDetails> classinfoActivityDetails) {
        return classinfoActivityDetailsService.save(classinfoActivityDetails);
    }
    @Override

    public Response Delete(@RequestBody Request<String> classinfoActivityDetails) {
        return classinfoActivityDetailsService.delete(classinfoActivityDetails);

    }
}
