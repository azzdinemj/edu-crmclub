package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.SysUser;
import com.wuxue.api.service.SysUserService;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Rogue on 2018/01/02.
 */
@RestController
@RequestMapping(value = "api/system/sysUser")
public class SysUserController implements IFindController<SysUser>,
        ISaveController<SysUser>, IDeleteController<String> {
    @Autowired
    private SysUserService sysUserService;

    @Override
    public Response Find(@RequestBody Request<SysUser> sysUser) {
        return sysUserService.find(sysUser);
    }

    @Override
    public Response Save(@RequestBody Request<SysUser> sysUser) {
        return sysUserService.save(sysUser);
    }

    @Override
    public Response Delete(@RequestBody Request<String> sysUser) {
        return sysUserService.delete(sysUser);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Response Login(@RequestBody Request<SysUser> sysUser) {
        return sysUserService.login(sysUser);
    }

    @RequestMapping(value = "/updatepassword",method = RequestMethod.POST)
    public Response updatePassword(@RequestBody Request<SysUser> sysUser) {
        return sysUserService.updatePassword(sysUser);
    }

}
