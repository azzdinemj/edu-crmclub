package com.wuxue.view.controllers.zhyou.teacher;

import com.github.pagehelper.PageInfo;
import com.wuxue.constant.Contsants;
import com.wuxue.model.Employee;
import com.wuxue.model.SysMenu;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.EmployeeClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.SessionCache;
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
 * 教师管理  中航油
 */
@Controller
@RequestMapping(value = "/teacher/teacherlist")
public class TeacherListController extends BaseController implements IQueryController<Employee, String>, ISaveController<Employee, String>,IQueryByPagingController<Employee,Map<String,Object>>,
       IEditController<Employee, String>, IDeleteController<Employee, String> {

    @Autowired
    private EmployeeClient employeeClient;

    /**
     * 员工列表   caption 姓名， short_code 年龄，  sex 性别， address 头像， memo 详情
     * @param model
     * @param employee
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, Employee employee) {
//        //页码，每页数量
//        String str=request.getParameter("pageNo");
//        if(str!=null&&str!=""){
//            int pageNo=Integer.parseInt(str);
//            employee.setPageNo(pageNo);
//        }else{
//            employee.setPageNo(Contsants.PAGE_NO);
//        }
//        employee.setPageSize(Contsants.PAGE_SIZE);
//        employee.setPkDomain(Contsants.PK_DOMAIN_ZHY);//数据分离
//        Response<PageInfo<Employee>> response=employeeClient.findzhyou(employee);
//        PageInfo<Employee> pageInfo=response.getData();
//
//        model.addAttribute("employeelist", pageInfo);
        return "/zhyou/teacher/teacherlist";
    }

    /**
     * 修改页面
     *
     * @param employee
     * @param model
     * @return
     */

    @Override
    public String edit(HttpServletRequest request, Model model, Employee employee) {
        if(employee.getPkEmployee()!=null&&employee.getPkEmployee()!=""){
            Response<Employee> byPrimaryKey = employeeClient.findByPrimaryKey(employee);
            Employee emp = byPrimaryKey.getData();
            model.addAttribute("employee", emp);
        }
        return "/zhyou/teacher/teacher";
    }



    /**
     * 保存角色
     *
     * @param employee
     * @param request
     * @param model
     * @return
     */
    @ResponseBody
    @Override
    public String save(HttpServletRequest request, Model model, Employee employee) {
        if(employee.getPkEmployee()==null||employee.getPkEmployee()==""){
            employee.setCreator(SessionCache.getUserName());
            employee.setModifier(SessionCache.getUserName());
        }else{
            employee.setModifier(SessionCache.getUserName());
        }
        return employeeClient.savezhyou(employee);
    }


    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Employee employee, Integer sEcho, Integer start, Integer length) {
//        employee.setPageNo((start/length)+1);
//        employee.setPageSize(length);
//
//        String dateTime = employee.getDateTime();
//        if(dateTime != null && !"".equals(dateTime)){
//            employee.setFromDate(DateTimeUtils.StringSubToDate(dateTime).get(0));
//            employee.setToDate(DateTimeUtils.StringSubToDate(dateTime).get(1));
//        }
//
//        employee.setPkDomain(Contsants.PK_DOMAIN_ZHY);
//        Response<List<Employee>> listResponse = employeeClient.find(employee);
//        List<Employee> data = listResponse.getData();
//        if(data==null) {
//            data = new ArrayList<>();
//        }
 //       return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);


        employee.setPageNo((start/length)+1);
        employee.setPageSize(length);
        employee.setIsvalid(1);
        employee.setPkDomain(Contsants.PK_DOMAIN_ZHY);//数据分离
        Response<PageInfo<Employee>> resp=employeeClient.findzhyou(employee);

        List<Employee> data = resp.getData().getList();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(resp.getData().getTotal(),resp.getData().getTotal(),data);
    }




    /**
     * 删除用户
     *
     * @param request
     * @param employee
     * @return
     */

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, Employee employee) {
        return employeeClient.delete(employee);
    }

    /*
     *查询列表 modal框
     *
     */
    @RequestMapping("/querylist")
    public String querylist(HttpServletRequest request, Model model, SysMenu sysMenu) throws IOException {
        return "/model/teacherModel";
    }

    /**
     * 根据主键查找老师对象
     * @param employee
     * @return
     */
    @RequestMapping("/findByPrimaryKey")
    @ResponseBody
    public Response findByPrimaryKey(Employee employee) {
        return employeeClient.findByPrimaryKeyzhyou(employee);
    }

}
