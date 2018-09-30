package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.SysMenu;
import com.wuxue.api.service.SysMenuService;
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
@RequestMapping(value = "api/system/sysMenu")
public class SysMenuController implements IFindController<SysMenu>,ISaveController<SysMenu>,IDeleteController<String> {
    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping(value = "/findparentisnotnull",method = RequestMethod.POST)
    public Response findparentisnotnull(@RequestBody Request<SysMenu> request)  {
        return sysMenuService.findparentisnotnull(request);
    }
    @RequestMapping(value = "/findparentisnull",method = RequestMethod.POST)
    public Response findparentisnull(@RequestBody Request<SysMenu> request)  {
        return sysMenuService.findparentisnull(request);
    }
    @Override
    public Response Find(@RequestBody Request<SysMenu> request)  {
       return sysMenuService.find(request);
    }

    @Override
    public Response Save(@RequestBody Request<SysMenu> request) {
        return sysMenuService.save(request);
    }

    @Override
    public Response Delete(@RequestBody Request<String> request) {
            return sysMenuService.delete(request);
    }
}
