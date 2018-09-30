package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.SysSet;
import com.wuxue.api.service.SysSetService;
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
@RequestMapping(value = "api/system/sysSet")
public class SysSetController implements IFindController<SysSet>,
        ISaveController<SysSet>,IDeleteController<SysSet> {
    @Autowired
    private SysSetService sysSetService;

    @Override
    public Response Find(@RequestBody Request<SysSet> sysSet) {
        return sysSetService.find(sysSet);
    }

    @Override
    public Response Save(@RequestBody Request<SysSet> sysSet) {
        return sysSetService.save(sysSet);
    }

    @Override
    public Response Delete(@RequestBody Request<SysSet> sysSet) {
        return sysSetService.delete(sysSet);

    }
}
