package com.wuxue.view.controllers.system;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.*;

import com.wuxue.utils.common.MD5Util;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.client.system.*;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.*;
import net.sf.json.JSONArray;
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
        ICreateController<SysUser, String>, IEditController<SysUser, String>, IDeleteController<SysUser, String> {

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
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    private StudentClient studentClient;
    @Autowired
    private EmployeeClient employeeClient;

    @Override
    public String create(HttpServletRequest request, Model model, SysUser sysUser) {

        sysUser.setCreationDate(new Date());
        sysUser.setLasteditDate(new Date());
        sysUser.put(LinkEntity.CREATOR_ENTITY, EmployeeUtils.getSysUser());
        sysUser.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
        model.addAttribute("sysUser",sysUser);


        initPageAttribute(model,SessionCache.getPkdomain(),SessionCache.getfDeparement());
        sysDictUtils.setModeAttribute(model,"userTypeList",SysDicEmnuUtils.ZHY_USER_TYPE);

        //所属角色
        Response<List<SysRole>> roleList = sysRoleClient.getRoleList(SessionCache.getPkdomain());
        model.addAttribute("sysRoles",roleList.getData());
        ArrayList<SysRole> roleArrayList = new ArrayList<>();
        model.addAttribute("userRole",roleArrayList);

        model.addAttribute("flag",0);

        return "/system/user";
    }

    @RequestMapping(value = "/createUserType", method = RequestMethod.GET)
    public String createUserType(HttpServletRequest request, Model model, SysUser sysUser) {

        sysUser.setCreationDate(new Date());
        sysUser.setLasteditDate(new Date());
        sysUser.put(LinkEntity.CREATOR_ENTITY, EmployeeUtils.getSysUser());
        sysUser.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
        model.addAttribute("sysUser",sysUser);


        initPageAttribute(model,SessionCache.getPkdomain(),SessionCache.getfDeparement());
        sysDictUtils.setModeAttribute(model,"userTypeList",SysDicEmnuUtils.ZHY_USER_TYPE);

        //所属角色
        Response<List<SysRole>> roleList = sysRoleClient.getRoleList(SessionCache.getPkdomain());
        model.addAttribute("sysRoles",roleList.getData());
        ArrayList<SysRole> roleArrayList = new ArrayList<>();
        model.addAttribute("userRole",roleArrayList);

        return "/system/userTypeAdd";
    }

    @ResponseBody
    @RequestMapping(value = "/saveUserType", method = RequestMethod.POST)
    public Response saveUserType(HttpServletRequest request, Model model, SysUser sysUser) {
        Response response = Response.newResponse();
        if(sysUser.getPassword() != null && !"".equals(sysUser.getPassword())) {
            sysUser.setPassword(MD5Util.string2MD5(sysUser.getPassword()));
        }else {
            sysUser.setPassword(null);
        }

        Response<SysUser> byPrimaryKey = sysUserClient.findByPrimaryKey(sysUser);
        if(byPrimaryKey.getData() == null) {
//        创建学生，教师，操作员
            if (!sysUser.getUserType().equals("") && sysUser.getUserType() != null) {
                String[] split = sysUser.getUserType().split(",");
                response = saveSysUserType(split,sysUser);
            }
        }

        return response;
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

    @Override
    public String edit(HttpServletRequest request, Model model, SysUser sysUser) {
        Response<SysUser> byPrimaryKey = sysUserClient.findByPrimaryKey(sysUser);
//        List<String> split = new ArrayList<>();
//        SysUser data = byPrimaryKey.getData();
//        if (data != null &&  data.getUserType() != null) {
//            String userType = byPrimaryKey.getData().getUserType();
//            String[] split1 = userType.split(",");
//            split = Arrays.asList(split1);
//        }
//        model.addAttribute("split",split);
        model.addAttribute("sysUser", byPrimaryKey.getData());

        //所属角色,角色回显
        Map<String, Object> map = byPrimaryKey.getData().getMap();
        String jsonString = JSON.toJSONString(map.get(Light.ROLE_LIST));
        ArrayList<SysRole> roleArrayList = JSON.parseObject(jsonString, new TypeReference<ArrayList<SysRole>>() {});
        model.addAttribute("userRole",roleArrayList);

        initPageAttribute(model,SessionCache.getPkdomain(),SessionCache.getfDeparement());
        sysDictUtils.setModeAttribute(model,"userTypeList",SysDicEmnuUtils.ZHY_USER_TYPE);

        //所属角色
        Response<List<SysRole>> roleList = sysRoleClient.getRoleList(SessionCache.getPkdomain());
        model.addAttribute("sysRoles",roleList.getData());

        model.addAttribute("flag",1);

        return "/system/user";
    }

    @Override
    public String query(HttpServletRequest request, Model model, SysUser sysUser) {
        sysUser.setPkDomain(SessionCache.getPkdomain());
        Response<List<SysUser>> listResponse = sysUserClient.find(sysUser);
        model.addAttribute("list", listResponse.getData());

        return "/system/userlist";
    }


    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, SysUser sysUser) {
        List<SysUserRoleKey> sysUserRoleKeyList = new ArrayList<>();
        SysUserRoleKey sysUserRoleKey;
//        获取前端传来的roleList字符串
        String roles = request.getParameter("roleList");
        if(roles !=null || "".equals(roles)){
            String[] split = roles.split(",");
            for (int i = 0; i < split.length; i++) {
                sysUserRoleKey = new SysUserRoleKey();
                sysUserRoleKey.setPkSysRole(split[i]);
                sysUserRoleKeyList.add(sysUserRoleKey);
            }

            sysUser.put(Light.SYS_USER_ROLE,sysUserRoleKeyList);

        }

//        Response<SysUser> byPrimaryKey = sysUserClient.findByPrimaryKey(sysUser);
//        if(byPrimaryKey.getData() == null) {
////        创建学生，教师，操作员
//            if (!sysUser.getUserType().equals("") && sysUser.getUserType() != null) {
//                String[] split = sysUser.getUserType().split(",");
//                saveSysUserType(split);
//            }
//        }

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
        Response<List<SysUser>> listResponse = sysUserClient.find(sysUser);
        List<SysUser> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }


    public Response saveSysUserType(String[] split, SysUser sysUser) {
        Response response = Response.newResponse();
        String message = "";
        try {
            for (int i = 0; i < split.length; i++) {
                sysUser.setCreator(SessionCache.getUserCode());
                sysUser.setCreationDate(new Date());
                sysUser.setModifier(SessionCache.getUserCode());
                sysUser.setLasteditDate(new Date());

                if (split[i].equals("251656464645615199")) {
                    Student student = new Student();
                    student.setPkStudent(GuidUtils.getGuid());
                    student.setCreator(SessionCache.getUserCode());
                    student.setCreationDate(new Date());
                    student.setPhone(sysUser.getPkSysUser());
                    student.setModifier(SessionCache.getUserCode());
                    student.setLastloginDate(new Date());
                    student.setCode("systemGeneration");
                    if (sysUser.getPassword() != null) {
                        student.setPassword(MD5Util.string2MD5(sysUser.getPassword()));
                    } else {
                        student.setPassword(MD5Util.string2MD5("123456"));
                    }
                    student.setCaption(sysUser.getCaption());
                    student.setPkDomain(SessionCache.getPkdomain());
                    studentClient.save(student);
                } else if (split[i].equals("251656464645615201")) {
                    Employee employee = new Employee();
                    employee.setCreator(SessionCache.getUserCode());
                    employee.setCreationDate(new Date());
                    employee.setModifier(SessionCache.getUserCode());
                    employee.setLasteditDate(new Date());
                    employee.setCode("systemGeneration");
                    employee.setCaption(sysUser.getCaption());
                    employee.setPkDomain(SessionCache.getPkdomain());
                    employee.setPkDepartment(SessionCache.getfDeparement());
                    employee.setSysUser(sysUser.getPkSysUser());
                    employeeClient.save(employee);

                    SysRole sysRole = new SysRole();
                    sysRole.setCode("teacher");
                    Response<List<SysRole>> listResponse = sysRoleClient.find(sysRole);
                    List<SysUserRoleKey> sysUserRoleKeyList = new ArrayList<>();
                    if(listResponse.getData() != null && listResponse.getData().size() > 0) {
                        SysUserRoleKey sysUserRoleKey = new SysUserRoleKey();
                        sysUserRoleKey.setPkSysRole(listResponse.getData().get(0).getPkSysRole());
                        sysUserRoleKeyList.add(sysUserRoleKey);
                        sysUser.put(Light.SYS_USER_ROLE,sysUserRoleKeyList);
                    }
                }
                sysUserClient.save(sysUser);
            }
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response;
    }

}
