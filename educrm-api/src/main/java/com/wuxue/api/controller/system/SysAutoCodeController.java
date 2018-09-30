package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.SysAutocode;
import com.wuxue.api.service.SysAutoCodeService;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/01/03.
 */
@RestController
@RequestMapping(value = "api/system/sysAutocode")
public class SysAutoCodeController implements IFindController<SysAutocode>,
        ISaveController<SysAutocode>,IDeleteController<String>{
    @Autowired
    private SysAutoCodeService sysAutocodeService;

    @Override
    public Response Find(@RequestBody Request<SysAutocode> sysAutocode) {
        return sysAutocodeService.find(sysAutocode);
    }

    @Override
    public Response Save(@RequestBody Request<SysAutocode> sysAutocode) {
        return sysAutocodeService.save(sysAutocode);
    }

    @Override
    public Response Delete(@RequestBody Request<String> sysAutocode) {
        return sysAutocodeService.delete(sysAutocode);
    }

    @RequestMapping(value = "/getcode",method = RequestMethod.POST)
    public Response getCode(@RequestBody Request<SysAutocode> sysAutocode) {
        return sysAutocodeService.getAutocode(sysAutocode);
    }
}
