package com.wuxue.view.controllers.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.*;

import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.*;
import com.wuxue.view.contract.TreeNode;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.EmployeeUtils;
import com.wuxue.view.utils.JsonUtils;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.tags.Param;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

@Controller
@RequestMapping(value = "/system/sysRole")
public class SysRoleController extends BaseController implements IQueryController<SysRole, String>, ISaveController<SysRole, String>,
        ICreateController<SysRole, Response>, IEditController<SysRole, Response>, IDeleteController<SysRole, String>, IQueryByPagingController<SysRole,Map<String,Object>>{

    @InitBinder("sysRole")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("role.");
    }

    @Autowired
    private SysRoleClient sysRoleClient;
    @Autowired
    private SysUserClient sysUserClient;
    @Autowired
    private SysMenuButtonClient sysMenuButtonClient;
    @Autowired
    private SysMenuClient sysMenuClient;
    @Autowired
    private SysAutcodeClient sysAutcodeClient;
    @Autowired
    private  DepartmentClient departmentClient ;

    /**
     * 角色列表
     *
     * @param model
     * @param sysRole
     * @return
     */

    @Override
    public String query(HttpServletRequest request, Model model, SysRole sysRole) {


        return "systemhtml/sysRoleList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, SysRole sysRole, Integer sEcho, Integer start, Integer length) {
        sysRole.setPageNo((start/length)+1);
        sysRole.setPageSize(length);

        Response<List<SysRole>> listResponse = sysRoleClient.find(sysRole);
        List<SysRole> data = listResponse.getData();

        if(data==null) {
            data = new ArrayList<>();
        }

        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    @RequestMapping(value = "/createHtml",method = RequestMethod.GET)
    public String createHtml(HttpServletRequest request, Model model, SysRole sysRole) {


        return "systemhtml/addSysRole";
    }


    /**
     * 创建角色跳转
     *
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    public Response create(HttpServletRequest request, Model model, SysRole sysRole) {
        sysRole = new SysRole();
        sysRole.setIsvalid(1);
        sysRole.setPkDomain(SessionCache.getPkdomain());
        sysRole.put(LinkEntity.CREATOR_ENTITY, EmployeeUtils.getSysUser());
        sysRole.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
        sysRole.setCreationDate(new Date());
        sysRole.setLasteditDate(new Date());
        List<TreeNode> data = getData(null);
        sysRole.put("power", data);
        Response response = Response.newResponse();
        response.setData(sysRole);
        response.setCode(0);

        return response;
    }


    /**
     * 保存角色
     *
     * @param sysRole
     * @param request
     * @param model
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, SysRole sysRole) {

        SysPowerKey userRoleKey;
        //获取前台传递的菜单字符串
        String menus = request.getParameter("menuList");
        String depts = request.getParameter("deptList");
        if (menus != null && !menus.equals("")) {
            List<SysPowerKey> sysPowerKeyList = JSON.parseArray(menus,SysPowerKey.class);
            sysRole.put(Light.SYS_POWER, sysPowerKeyList);
        }
        if (depts != null && !depts.equals("")) {
            List<SysRoleDepartment> sysRoleDepartmentList = JSON.parseArray(depts,SysRoleDepartment.class);
            for( int i = 0 ; i < sysRoleDepartmentList.size() ; i++) {
            	SysRoleDepartment sysdept=new SysRoleDepartment();
            	sysdept=sysRoleDepartmentList.get(i);
            	sysdept.setPkDomain(SessionCache.getPkdomain());
            	sysRoleDepartmentList.set(i, sysdept);
            }
            
            sysRole.put(Light.SYS_DEPT, sysRoleDepartmentList);
        }
        if (sysRole.getPkSysRole() != null && sysRole.getPkSysRole() != "") {
            sysRole.setModifier(SessionCache.getUserCode());
        } else {
            sysRole.setCreator(SessionCache.getUserCode());
            sysRole.setModifier(SessionCache.getUserCode());
        }


        return sysRoleClient.save(sysRole);
    }

    /**
     * 修改页面
     *
     * @param sysRole
     * @param model
     * @return
     */
    @Override
    @ResponseBody
    public Response edit(HttpServletRequest request, Model model, SysRole sysRole) {
        Response<SysRole> byPrimaryKey = sysRoleClient.findByPrimaryKey(sysRole);
        SysRole data = byPrimaryKey.getData();

        List<SysPowerKey> list = DataUtils.objectToList(byPrimaryKey.getData().get(Light.SYS_POWER_LIST), SysPowerKey.class);
        model.addAttribute("power", JSON.toJSONString(getData(list)));
        data.put("power", getData(list));
        byPrimaryKey.setData(data);
        return byPrimaryKey;
    }
    @RequestMapping(value = "/editHtml",method = RequestMethod.GET)
    public String editHtml(HttpServletRequest request, Model model, SysRole sysRole) {
        return "systemhtml/editsysRole";
    }

    /**
     * 删除用户
     *
     * @param request
     * @param sysRole
     * @return
     */

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, SysRole sysRole) {
        return sysRoleClient.delete(sysRole.getPkSysRole());
    }

    private TreeNode createTreeNode(String id, String name, boolean open, boolean checked) {
        TreeNode treeNode = new TreeNode();
        treeNode.setId(id);
        treeNode.setName(name);
        treeNode.setOpen(open);
        treeNode.setChecked(checked);
        return treeNode;
    }
    private TreeNode createTreeNode2(String id,String pid, String name, boolean open, boolean checked) {
        TreeNode treeNode = new TreeNode();
        treeNode.setId(id);
        treeNode.setName(name);
        treeNode.setOpen(open);
        treeNode.setChecked(checked);
        treeNode.setpId(pid);
        return treeNode;
    }

    private Boolean hasPower(String pkSysMenu, String pkSysButton, List<SysPowerKey> sysPowerKeyList) {
        if (sysPowerKeyList != null && sysPowerKeyList.size() > 0) {
            for (SysPowerKey sysPowerKey : sysPowerKeyList) {
                if (sysPowerKey.getPkSysMenu().equals(pkSysMenu) && sysPowerKey.getPkSysButton().equals(pkSysButton)) {
                	
                    return true;
                }
            }
        }
        return false;
    }

    public List<TreeNode> getData(List<SysPowerKey> sysPowerKeyList) {
        List<TreeNode> list = new ArrayList<>();
        Response<List<SysMenu>> response = sysMenuClient.find(new SysMenu());
        Response<List<SysMenuButton>> buttonResponse = sysMenuButtonClient.find(new SysMenuButton());
        for (SysMenu sysMenu : response.getData()) {
            if (sysMenu.getPkParent().equals("")) {
                TreeNode treeNodeParent = createTreeNode(sysMenu.getPkSysMenu(), sysMenu.getCaption(), false, false);
                List<TreeNode> listChild = new ArrayList<>();
                for (SysMenu sysMenuChild : response.getData()) {
                    if (sysMenuChild.getPkParent().equals(sysMenu.getPkSysMenu())) {
                        TreeNode treeNodeChild = createTreeNode(sysMenuChild.getPkSysMenu(), sysMenuChild.getCaption(), false, false);
                        List<TreeNode> listButton = new ArrayList<>();
                        for (SysMenuButton button : buttonResponse.getData()) {
                            if (button.getPkSysMenu().equals(sysMenuChild.getPkSysMenu())) {
                                listButton.add(createTreeNode(button.getPkSysButton(), button.getCaption(), false, hasPower(button.getPkSysMenu(), button.getPkSysButton(), sysPowerKeyList)));
                            }
                        }
                        if (listButton.size() > 0) {
                            treeNodeChild.setChildren(listButton);
                        }
                        listChild.add(treeNodeChild);
                    }
                }
                if (listChild.size() > 0) {
                    treeNodeParent.setChildren(listChild);
                    list.add(treeNodeParent);
                }
            }
        }
        return list;
    }
    
    //部门权限
    public List<TreeNode> getDeptRoleData(List<SysRoleDepartment> sysRoleDepatemntList) {
        List<TreeNode> list = new ArrayList<>();
        Response<List<Department>> response = departmentClient.find(new Department());
        if(response.getData().size()>0) {
        	List<TreeNode> listChild = new ArrayList<>();
        	Integer j=0;
        	
        	for(Department department:response.getData()) {
        		j=0;
        		if(sysRoleDepatemntList!=null) {
        			
        		
	        		for(SysRoleDepartment sysRoleDept:sysRoleDepatemntList) {
	        			if(sysRoleDept.getPkDepartment().equals(department.getPkDepartment())){
	        				list.add(createTreeNode2(department.getPkDepartment(),department.getPkParent(),department.getCaption(),false,true));
	        				j=1;
	            			break;
	            		}
	            	}
        		}
        		if(j.equals(0)) {
        			list.add(createTreeNode2(department.getPkDepartment(),department.getPkParent(),department.getCaption(),false,false));
        			
        		}
        		
        		
        	}
        	
        	
        }
      
        return list;
    }

}
