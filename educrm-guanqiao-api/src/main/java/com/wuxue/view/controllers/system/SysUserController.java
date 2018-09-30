package com.wuxue.view.controllers.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.*;

import com.wuxue.utils.common.MD5Util;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.*;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/system/sysUser")
public class SysUserController extends BaseController
        implements IQueryController<SysUser, String>, ISaveController<SysUser, String>,IQueryByPagingController<SysUser,Map<String,Object>>,
        ICreateController<SysUser, Response>, IEditController<SysUser, Response>, IDeleteController<SysUser, String> {

    @InitBinder("sysUser")
    public void initBinder1(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("user.");
    }

    @Autowired
    private SysUserClient sysUserClient;
    @Autowired
    private DomainClient domainClient;
    @Autowired
    private DepartmentClient departmentClient;
    @Autowired
    private SysRoleClient sysRoleClient;
    @Autowired
    private SysMenuClient sysMenuClient;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    private DepartmentUtils departmentUtils;


    @RequestMapping(value = "/createHtml",method = RequestMethod.GET)
    public String createHtml(HttpServletRequest request, Model model, SysUser sysUser) {
        return "systemhtml/addUser";
    }

    @Override
    @ResponseBody
    public Response create(HttpServletRequest request, Model model, SysUser sysUser) {


        sysUser.setCreationDate(new Date());
        sysUser.setLasteditDate(new Date());
        sysUser.setPkDomain(SessionCache.getPkdomain());
        sysUser.put(LinkEntity.CREATOR_ENTITY, EmployeeUtils.getSysUser());
        sysUser.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());


        initPageAttribute(model,SessionCache.getPkdomain(),SessionCache.getfDeparement());

        //所属角色
        Response roleList = sysRoleClient.getRoleList(SessionCache.getPkdomain());

        sysUser.put("sysRoles",roleList.getData());
        sysUser.put("flag",0);

        Response response = Response.newResponse();
        roleList.setData(sysUser);

        return roleList;
    }



    public void initPageAttribute(Model model,String pkDomain,String pkDepartment){

        domainUtils.setListModeAttribute(model,"domains",pkDomain);
        departmentUtils.setListModeAttribute(model,"departments",pkDepartment);


    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, SysUser sysUser) {
        return sysUserClient.delete(sysUser.getPkSysUser());
    }
    @RequestMapping(value = "/editHtml",method = RequestMethod.GET)
    public String editHtml(HttpServletRequest request, Model model, SysUser sysUser) {
        return "systemhtml/editUser";
    }
    @Override
    @ResponseBody
    public Response edit(HttpServletRequest request, Model model, SysUser sysUser) {
        Response<SysUser> byPrimaryKey = sysUserClient.findByPrimaryKey(sysUser);
        SysUser data = byPrimaryKey.getData();

        Map<String, Object> map = byPrimaryKey.getData().getMap();
        String jsonString = JSON.toJSONString(map.get(Light.ROLE_LIST));
        ArrayList<SysRole> roleArrayList = JSON.parseObject(jsonString, new TypeReference<ArrayList<SysRole>>() {});
        model.addAttribute("userRole",roleArrayList);

        data.put("userRole",roleArrayList);

        //所属角色
        Response<List<SysRole>> roleList = sysRoleClient.getRoleList(SessionCache.getPkdomain());
        model.addAttribute("sysRoles",roleList.getData());

        data.put("sysRoles",roleList.getData());
        data.put("flag",1);

        model.addAttribute("flag",1);
        byPrimaryKey.setData(data);

        return byPrimaryKey;
    }

    @Override
    public String query(HttpServletRequest request, Model model, SysUser sysUser) {

        return "systemhtml/sysUserList";
    }


    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, SysUser sysUser) {
        List<SysUserRoleKey> sysUserRoleKeyList = new ArrayList<>();
        SysUserRoleKey sysUserRoleKey;
//        获取前端传来的roleList字符串
        String roles = request.getParameter("roleList");
        if(roles !=null || !"".equals(roles)){
            String[] split = roles.split(",");
            for (int i = 0; i < split.length; i++) {
                sysUserRoleKey = new SysUserRoleKey();
                sysUserRoleKey.setPkSysRole(split[i]);
                sysUserRoleKeyList.add(sysUserRoleKey);
            }

            sysUser.put(Light.SYS_USER_ROLE,sysUserRoleKeyList);

        }

        Integer flag = Integer.valueOf(request.getParameter("flag"));
        if (flag == 0) {
            sysUser.setCreator(SessionCache.getUserCode());
        }
        sysUser.setModifier(SessionCache.getUserCode());

        if(sysUser.getPassword() != null && !"".equals(sysUser.getPassword())) {
            sysUser.setPassword(MD5Util.string2MD5(sysUser.getPassword()));
        }else {
            sysUser.setPassword(null);
        }

        return sysUserClient.save(sysUser);
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, SysUser sysUser, Integer sEcho, Integer start, Integer length) {
        sysUser.setPageNo((start/length)+1);
        sysUser.setPageSize(length);
//        String dateTime = sysUser.getDateTime();
//        if(dateTime!=null && !"".equals(dateTime)){
//            String substring = dateTime.substring(0, 10);
//            String substring1 = dateTime.substring(12);
//            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
////            SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//            try {
//                Date strTime=simpleDateFormat.parse(substring);
//                Date endTime=simpleDateFormat.parse(substring1);
//                classinfo.setFromDate(strTime);
//                classinfo.setToDate(endTime);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
        sysUser.setPkDomain(SessionCache.getPkdomain());
        Response<List<SysUser>> listResponse = sysUserClient.find(sysUser);
        List<SysUser> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }


}
