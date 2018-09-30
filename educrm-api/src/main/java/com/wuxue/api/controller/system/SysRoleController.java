package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.SysRole;
import com.wuxue.api.service.SysRoleService;
import com.wuxue.model.SysUser;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/01/02.
 */
@RestController
@RequestMapping(value = "api/system/sysrole")
public class SysRoleController implements IFindController<SysRole>,
        ISaveController<SysRole>,IDeleteController<String>{
    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public Response Find(@RequestBody Request<SysRole> sysRole) {
        return sysRoleService.find(sysRole);
    }

    @Override
    public Response Save(@RequestBody Request<SysRole> sysRole) {
        return sysRoleService.save(sysRole);
    }

    @Override
    public Response Delete(@RequestBody Request<String> sysRole) {
        return sysRoleService.delete(sysRole);
    }


    @RequestMapping(value = "/domainrole",method = RequestMethod.POST)
    public Response domainRole(@RequestBody Request<String> sysRole) {
        return sysRoleService.domainRole(sysRole);
    }

    @RequestMapping(value = "/getuserrole",method = RequestMethod.POST)
    public Response getUserRole(@RequestBody Request<String> sysUser) {
        return sysRoleService.getUserRole(sysUser);
    }
}
