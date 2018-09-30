package com.wuxue.api.controller.canteen;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.ParentOrderEditService;
import com.wuxue.model.ParentOrderEdit;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jie 2018/07/10
 * 家长点餐修改记录
 */
@RestController
@RequestMapping(value = "api/canteen/parentOrderEdit")
public class ParentOrderEditController implements IFindController<ParentOrderEdit>,
        ISaveController<ParentOrderEdit>,IDeleteController<String>{
    @Autowired
    private ParentOrderEditService parentOrderEditService;

    @Override
    public Response Find(@RequestBody Request<ParentOrderEdit> parentOrderEdit) {
        return parentOrderEditService.find(parentOrderEdit);
    }

    @Override
    public Response Save(@RequestBody Request<ParentOrderEdit> parentOrderEdit) {
        return parentOrderEditService.save(parentOrderEdit);
    }

    @Override
    public Response Delete(@RequestBody Request<String> parentOrderEdit) {
        return parentOrderEditService.delete(parentOrderEdit);
    }


}
