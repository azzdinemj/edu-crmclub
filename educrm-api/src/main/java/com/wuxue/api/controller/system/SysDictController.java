package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindByParentController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.SysDict;
import com.wuxue.api.service.SysDictService;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/01/03.
 */
@RestController
@RequestMapping(value = "api/system/sysDict")
public class SysDictController implements IFindController<SysDict>,
        ISaveController<SysDict>,IDeleteController<String>{
    @Autowired
    private SysDictService sysDictService;

    @Override
    public Response Find(@RequestBody Request<SysDict> sysDict) {
        return sysDictService.find(sysDict);
    }

    @Override
    public Response Save(@RequestBody Request<SysDict> sysDict) {
        return sysDictService.save(sysDict);
    }

    @Override
    public Response Delete(@RequestBody Request<String> sysDict) {
        return sysDictService.delete(sysDict);
    }

}
