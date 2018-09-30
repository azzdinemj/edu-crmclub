package com.wuxue.view.controllers.classinfo;

import com.wuxue.model.ClassTime;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ClassTimeClient;
import com.wuxue.view.client.product.ScheduleClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IEditController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/classinfo/teacherschedule")
public class TeacherScheduleController extends BaseController
        implements IQueryController<ClassTime, String>,IEditController<ClassTime, String> {

    @Autowired
    private ClassTimeClient classTimeClient;
    @Autowired
    private ScheduleClient scheduleClient;

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
    public String query(HttpServletRequest request, Model model, ClassTime classTime) {


//        String pkClassinfo = request.getParameter("pkClassinfo");
//        classTime.put(Light.CLASSINFO,"201817456867753551");

        String employee = SessionCache.getEmployee();
        classTime.put(Light.EMPLOYEE,employee);
//        classTime.put(Light.EMPLOYEE,"201819986279409399");


        Response<List<ClassTime>> listResponse = scheduleClient.findClassSchodule(classTime);
        List<ClassTime> list = listResponse.getData();

        model.addAttribute("list",list);


        return "/classinfo/classinfoSchedule";
    }

    /**
     * 修改页面
     *
     * @param classTime
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, ClassTime classTime) {


        return "/classinfo/editClassinfoStudent";
    }





}
