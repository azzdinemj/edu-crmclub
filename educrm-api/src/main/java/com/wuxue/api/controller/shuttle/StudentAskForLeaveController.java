package com.wuxue.api.controller.shuttle;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wuxue.api.service.AskForLeaveService;
import com.wuxue.api.service.LinkmanService;
import com.wuxue.api.service.StudentService;
import com.wuxue.model.AskForLeave;
import com.wuxue.model.LinkmanList;
import com.wuxue.model.Student;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.common.DateTimeUtils;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jie 2018/07/10  请假
 */
@RestController
@RequestMapping(value = "api/shuttle/studentAskForLeave")
public class StudentAskForLeaveController {

    @Autowired
    private AskForLeaveService askForLeaveService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private LinkmanService linkmanService;

    /**
     *  请假列表
     * @param request
     * @param askForLeave
     * @return
     */
    @RequestMapping(value = "/findByPar",method = RequestMethod.POST)
    public Response findByPar(HttpServletRequest request, @RequestBody AskForLeave askForLeave){

        List<AskForLeave> list = askForLeaveService.findByPar(askForLeave);

        Response response = Response.newResponse();
        response.setData(list);

        return response;
    }

    /**
     * 申请请假
     * @param request
     * @param pkLinkman
     * @return
     */
    @RequestMapping(value = "/applyLeave",method = RequestMethod.POST)
    public Response applyLeave(HttpServletRequest request, String pkLinkman){

        List<Student> studentList = studentService.selectByParent(pkLinkman);

        Response response = Response.newResponse();
        response.setData(studentList);

        return response;
    }

    /**
     *  请假详情
     * @param request
     * @param pkAskForLeave
     * @return
     */
    @RequestMapping(value = "/findByKey",method = RequestMethod.POST)
    public Response findByKey(HttpServletRequest request, String pkAskForLeave){

        AskForLeave askForLeave = askForLeaveService.findByKey(pkAskForLeave);

        Response response = Response.newResponse();
        response.setData(askForLeave);

        return response;
    }

    /**
     * 取消请假申请
     * @param request
     * @param pkAskForLeave
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Response delete(HttpServletRequest request, @RequestBody String pkAskForLeave){

        Response response = askForLeaveService.deleteByLeaveId(pkAskForLeave);
        return response;
    }

    /**
     * 提交请假申请
     * @param request
     * @param askForLeave
     * @return
     */
    @RequestMapping(value = "/applyLeaveSubmit",method = RequestMethod.POST)
    public Response applyLeaveSubmit(HttpServletRequest request, @RequestBody AskForLeave askForLeave){

//        List<String> ids1 = ids.getIds();

        List<String> ids = new ArrayList<>();
        if(askForLeave.getMap() != null) {
//            Object o = askForLeave.getMap().get(Light.STUDENT_LIST);
//            List<String> strings = JSON.parseArray(o, String.class);
//            JSONArray.t
//            if (strings != null){
//
//            }
            List<Student> studentList = DataUtils.objectToList(askForLeave.getMap().get(Light.STUDENT_LIST), Student.class);
            if (studentList == null){
                studentList = new ArrayList<>();
            }else {
                for (Student student : studentList) {
                    if (student.getPkStudent() != null && !student.getPkStudent().equals(""))
                    ids.add(student.getPkStudent());
                }
            }
        }

        askForLeave.setStartTime(DateTimeUtils.getStringToDate(askForLeave.getStartTimeStr(),"yyyy-MM-dd hh:mm"));
        askForLeave.setEndTime(DateTimeUtils.getStringToDate(askForLeave.getEndTimeStr(),"yyyy-MM-dd hh:mm"));
        Response response = askForLeaveService.applyLeaveSubmit(askForLeave,ids);
        return response;
    }

}
