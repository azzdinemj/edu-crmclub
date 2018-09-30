package com.wuxue.view.controllers.system;



import com.wuxue.model.SysUser;

import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.SysUserClient;
import com.wuxue.view.controllers.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

@Controller
@RequestMapping(value = "/system")
public class SystemController extends BaseController{

    @Autowired
    private SysUserClient sysUserClient;
    public SystemController() {

    }

    /**
     * 用户列表页面
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @RequestMapping(value = "/queryUser", method = RequestMethod.GET)
    public String queryUser(Model model, SysUser sysUser) throws Exception {
        Response<List<SysUser>> listResponse = sysUserClient.find(sysUser);

        model.addAttribute("list",listResponse.getData() );
        return "/system/userlist";
    }

    public String addUser(){
        return "/";
    }


    /**
     * 保存用户
     * @param sysUser
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/saveUser",method = RequestMethod.POST)
    @ResponseBody
    public  String saveuser(SysUser sysUser,HttpServletRequest request,Model model){
        return sysUserClient.save(sysUser);
    }

    /**
     * 创建用户跳转
     * @param request
     * @return
     */
    @RequestMapping(value = {"/admincreate"}, method = RequestMethod.GET)
    public String createAdmin(HttpServletRequest request) {
        return "/system/adduser";
    }

    /**
     * 修改页面
     * @param sysUser
     * @param model
     * @return
     */
    @RequestMapping({"/editUser"})
    public String editUser(SysUser sysUser,Model model) {
        Response<SysUser> byPrimaryKey = sysUserClient.findByPrimaryKey(sysUser);
        model.addAttribute("sysUser",byPrimaryKey.getData());

        return "/system/edituser";
    }


    /**
     *
     * @param request
     * @param sysUser
     * @return
     */
    @RequestMapping(value = "/deleteuser", method =RequestMethod.POST)
    @ResponseBody
    public String deleteAdmin(HttpServletRequest request,SysUser sysUser) {

        return sysUserClient.delete(sysUser.getPkSysUser());
        }
}