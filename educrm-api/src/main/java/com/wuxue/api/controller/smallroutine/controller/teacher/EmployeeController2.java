package com.wuxue.api.controller.smallroutine.controller.teacher;

import com.wuxue.api.controller.smallroutine.client.teacher.EmployeeClient;
import com.wuxue.api.controller.smallroutine.client.wxOpenid.DatavalidClient;
import com.wuxue.api.controller.smallroutine.controller.BaseController;
import com.wuxue.model.Datavalid;
import com.wuxue.model.Employee;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 课程
 */
@Controller
@RequestMapping(value = "/teacher")
public class EmployeeController2 extends BaseController {

    @Autowired
    private EmployeeClient employeeClient;

    @Autowired
    DatavalidClient datavalidClient;

    //判断用户是否登录
    public  boolean validUserbingDing(String string){
        boolean flag=false;
        String []strs=string.split(",");
        if(strs.length<2){
            return flag;
        }
        Datavalid datavalid=new Datavalid();
        datavalid.setId(strs[0]);
        Response<Datavalid> response=datavalidClient.findByPrimaryKey(datavalid);
        if(response.getData()!=null){
            String  str=response.getData().getId()+","+response.getData().getPkempstu();
            if(str.equals(string)){
                flag=true;
            }
        }

        return  flag;
    }


    /**
     * 教师关联的 所有课程,学生。
     * @param model
     * @param employee
     * @return
     */
    @ResponseBody
    @RequestMapping("/findteacherInfo")
    public Response findteacherInfo(HttpServletRequest request, Model model, Employee employee) {
        String str=request.getParameter("userKey");
        if(str!=null&&str!=""){
          if(validUserbingDing(request.getParameter("userKey"))){
              Response<List<Employee>> listResponse= employeeClient.findjiedian(employee);
              return  listResponse;
          }
        }
        return  Response.newResponse().USER_NULL();
    }


}
