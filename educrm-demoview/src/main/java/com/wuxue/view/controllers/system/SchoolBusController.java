package com.wuxue.view.controllers.system;

import com.wuxue.model.Classinfo;
import com.wuxue.model.SchoolBus;
import com.wuxue.model.Student;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ClassInfoClient;
import com.wuxue.view.client.system.SchoolBusClient;
import com.wuxue.view.client.system.SchoolBusStudentClient;
import com.wuxue.view.client.system.SysUserClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 校车管理
 */
@Controller
@RequestMapping(value = "/system/schoolBus")
public class SchoolBusController extends BaseController
        implements IQueryController<SchoolBus, String>, ISaveController<SchoolBus, String>, IQueryByPagingController<SchoolBus, Map<String, Object>>,
        ICreateController<SchoolBus, String>, IEditController<SchoolBus, String>, IDeleteController<SchoolBus, String> {


    @InitBinder("schoolBus")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("bus.");
    }

    @Autowired
    private SchoolBusClient schoolBusClient;
    @Autowired
    private SysUserClient sysUserClient;
    @Autowired
    private SysAutoCodeUtils sysAutoCodeUtils;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    private ClassInfoClient classInfoClient;
    @Autowired
    private SchoolBusStudentClient schoolBusStudentClient;

    /**
     * 校车列表页面
     *
     * @param model
     * @return
     * @throws ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, SchoolBus schoolBus) {
//        Response<List<SchoolBus>> listResponse = schoolBusClient.find(schoolBus);
//
//        model.addAttribute("dormlist",listResponse.getData() );
//        String jsonString = JSON.toJSONString(listResponse.getData());
//        model.addAttribute("dorm",jsonString);

        return "/system/schoolBusList";
    }


    @Override
    public String create(HttpServletRequest request, Model model, SchoolBus schoolBus) {
        String pkdomain = SessionCache.getPkdomain();
        schoolBus.setPkDomain(pkdomain);
        model.addAttribute("schoolBus", schoolBus);
//        List<SysDictValues> sysDictValue = sysDictUtils.getSysDictValue(SysDicEmnuUtils.SCHOOL_BUS_KIND);
//        model.addAttribute("schoolBusKind",sysDictValue);
        initReportPageAttribute(model, SysDicEmnuUtils.SCHOOL_BUS_KIND);

        return "/system/schoolBus";
    }

    private void initReportPageAttribute(Model model, String pkSysDictValues) {
        sysDictUtils.setModeAttribute(model, "schoolBusKind", pkSysDictValues);
    }

    @Override
    public String edit(HttpServletRequest request, Model model, SchoolBus schoolBus) {
        Response<SchoolBus> byPrimaryKey = schoolBusClient.findByPrimaryKey(schoolBus);
        model.addAttribute("schoolBus", byPrimaryKey.getData());
//        List<SysDictValues> sysDictValue = sysDictUtils.getSysDictValue(SysDicEmnuUtils.SCHOOL_BUS_KIND);
//        model.addAttribute("schoolBusKind",sysDictValue);
        initReportPageAttribute(model, SysDicEmnuUtils.SCHOOL_BUS_KIND);
        Map<String, Object> map = byPrimaryKey.getData().getMap();
        List<Student> studentList = DataUtils.objectToList(map.get(Light.STUDENT_LIST), Student.class);
        model.addAttribute("studentList",studentList);
        return "/system/schoolBus";
    }


    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, SchoolBus schoolBus) {
        return schoolBusClient.delete(schoolBus.getPkSchoolBus());
    }

    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, SchoolBus schoolBus) {
        if (schoolBus.getPkSchoolBus() == null || schoolBus.getPkSchoolBus().equals("")) {
            schoolBus.setCreator(SessionCache.getUserCode());
            schoolBus.setPkSchoolBus(GuidUtils.getGuid());
        }
        schoolBus.setModifier(SessionCache.getUserCode());
        if (schoolBus.getCurrentNum() == null || "".equals(schoolBus.getCurrentNum())) {
            schoolBus.setCurrentNum(0);
        }

        return schoolBusClient.save(schoolBus);
    }

    @RequestMapping(value = "/checkIn")
    public String checkIn(HttpServletRequest request, Model model, SchoolBus schoolBus) {
        Response<SchoolBus> byPrimaryKey = schoolBusClient.findByPrimaryKey(schoolBus);
        model.addAttribute("schoolBus", byPrimaryKey.getData());


        Classinfo classinfo = new Classinfo();
        classinfo.setPageSize(10000);
        Response<List<Classinfo>> listResponse = classInfoClient.find(classinfo);
        model.addAttribute("classinfo", listResponse.getData());
        return "/system/schoolBusStudent";

    }


    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, SchoolBus schoolBus, Integer sEcho, Integer start, Integer length) {
        schoolBus.setPageNo((start / length) + 1);
        schoolBus.setPageSize(length);

        Response<List<SchoolBus>> listResponse = schoolBusClient.find(schoolBus);
        List<SchoolBus> data = listResponse.getData();
        if (data == null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(), listResponse.getTotal(), data);
    }


}
