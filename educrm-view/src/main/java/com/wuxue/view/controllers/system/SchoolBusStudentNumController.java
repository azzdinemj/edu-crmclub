package com.wuxue.view.controllers.system;

import com.alibaba.fastjson.JSON;
import com.wuxue.model.*;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.SchoolBusStudentNumClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping(value = "/system/schoolBusStudentNum")
public class SchoolBusStudentNumController extends BaseController
        implements IQueryController<SchoolBusStudentNum, String>,IQueryByPagingController<SchoolBusStudentNum,Map<String,Object>> {


    @Autowired
    private SchoolBusStudentNumClient schoolBusStudentNumClient;

    /**
     * 列表页面
     *
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, SchoolBusStudentNum schoolBusStudentNum) {

//        schoolBusStudentNumClient.find(schoolBusStudentNum);

        return "/system/schoolBusStudentNumList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, SchoolBusStudentNum schoolBusStudentNum, Integer sEcho, Integer start, Integer length) {
        schoolBusStudentNum.setPageNo((start/length)+1);
        schoolBusStudentNum.setPageSize(length);
        int sumStudent = 0;
        Response<List<SchoolBusStudentNum>> listResponse = schoolBusStudentNumClient.find(schoolBusStudentNum);
        List<SchoolBusStudentNum> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        if (data != null && data.size() >0){
            for (SchoolBusStudentNum datum : data) {
                if (datum.getStunum() != null){
                    sumStudent +=datum.getStunum();
                }
            }
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }



}
