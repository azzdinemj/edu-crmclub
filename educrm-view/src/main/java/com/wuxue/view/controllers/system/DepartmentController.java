package com.wuxue.view.controllers.system;

import com.alibaba.fastjson.JSON;
import com.wuxue.base.KeyValue;
import com.wuxue.model.Department;
import com.wuxue.model.Domain;
import com.wuxue.model.SysAutocode;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.DepartmentClient;
import com.wuxue.view.client.system.SysAutcodeClient;
import com.wuxue.view.client.system.SysUserClient;
import com.wuxue.view.contract.TreeGridColumn;
import com.wuxue.view.contract.TreeGridModel;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 部门管理
 */
@Controller
@RequestMapping(value = "/system/department")
public class DepartmentController extends BaseController
        implements IQueryController<Department, String>, ISaveController<Department, String>,IQueryByPagingController<Department,Map<String,Object>>,
        ICreateController<Department, String>, IEditController<Department, String>, IDeleteController<Department, String> {

    @InitBinder("department")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("dep.");
    }

    @Autowired
    private DepartmentClient departmentClient;
    @Autowired
    private SysUserClient sysUserClient;
    @Autowired
    private SysAutcodeClient sysAutcodeClient;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    private DepartmentUtils departmentUtils;
    @Autowired
    private SysAutoCodeUtils sysAutoCodeUtils;

    /**
     * 部门列表页面
     *
     * @param model
     * @return
     * @throws
     */
    @Override
    public String query(HttpServletRequest request, Model model, Department department) {

        List<Department> departments = new ArrayList<>();

        String s = SessionCache.getfDeparement();

        Response<List<Department>> listResponse = departmentClient.find(department);


        //List<Department> treeDepartment = getTreeDepartment(listResponse.getData(), s);



        department.setPkDepartment(SessionCache.getfDeparement());
        Response<Department> byPrimaryKey = departmentClient.findByPrimaryKey(department);
        model.addAttribute("myDep",byPrimaryKey.getData());

        return "/system/departmentList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Department department, Integer sEcho, Integer start, Integer length) {
        department.setPageNo((start/length)+1);
        department.setPageSize(length);



        Response<List<Department>> listResponse = departmentClient.find(department);
        List<Department> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    /**
     * 修改部门页面
     *
     * @param department
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, Department department) {

//        Department parentDep;
//
//        Department department1 = departmentUtils.getDepartment(department.getPkDepartment());
//        if (department1.getPkParent() == null || "".equals(department1.getPkParent())){
//            parentDep = new Department();
//        }else {
//            parentDep= departmentUtils.getParentDep(department1.getPkParent());
//        }

        Response<List<Department>> listResponse = departmentClient.find(new Department());

        model.addAttribute("parentList",listResponse.getData());

        Response<Department> byPrimaryKey = departmentClient.findByPrimaryKey(department);
        model.addAttribute("department", byPrimaryKey.getData());

        //初始化下拉列表
       initPageAttribute(model,byPrimaryKey.getData().getPkDomain());

        return "/system/departmentModel";
    }

    private void initPageAttribute(Model model,String pkDoamin){
        //所属公司
        domainUtils.setModeAttribute(model,"domain",pkDoamin);

    }

    /**
     * 保存
     *
     * @param department
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Department department) {

        if (department.getPkDepartment() == null || "".equals(department.getPkDepartment())) {
            department.setCreator(SessionCache.getUserCode());
            department.setPkDepartment(GuidUtils.getGuid());

        }
        department.setModifier(SessionCache.getUserCode());

        String response = departmentClient.save(department);
        return response;

    }

    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, Department department) {

//        if(department.getPkDepartment() !=null && !"".equals(department.getPkDepartment())){
//
//            Department department1 = departmentUtils.getDepartment(department.getPkDepartment());
//            Department parentDep= departmentUtils.getParentDep(department1.getPkParent());
//            model.addAttribute("parent",parentDep);
//
//        }else{
//            String getfDeparement = SessionCache.getfDeparement();
//            Department department1 = new Department();
//            department1.setPkDepartment(getfDeparement);
//            Response<Department> byPrimaryKey1 = departmentClient.findByPrimaryKey(department1);
//            model.addAttribute("parent",byPrimaryKey1.getData());
//
//        }

        Response<List<Department>> listResponse = departmentClient.find(new Department());

        model.addAttribute("parentList",listResponse.getData());

        //初始化下拉列表
        initPageAttribute(model,SessionCache.getPkdomain());

        department.put(LinkEntity.CREATOR_ENTITY, EmployeeUtils.getSysUser());
        department.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
        department.setCreationDate(new Date());
        department.setLasteditDate(new Date());
        String sysAutocode = sysAutoCodeUtils.getSysAutocode(AutoCodeEnum.DEPARTMENT);
        department.setCode(sysAutocode);
        model.addAttribute("department",department);

        return "/system/departmentModel";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, Department department) {
        String response = departmentClient.delete(department.getPkDepartment());
        return response;
    }


    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public String getlist(HttpServletRequest request, Model model,Department department){
        Response<List<Department>> listResponse = departmentClient.find(department);
        List<Department> data = listResponse.getData();
        List<JsonUtils> list = new ArrayList<>();
        JsonUtils jsonUtils;
        for (Department datum : data) {

            jsonUtils= new JsonUtils();

            jsonUtils.setId(datum.getPkDepartment());
            jsonUtils.setpId(datum.getPkParent());
            jsonUtils.setName(datum.getCaption());
            list.add(jsonUtils);

        }
        String jsonString = JSON.toJSONString(list);

        return jsonString;
    }

    /**
     * 获取用户所有子部门
     * @param list
     * @param pkDepartmen
     * @return
     */
    public List<Department> getTreeDepartment(List<Department> list ,String pkDepartmen){

       List<Department> treeDepartment = new ArrayList<>();
        if(list != null && list.size()>0){
            for (Department datum : list) {

                if(pkDepartmen.equals(datum.getPkParent())){
                    treeDepartment.addAll(getTreeDepartment(list,datum.getPkDepartment()));
                    treeDepartment.add(datum);

                }
            }
        }

        return treeDepartment;
    }

    @RequestMapping(value = "/getData",method = RequestMethod.GET)
    @ResponseBody
    public TreeGridModel<Department> getData(){
        TreeGridModel<Department> treeGridModel=new TreeGridModel<Department>();
        Response<List<Department>> response =  departmentClient.find(new Department());
        List<Department> list = new ArrayList<>();
        for(Department department : response.getData()){
            if(department.getPkParent() == null || department.getPkParent().equals("")){
                department.setCaption("<a class='yellow'  data-toggle='modal' data-target='#modal' data-url='/system/department/edit?pkDepartment="+department.getPkDepartment()+"'>"+department.getCaption()+"</a>");
                department.setCreationDateTime(DateTimeUtils.DateToString(department.getCreationDate()));
                department.setLasteditDateTime(DateTimeUtils.DateToString(department.getLasteditDate()));
                KeyValue creatorEntity = DataUtils.objectToObject(department.getMap().get(LinkEntity.CREATOR_ENTITY), KeyValue.class);
                department.setCreator(creatorEntity.getCaption());
                KeyValue modeifierrEntity = DataUtils.objectToObject(department.getMap().get(LinkEntity.MODIFIER_ENTITY), KeyValue.class);
                department.setModifier(modeifierrEntity.getCaption());

                getData(response.getData(),department);
                list.add(department);
            }
        }
        treeGridModel.setData(list);

        treeGridModel.setRenderTo("div1");
        List<TreeGridColumn> columnList = new ArrayList<>();
        columnList.add(createTreeGridColumn("部门编码","code"));
        columnList.add(createTreeGridColumn("部门名称","caption"));
        columnList.add(createTreeGridColumn("部门负责人","pkSysUser"));
        columnList.add(createTreeGridColumn("电话","phone"));
        columnList.add(createTreeGridColumn("传真","fax"));
        columnList.add(createTreeGridColumn("eMail","email"));
        columnList.add(createTreeGridColumn("创建人","creator"));
        columnList.add(createTreeGridColumn("创建时间","creationDateTime"));
        columnList.add(createTreeGridColumn("修改人","modifier"));
        columnList.add(createTreeGridColumn("修改时间","lasteditDateTime"));

        treeGridModel.setColumns(columnList);



        treeGridModel.setFolderCloseIcon("/images/folderOpen.png");
        treeGridModel.setFolderOpenIcon("/images/folderClose.png");
        treeGridModel.setDefaultLeafIcon("/images/defaultLeaf.gif");

        return treeGridModel;
    }
    private TreeGridColumn createTreeGridColumn(String headText,String dataField){
        TreeGridColumn treeGridColumn = new TreeGridColumn();
        treeGridColumn.setHeaderText(headText);
        treeGridColumn.setDataField(dataField);
        treeGridColumn.setUrl("www.baidu.com");
        return treeGridColumn;
    }
    private void getData(List<Department> list,Department department){
        List<Department> listTmp = new ArrayList<>();
        for (Department dept : list){
            if(dept.getPkParent()!= null && department.getPkDepartment()!= null){
                if(dept.getPkParent().equals(department.getPkDepartment())){
                    dept.setCaption("<a class='yellow'  data-toggle='modal' data-target='#modal' data-url='/system/department/edit?pkDepartment="+dept.getPkDepartment()+"'>"+dept.getCaption()+"</a>");
                    dept.setCreationDateTime(DateTimeUtils.DateToString(dept.getCreationDate()));
                    dept.setLasteditDateTime(DateTimeUtils.DateToString(dept.getLasteditDate()));
                    KeyValue creatorEntity = DataUtils.objectToObject(dept.getMap().get(LinkEntity.CREATOR_ENTITY), KeyValue.class);
                    dept.setCreator(creatorEntity.getCaption());
                    KeyValue modeifierrEntity = DataUtils.objectToObject(dept.getMap().get(LinkEntity.MODIFIER_ENTITY), KeyValue.class);
                    dept.setModifier(modeifierrEntity.getCaption());
                    listTmp.add(dept);
                    getData(list,dept);
                }
            }

        }
        if(listTmp.size()>0) {
            department.setChildren(listTmp);
        }
       // department.put("children",listTmp);
    }
}
