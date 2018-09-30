package com.wuxue.api.controller.canteen;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.MaterialService;
import com.wuxue.model.junhwa.Material;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jie 2018/07/10
 * 套餐表基本信息
 */
@RestController
@RequestMapping(value = "api/canteen/material")
public class MaterialController implements IFindController<Material>,
        ISaveController<Material>,IDeleteController<String>{
    @Autowired
    private MaterialService materialService;

    @Override
    public Response Find(@RequestBody Request<Material> material) {
        return materialService.find(material);
    }

    @Override
    public Response Save(@RequestBody Request<Material> material) {
        return materialService.save(material);
    }

    @Override
    public Response Delete(@RequestBody Request<String> material) {
        return materialService.delete(material);
    }
}
