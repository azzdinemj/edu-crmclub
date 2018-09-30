package com.wuxue.api.controller.smallroutine.controller.student;

import com.wuxue.api.controller.smallroutine.client.student.StudentClient;
import com.wuxue.api.controller.smallroutine.client.wxOpenid.DatavalidClient;
import com.wuxue.model.Datavalid;
import com.wuxue.model.Student;
import com.wuxue.utils.contract.Response;
import com.wuxue.api.controller.smallroutine.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @Description: student控制
* @author wanghao
* @date  9:27 2018/3/12
* @version V1.0
*/
@Controller
@RequestMapping(value="/student")
public class StudentController2 extends BaseController{

    @Autowired
    private StudentClient studentClient;

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
     * 学生关联的 所有课程。
     * @param model
     * @param student
     * @return
     */
    @ResponseBody
    @RequestMapping("/findstudentInfo")
    public Response findstudentInfo(HttpServletRequest request, Model model, Student student) {
        String str=request.getParameter("userKey");
        if(str!=null&&str!=""){
            if(validUserbingDing(request.getParameter("userKey"))){
                Response<List<Student>> listResponse= studentClient.findjiedian(student);
                return  listResponse;
            }
        }
        return  Response.newResponse().USER_NULL();

    }


}
