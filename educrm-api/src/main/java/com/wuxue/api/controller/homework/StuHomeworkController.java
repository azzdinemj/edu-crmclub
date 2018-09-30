package com.wuxue.api.controller.homework;

import com.wuxue.api.service.HomeworkService;
import com.wuxue.api.service.SysDictValuesService;
import com.wuxue.base.KeyValue;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Rogue on 2018/01/02.
 */
@RestController
@RequestMapping(value = "api/homework/stuHomework")
public class StuHomeworkController {

    @Autowired
    private HomeworkService homeworkService;
    @Autowired
    private SysDictValuesService sysDictValuesService;

    @InitBinder("raceRecord")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("act.");
    }


    /**
     * 学生科目作业列表
     * @param request
     * @param pkStudent
     * @return
     */
    @RequestMapping(value = "/findStuWork",method = RequestMethod.POST)
    public Response findStuWork(HttpServletRequest request,String pkStudent,String workType){

        Response response = homeworkService.findStuWork(pkStudent,workType);


        return response;
    }
    /**
     * 作业详情
     * @param request
     * @param pkHomework
     * @return
     */
    @RequestMapping(value = "/findBykey",method = RequestMethod.POST)
    public Response findBykey(HttpServletRequest request,String pkHomework){

        Response response = homeworkService.findBykey(pkHomework);


        return response;
    }

//    @RequestMapping(value = "/saveHome",method = RequestMethod.POST)
//    public Response saveHome(HttpServletRequest request,Homework homework){
//
//        Response response = homeworkService.save(homework);
//
//
//        return response;
//    }

    /**
     * 学生作业类别列表
     * @param request
     * @param pkStudent
     * @return
     */
    @RequestMapping(value = "/findWorkType",method = RequestMethod.POST)
    public Response findWorkType(HttpServletRequest request,String pkStudent){

        List<KeyValue> list = sysDictValuesService.findWorkType(pkStudent);

        Response response = Response.newResponse();
        response.setData(list);

        return response;
    }



}
