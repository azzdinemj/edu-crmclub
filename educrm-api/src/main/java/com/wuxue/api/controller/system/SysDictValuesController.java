package com.wuxue.api.controller.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindByParentController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.SysDictService;
import com.wuxue.model.SysDict;
import com.wuxue.model.SysDictValues;
import com.wuxue.api.service.SysDictValuesService;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rogue on 2018/01/03.
 */
@RestController
@RequestMapping(value = "api/system/sysDictValues")
public class SysDictValuesController implements IFindController<SysDictValues>,
        ISaveController<SysDictValues>,IDeleteController<String>,IFindByParentController<String> {
    @Autowired
    private SysDictValuesService sysDictValuesService;

    @Override
    public Response Find(@RequestBody Request<SysDictValues> sysDictValues) {
        return sysDictValuesService.find(sysDictValues);
    }

    @Override
    public Response Save(@RequestBody Request<SysDictValues> sysDictValues) {
        return sysDictValuesService.save(sysDictValues);
    }

    @Override
    public Response Delete(@RequestBody Request<String> sysDictValues) {
        return sysDictValuesService.delete(sysDictValues);
    }

    @Override
    public Response FindValue(@RequestBody Request<String> sysDictValues) {
        return sysDictValuesService.findValue(sysDictValues);
    }
}
