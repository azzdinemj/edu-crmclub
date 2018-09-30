package com.wuxue.view.controllers.classinfo;

import com.alibaba.fastjson.JSON;
import com.wuxue.model.ClassinfoStudent;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ClassInfoStudentClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.ClasStuListUtils;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/classinfo/classinfoStudent")
public class ClassInfoStudentController extends BaseController
        implements IQueryController<ClassinfoStudent, String>, ISaveController<ClassinfoStudent, String>,
        ICreateController<ClassinfoStudent, String>, IEditController<ClassinfoStudent, String>, IDeleteController<ClassinfoStudent, String> {

    @Autowired
    private ClassInfoStudentClient classinfoStudentClient;

    @InitBinder("classinfoStudent")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("clas.");
    }

    /**
     * 班级学生列表页面
     *
     * @param model
     * @return
     * @throws
     */
    @Override
    public String query(HttpServletRequest request, Model model, ClassinfoStudent classinfoStudent) {
        /*Response<List<Domain>> listResponse = sysUserClient.find(Domain);

        model.addAttribute("list",listResponse.getData() );*/
        return "/classinfo/classInfoList";
    }

    /**
     * 修改页面
     *
     * @param classinfoStudent
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, ClassinfoStudent classinfoStudent) {
        Response<ClassinfoStudent> byPrimaryKey = classinfoStudentClient.findByPrimaryKey(classinfoStudent);
        model.addAttribute("classinfoStudent", byPrimaryKey.getData());
        return "/classinfo/editClassinfoStudent";
    }



    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, ClassinfoStudent classinfoStudent) {
//        String oldPkClassinfo = request.getParameter("oldPkClassinfo");
//        classinfoStudent.put(Light.OLD_PK_CLASSINFO,oldPkClassinfo);
        classinfoStudent.setCreator(SessionCache.getUserCode());
        classinfoStudent.setModifier(SessionCache.getUserCode());

        String response = classinfoStudentClient.save(classinfoStudent);
        return response;

    }
    @ResponseBody
    @RequestMapping(value = "/saveall",method = RequestMethod.POST)
    public String save(HttpServletRequest request, Model model, ClassinfoStudent classinfoStudent,ClasStuListUtils clasStuListUtils) {

       List<String> pkStudents = clasStuListUtils.getPkStudents();
       List<ClassinfoStudent> classinfoStudentList = new ArrayList<>();
       ClassinfoStudent clsinfostu ;
       if(pkStudents != null && pkStudents.size() >0){
           for (String pkStudent : pkStudents) {
               clsinfostu = new ClassinfoStudent();
               clsinfostu.setPkStudent(pkStudent);
               clsinfostu.setCreator(SessionCache.getUserCode());
               clsinfostu.setModifier(SessionCache.getUserCode());
               clsinfostu.setPkClassinfo(classinfoStudent.getPkClassinfo());
               classinfoStudentList.add(clsinfostu);

           }
       }

       classinfoStudent.put(Light.CLASSINFO_STUENT,classinfoStudentList);

        return classinfoStudentClient.save(classinfoStudent);

    }

    /**
     * 学生入班
     * @param request
     * @param model
     * @param classinfoStudent
     * @param clasStuListUtils
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/savell",method = RequestMethod.POST)
    public String saveall(HttpServletRequest request, Model model, ClassinfoStudent classinfoStudent, ClasStuListUtils clasStuListUtils) {

        classinfoStudent.put(Light.CLASSINFO_STUENT,clasStuListUtils);
        String response = classinfoStudentClient.save(classinfoStudent);
        return response;

    }

    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, ClassinfoStudent classinfoStudent) {
        return "/classinfo/addClassinfoStudent";
    }

    /**
     * 删除
     *
     * @param classinfoStudent
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, ClassinfoStudent classinfoStudent) {
        String response = classinfoStudentClient.delete(classinfoStudent.getPkClassinfo());
        return response;
    }

    /**
     * 学生转班
     * @param request
     * @param model
     * @param classinfoStudent
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/studentreturnclass",method = RequestMethod.POST)
    public String studentReturnClass(HttpServletRequest request, Model model, ClassinfoStudent classinfoStudent) {
        String oldPkClassinfo = request.getParameter("oldPkClassinfo");
        classinfoStudent.put(Light.OLD_PK_CLASSINFO,oldPkClassinfo);
        classinfoStudent.setCreator(SessionCache.getUserCode());
        classinfoStudent.setModifier(SessionCache.getUserCode());

        String response = classinfoStudentClient.studentReturnClass(classinfoStudent);
        return response;

    }


}
