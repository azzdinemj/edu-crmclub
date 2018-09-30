package com.wuxue.view.controllers.system;

import com.wuxue.model.SysMenu;
import com.wuxue.model.SysMenuButton;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.SysMenuButtonClient;
import com.wuxue.view.client.system.SysMenuClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IEditController;
import com.wuxue.view.interfaces.IQueryByPagingController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.interfaces.ISaveController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单管理控制器
 */
@Controller
@RequestMapping("/system/sysButtonManager")
public class SysButtonManagerController extends BaseController implements IQueryController<SysMenuButton, String>,
        ISaveController<SysMenuButton, String>, IEditController<SysMenuButton, String>,IQueryByPagingController<SysMenu,Map<String,Object>> {

    @Autowired
    SysMenuButtonClient sysMenuButtonClient;


    @Autowired
    SysMenuClient sysMenuClient;

    /*
     *编辑-跳转
     *sysMenu  参数为:主键不为空
     */
    @Override
    public String edit(HttpServletRequest request, Model model, SysMenuButton sysMenu) {
           if(sysMenu.getPkSysMenu()!=null&&sysMenu.getPkSysMenu()!=""){
               Response<SysMenuButton> response=sysMenuButtonClient.findByPrimaryKey(sysMenu);
               model.addAttribute("responseSysmenu",response.getData());
           }
           return "/system/sysButtonManager";
    }




    /*
     *新增/修改
     *
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, SysMenuButton sysmenu) {
         return sysMenuButtonClient.save(sysmenu);
    }

    /*
     *查询列表
     *
     */
    @Override
    public String query(HttpServletRequest request, Model model, SysMenuButton sysMenu) throws IOException {
        Response<List<SysMenuButton>> listResponse = sysMenuButtonClient.find(sysMenu);
        model.addAttribute("list", listResponse.getData());
        return "/system/sysButtonManagerlist";
    }


    /*
     *查询列表 modal框
     *
     */
   @RequestMapping("/querylist")
    public String querylist(HttpServletRequest request, Model model, SysMenuButton sysMenu) throws IOException {
       return "/model/sysButtonManagerModel";
    }


    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, SysMenu sysMenu, Integer sEcho, Integer start, Integer length) {
        sysMenu.setPageNo((start/length)+1);
        sysMenu.setPageSize(length);

        Response<List<SysMenu>> listResponse = sysMenuClient.findparentisnotnull(sysMenu);
        List<SysMenu> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }


}
