package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.SysMenuButton;
import com.wuxue.model.SysMenuButtonKey;
import com.wuxue.api.service.SysMenuButtonService;
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
@RequestMapping(value = "api/system/sysMenuButton")
public class SysMenuButtonController implements IFindController<SysMenuButton>,
        ISaveController<SysMenuButton>,IDeleteController<SysMenuButtonKey>{
    @Autowired
    private SysMenuButtonService sysMenuButtonService;

    @Override
    public Response Find(@RequestBody Request<SysMenuButton> sysMenuButton) {
        return sysMenuButtonService.find(sysMenuButton);
    }

    @Override
    public Response Save(@RequestBody Request<SysMenuButton> sysMenuButton) {
        return sysMenuButtonService.save(sysMenuButton);
    }

    @Override
    public Response Delete(@RequestBody Request<SysMenuButtonKey> sysMenuButton) {
        return sysMenuButtonService.delete(sysMenuButton);
    }
}
