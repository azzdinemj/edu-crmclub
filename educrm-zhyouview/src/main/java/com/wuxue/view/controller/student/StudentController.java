package com.wuxue.view.controller.student;

import com.github.pagehelper.PageInfo;
import com.wuxue.model.Student;
import com.wuxue.model.StudentAssign;
import com.wuxue.utils.common.MD5Util;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.course.StudentAssignClient;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.constant.Contsants;
import com.wuxue.view.interfaces.ISaveController;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
* @Description: student控制
* @author wanghao
* @date  9:27 2018/3/12
* @version V1.0
*/
@Controller
@RequestMapping(value="/student")
public class StudentController implements ISaveController<Student,String>{

    @Autowired
    private StudentClient studentClient;
    @Autowired
    private StudentAssignClient studentAssignClient;

    /**
     * 个人设置页面
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "set",method = RequestMethod.GET)
    public  String  set(HttpSession session, Model model){
        Response<Student> response = studentClient.findByPrimaryKey(SessionCache.getPkStudent());
        Student student=response.getData();
        model.addAttribute("studentUser",student);
        return "/set";
    }

    /**
     * 修改用户信息
     * @param request
     * @param model
     * @param student
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Student student) {
        return studentClient.save(student);
    }


    /**
     * 修改密码 登录后
     * @param
     * @return
     */
    @RequestMapping(value="updatepwdSet",method = RequestMethod.POST)
    @ResponseBody
    public Response updatePwdSet(HttpServletRequest request,Student student) {
        String opwd=request.getParameter("opwd");
        String npwd=request.getParameter("newpwd");
        String strphone = SessionCache.getPhone();
        Response response = new Response();
        //验证密码与session密码是否相同
        if (SessionCache.getCheckpassword().equals(MD5Util.string2MD5(opwd))) {
            student.setPhone(strphone);
            student.setPassword(npwd);
            response = studentClient.updatepassword(student);
        } else {
            response.setMessage("原密码错误");
            response.setCode(1);
        }

        return  response;
    }


    /**
     * 学生个人中心
     * @param request
     * @param model
     * @param studentAssign
     * @return
     */
    @RequestMapping("/personalcenter")
    public String query(HttpServletRequest request, Model model, StudentAssign studentAssign) {
        String strPage=request.getParameter("pageNo");
        //查询课程列表
//        if(strPage!=null&&strPage!=""){
//            int pageNo=Integer.parseInt(strPage);
//            studentAssign.setPageNo(pageNo);
//        }else{
//            studentAssign.setPageNo(Contsants.PAGE_NO);
//        }
        //课程列表
        studentAssign.setPkStudent(SessionCache.getPkStudent());
        studentAssign.setPageSize(Contsants.PAGE_SIZE);

        Response<PageInfo<StudentAssign>> response=studentAssignClient.find(studentAssign);
        PageInfo<StudentAssign> pageInfo=response.getData();
        List<StudentAssign> studentAssignList=pageInfo.getList();

        List<Object> courseList=new ArrayList<>();
        for (StudentAssign studentAssign1:studentAssignList) {
            Object o= studentAssign1.getMap().get(Light.COURSE);
            courseList.add(o);
        }

        model.addAttribute("courseList",courseList);
        return "/personalcenter";
    }


}
