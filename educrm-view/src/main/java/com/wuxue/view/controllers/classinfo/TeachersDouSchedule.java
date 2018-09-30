package com.wuxue.view.controllers.classinfo;

import com.wuxue.model.ClassTime;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.product.ScheduleClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IQueryController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/classinfo/teachersDouSchedule")
public class TeachersDouSchedule extends BaseController implements IQueryController<ClassTime, String> {

    @Autowired
    private ScheduleClient scheduleClient;
    @Override
    public String query(HttpServletRequest request, Model model, ClassTime classTime) {


        Response<List<ClassTime>> listResponse = scheduleClient.findEmpDoubleSchodule(classTime);
        List<ClassTime> list = listResponse.getData();

        model.addAttribute("list",list);

        return "/classinfo/teacherDoubleSchedule";
    }
}
