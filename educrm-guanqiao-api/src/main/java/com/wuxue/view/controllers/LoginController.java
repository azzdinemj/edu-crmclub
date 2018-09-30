package com.wuxue.view.controllers;


import com.alibaba.fastjson.JSON;
import com.wuxue.model.*;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.*;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 *
 * description: 
 * @auther: wh
 * @date: 2018/6/28 11:37
 */
@Controller
@RequestMapping(value = "/")
public class LoginController extends BaseController {

    @Autowired
    private SysUserClient sysUserClient;
    @Autowired
    private SysMenuClient sysMenuClient;

    @Autowired
    private SysRoleClient sysRoleClient;

    @Autowired
    private NoticeClient noticeClient;

    @Autowired
    private TaskClient taskClient;


    /**
     * 登陆页
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getSignon(HttpServletRequest request, Model model) {
        String userCode = SessionCache.getUserCode();
        SessionCache.setLoginVersion("Chinese");
        if(userCode != null && !"".equals(userCode)){
            return "redirect:/sigonindex";
        }
        return "loginhtml/login";
    }
    @InitBinder("sysUser")
    public void initBinder2(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("user.");
    }


    /**
     * 登陆验证
     */
    @RequestMapping(value = "signon", method = RequestMethod.POST)
    @ResponseBody
    public Response<SysUser> signon(HttpServletRequest request, Model model, SysUser sysUser) {

        Response<SysUser> response = sysUserClient.login(sysUser);
        if (response.getCode() == 0 && response.getData() != null) {
            SessionCache.setUserName(response.getData().getCaption());
            SessionCache.setUserCode(response.getData().getPkSysUser());
            SessionCache.setPassword(sysUser.getPassword());
            SessionCache.setPkdomain(response.getData().getPkDomain());
            SessionCache.setfDeparement(response.getData().getPkDepartment());
            Map<String,Object> map = response.getData().getMap();
            SessionCache.setSysMenu(DataUtils.objectToList(map.get(Light.SYS_MENU_LIST), SysMenu.class));
            request.getSession().setAttribute("sessionUser",SessionCache.getUserName());
            SessionCache.setDomainCaption(map.get(Light.DOMAIN_CAPTION).toString());
            request.getSession().setAttribute("doMain",SessionCache.getDomainCaption());
          //对应员工
            Employee employee=new Employee();
            employee=DataUtils.objectToObject(map.get(Light.SYS_EMPLOYEE), Employee.class);
            if(employee!=null) {
            	SessionCache.setEmployee(employee.getPkEmployee());
            }else {
            	SessionCache.setEmployee("");
            }

            //对应角色
            Response<SysRole> userRole = sysRoleClient.getUserRole(response.getData().getPkSysUser());
            SessionCache.setSysRole(userRole.getData().getCode());
            //initMenu();
        }
        return response;
    }


    private void initMenu() {
        if (SessionCache.getSysMenu() == null || SessionCache.getSysMenu().size() < 1) {
            Response<List<SysMenu>> response = new Response<>();
            response = sysMenuClient.find(new SysMenu());
            //获取用户对应的所有角色
            SysUser sysUser = new SysUser();
            sysUser.setPkSysUser(SessionCache.getUserCode());
            Response<SysUser> sysUserResponse = sysUserClient.findByPrimaryKey(sysUser);
            //所属角色,角色回显
            Map<String, Object> map = sysUserResponse.getData().getMap();
            String jsonString = JSON.toJSONString(map.get(Light.ROLE_LIST));
            List<SysRole> roleArrayList = JSON.parseArray(jsonString, SysRole.class);
            Set<String> sysMenuSet = new HashSet<String>();
            for (SysRole sysRole : roleArrayList) {
                Response<SysRole> sysRoleResponse = sysRoleClient.findByPrimaryKey(sysRole);
                List<SysPowerKey> sysPowerlist = DataUtils.objectToList(sysRoleResponse.getData().get(Light.SYS_POWER_LIST), SysPowerKey.class);
                for (SysPowerKey sysPowerKey : sysPowerlist) {
                    if (sysPowerKey.getPkSysButton().toUpperCase().equals("ONOPEN")) {
                        if (!sysMenuSet.contains(sysPowerKey.getPkSysMenu())) {
                            sysMenuSet.add(sysPowerKey.getPkSysMenu());
                        }
                    }
                }
            }
            List<SysMenu> sysMenuList = new ArrayList<SysMenu>();
            for (SysMenu sysMenu : response.getData()) {
                if (sysMenu.getPkParent() == null || sysMenu.getPkParent().equals("")) {
                    sysMenuList.add(sysMenu);
                    continue;
                }
                if (sysMenuSet.contains(sysMenu.getPkSysMenu())) {
                    sysMenuList.add(sysMenu);
                }
            }
            SessionCache.setSysMenu(sysMenuList);
        }
    }

    @RequestMapping(value = "sigonindex")
    public String loginIndex(Model model) {
        Notice notice = new Notice();
        notice.setIsdel(1);
        Response<List<Notice>> listResponse = noticeClient.find(notice);
        model.addAttribute("noticeList",listResponse.getData());
        model.addAttribute("num",listResponse.getTotal());

        Task task = new Task();
//        201816536798200506(财务主键，负责任务分配使用)
        boolean contains = SessionCache.getSysRole().contains("201816536798200506");
        if(contains == true){
            task.setType(0);
        }else{
            task.setType(1);
            task.setPkUser(SessionCache.getUserCode());
        }
        Response<List<Task>> response = taskClient.find(task);
        model.addAttribute("taskList",response.getData());

        return "loginhtml/index";
    }


    /**
     * 退出
     *
     * @return
     */
    @RequestMapping(value = "signout", method = RequestMethod.GET)
    public String signOut() {

        SessionCache.setUserName(null);
        SessionCache.setUserCode(null);
        SessionCache.setPassword(null);
        SessionCache.setPkdomain(null);
        SessionCache.setSysMenu(null);
        SessionCache.setfDeparement(null);
        return "loginhtml/login";
    }

    /**
     * 修改密码
     *
     * @return
     */
    @RequestMapping(value = "updatePassword", method = RequestMethod.GET)
    public String updatePassword() {
        return "/commons/updatePassword";
    }

    @ResponseBody
    @RequestMapping(value = "savePassword", method = RequestMethod.POST)
    public String savePassword(SysUser sysUser) {
        sysUser.setPkSysUser(SessionCache.getUserCode());
        return sysUserClient.updatePassword(sysUser);
    }
}
