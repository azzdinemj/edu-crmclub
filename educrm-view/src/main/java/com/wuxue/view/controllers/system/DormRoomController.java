package com.wuxue.view.controllers.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wuxue.model.*;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ClassInfoClient;
import com.wuxue.view.client.system.DormRoomClient;
import com.wuxue.view.client.system.DormRoomStudentClient;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 宿舍管理
 */
@Controller
@RequestMapping(value = "/system/dormRoom")
public class DormRoomController extends BaseController
        implements IQueryController<DormRoom,String>,ISaveController<DormRoom,String>,IQueryByPagingController<DormRoom,Map<String,Object>>,
        ICreateController<DormRoom,String>,IEditController<DormRoom,String>,IDeleteController<DormRoom,String> {


    @InitBinder("dormRoom")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("room.");
    }

    @Autowired
    private DormRoomClient dormRoomClient;
    @Autowired
    private SysUserClient sysUserClient;
    @Autowired
    private SysAutoCodeUtils sysAutoCodeUtils;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    private ClassInfoClient classInfoClient;
    @Autowired
    private DormRoomStudentClient dormRoomStudentClient;
    @Autowired
    private EmployeeUtils employeeUtils;

    /**
     * 宿舍列表页面
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, DormRoom dormRoom)  {
//        Response<List<DormRoom>> listResponse = dormRoomClient.find(dormRoom);
//
//        model.addAttribute("dormlist",listResponse.getData() );
//        String jsonString = JSON.toJSONString(listResponse.getData());
//        model.addAttribute("dorm",jsonString);



        return "/system/dormRoomList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, DormRoom dormRoom, Integer sEcho, Integer start, Integer length) {
        dormRoom.setPageNo((start/length)+1);
        dormRoom.setPageSize(length);

        Response<List<DormRoom>> listResponse = dormRoomClient.find(dormRoom);
        List<DormRoom> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }



    @Override
    public String create(HttpServletRequest request, Model model, DormRoom dormRoom) {
        String sysAutocode = sysAutoCodeUtils.getSysAutocode(AutoCodeEnum.DORMROOM_CODE);
        String pkdomain = SessionCache.getPkdomain();
        dormRoom.setPkDomain(pkdomain);
        dormRoom.setCode(sysAutocode);
        model.addAttribute("dormRoom",dormRoom);
        List<SysDictValues> sysDictValue = sysDictUtils.getSysDictValue(SysDicEmnuUtils.DORMCLASS);
        model.addAttribute("drclass",sysDictValue);

        List<Employee> employeeByJobPost = employeeUtils.getEmployeeByJobPost(JobPostEnum.LIFE_EMPLOYEE);
        model.addAttribute("housemasters",employeeByJobPost);

        return "/system/dormRoom";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, DormRoom dormRoom) {
        return dormRoomClient.delete(dormRoom.getPkDormRoom());


    }

    @Override
    public String edit(HttpServletRequest request, Model model, DormRoom dormRoom) {
        Response<DormRoom> byPrimaryKey = dormRoomClient.findByPrimaryKey(dormRoom);
        model.addAttribute("dormRoom",byPrimaryKey.getData());
        List<SysDictValues> sysDictValue = sysDictUtils.getSysDictValue(SysDicEmnuUtils.DORMCLASS);
        model.addAttribute("drclass",sysDictValue);
        DormRoom data = byPrimaryKey.getData();
        Map<String, Object> map = data.getMap();
        List<Employee> employeeList = DataUtils.objectToList(map.get(Light.EMPLOYEE), Employee.class);
        List<Student> studentList = DataUtils.objectToList(map.get(Light.STUDENT_LIST), Student.class);
        if(employeeList != null && employeeList.size()>0){
            model.addAttribute("employeeList",employeeList);
        }
        if (studentList != null && studentList.size() > 0){
            model.addAttribute("studentList",studentList);
        }

        String strings = DataUtils.objectToObject(map.get(Light.EMPLOYEE_LIFE), String.class);
        if (strings== null){
            strings="";
        }
        model.addAttribute("lifes",strings);

        List<Employee> employeeByJobPost = employeeUtils.getEmployeeByJobPost(JobPostEnum.LIFE_EMPLOYEE);
        model.addAttribute("housemasters",employeeByJobPost);

        return "/system/dormRoom";
    }




    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, DormRoom dormRoom) {
        if(dormRoom.getPkDormRoom() == null || dormRoom.getPkDormRoom().equals("")){
            dormRoom.setCreator(SessionCache.getUserCode());
            dormRoom.setPkDormRoom(GuidUtils.getGuid());
        }
        dormRoom.setModifier(SessionCache.getUserCode());

        List<String> pkEmployees = new ArrayList<>();
        String housemaster = request.getParameter("housemaster");
        if(housemaster != null && housemaster.length() >0){
            String[] split = housemaster.split(",");
            if (split.length >0){
                for (int i = 0;i <split.length;i++){
                    if (split[i] != ""){
                        pkEmployees.add(split[i]);
                    }
                }
            }
        }

        dormRoom.put(Light.EMPLOYEE,pkEmployees);
        return dormRoomClient.save(dormRoom);
    }

    @RequestMapping(value = "/checkIn")
    public String checkIn(HttpServletRequest request, Model model, DormRoom dormRoom){
        Response<DormRoom> byPrimaryKey = dormRoomClient.findByPrimaryKey(dormRoom);
        model.addAttribute("dormRoom",byPrimaryKey.getData());

        if (byPrimaryKey.getData().getKind()==0){
            Classinfo classinfo = new Classinfo();
            classinfo.setPageSize(10000);
            Response<List<Classinfo>> listResponse = classInfoClient.find(classinfo);
            model.addAttribute("classinfo",listResponse.getData());
            return "/system/dormRoomStudent";
        }

        return "/system/dormRoomEmployee";
    }

    @ResponseBody
    @RequestMapping(value = "/saveAll",method = RequestMethod.POST)
    public String saveAll(HttpServletRequest request, Model model, DormRoom dormRoom) {
//        String pkStudents = request.getParameter("pkStudents");
//        List<String> list = JSON.parseArray(pkStudents).toJavaList(String.class);
//        List<Student> students = new ArrayList<>();
//
//        if(list.size()>0){
//            for (String s : list) {
//                Student student = new Student();
//                student.setPkStudent(s);
//                students.add(student);
//            }
//        }
//        dormRoom.put(Light.STUDENT_LIST,students);
        return dormRoomClient.save(dormRoom);
    }

}
