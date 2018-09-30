package com.wuxue.view.controllers.system;

import com.wuxue.model.SysMenu;
import com.wuxue.model.SysUser;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.SysMenuClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/system/sysMenu")

public class SysMenuController
       /* implements IQueryController<SysMenu,String>,ISaveController<SysMenu>,
        ICreateController<SysMenu>,IEditController<SysMenu>,IDeleteController<SysMenu>*/ {
   /* @Autowired

public class SysMenuController extends BaseController
        implements IQueryController<SysMenu,String>,ISaveController<SysMenu,String>,
        ICreateController<SysMenu,String>,IEditController<SysMenu,String>,IDeleteController<SysMenu,String> {
    @Autowired

    private SysMenuClient susMenuClient;

    @Override
    public String create(HttpServletRequest request, Model model, SysMenu sysMenu) {
        return "/system/addSysMenu";
    }

    @Override
    public String delete(HttpServletRequest request, Model model, SysMenu sysMenu) {
        return (String) susMenuClient.delete(sysMenu.getPkSysMenu());
    }

    @Override
    public String edit(HttpServletRequest request, Model model, SysMenu sysMenu) {
        Response<SysMenu> byPrimaryKey = susMenuClient.findByPrimaryKey(sysMenu);
        model.addAttribute("sysMenu",byPrimaryKey.getData());
        return "/system/editSysMenu";
    }

    @Override
    public String query(HttpServletRequest request, Model model, SysMenu sysMenu) {
        Response<List<SysMenu>> listResponse = susMenuClient.find(sysMenu);
        model.addAttribute("list",listResponse.getData() );
        return "/system/sysMenuList";
    }

    @Override
    public String save(HttpServletRequest request, Model model, SysMenu sysMenu) {
        return susMenuClient.save(sysMenu);
    }*/
}
