package com.wuxue.view.controllers.classinfo;

import com.wuxue.model.junhwa.AwardsStudent;
import com.wuxue.model.junhwa.RaceAwards;
import com.wuxue.model.junhwa.RaceRecord;
import com.wuxue.model.junhwa.RaceRecord;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.activity.RaceRecordClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.DateTimeUtils;
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
 * 班级升班
 */

@Controller
@RequestMapping(value = "/classinfo/raceRecord")
public class RaceRecordController extends BaseController
        implements IQueryController<RaceRecord, String>, ISaveController<RaceRecord, String>,IQueryByPagingController<RaceRecord,Map<String,Object>>,
        ICreateController<RaceRecord, String>, IEditController<RaceRecord, String>, IDeleteController<RaceRecord, String> {

    @Autowired
    private RaceRecordClient raceRecordClient;

    @InitBinder("raceRecord")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("act.");
    }

    /**
     * 班级活动列表页面
     *
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, RaceRecord raceRecord) {
        /*Response<List<Domain>> listResponse = sysUserClient.find(Domain);

        model.addAttribute("list",listResponse.getData() );*/
        return "/raceRecord/classOnList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, RaceRecord raceRecord, Integer sEcho, Integer start, Integer length) {
        raceRecord.setPageNo((start/length)+1);
        raceRecord.setPageSize(length);

        Response<List<RaceRecord>> listResponse = raceRecordClient.find(raceRecord);
        List<RaceRecord> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    /**
     * 修改页面
     *
     * @param raceRecord
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, RaceRecord raceRecord) {
        Response<RaceRecord> byPrimaryKey = raceRecordClient.findRaceRecordInfoById(raceRecord);

        RaceRecord data = byPrimaryKey.getData();
        if (data != null){
            List<RaceAwards> raceAwardsList = data.getRaceAwardsList();
            if (raceAwardsList != null && raceAwardsList.size() >0){
                for (RaceAwards raceAwards : raceAwardsList) {

                    List<AwardsStudent> awardsStudentList = raceAwards.getAwardsStudentList();
                    String studentNames = "";
                    String studentIds = "";
                    if (awardsStudentList.size()>0){
                        for (int i =0; i < awardsStudentList.size(); i++) {
                            if (i == awardsStudentList.size() -1){
                                studentIds = studentIds + awardsStudentList.get(i).getStudentId();
                                studentNames = studentNames + awardsStudentList.get(i).getStudentName();
                            }else {
                                studentIds = studentIds + awardsStudentList.get(i).getStudentId() + ",";
                                studentNames = studentNames + awardsStudentList.get(i).getStudentName() + ";";
                            }
                        }
                    }

                    raceAwards.setStudentIds(studentIds);
                    raceAwards.put("studentNames",studentNames);

                }
            }
        }


        model.addAttribute("raceRecord", byPrimaryKey.getData());
        return "/classinfo/raceRecord";
    }


    /**
     * 保存
     *
     * @param raceRecord
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, RaceRecord raceRecord) {

        raceRecord.setRaceDate(DateTimeUtils.stringToDate(raceRecord.getRaceDateTime()));
//        raceRecord.setActivityTime(DateTimeUtils.stringToDate(raceRecord.getActivityTimes()));

        List<RaceAwards> raceAwardsList = raceRecord.getRaceAwardsList();
        if (raceAwardsList != null && raceAwardsList.size() > 0){
            for (RaceAwards raceAwards : raceAwardsList) {

                String studentIds = raceAwards.getStudentIds();
                String[] split = studentIds.split(",");
                if (split.length>0){
                    List<AwardsStudent> awardsStudentList = new ArrayList<>();
                    AwardsStudent awardsStudent ;
                    for (String s : split) {
                        awardsStudent = new AwardsStudent();
                        awardsStudent.setStudentId(s);
                        awardsStudentList.add(awardsStudent);
                    }
                    raceAwards.setAwardsStudentList(awardsStudentList);
                }
            }
        }


        String pkClassinfo = raceRecord.getPkClassinfo();
//        String response = raceRecordClient.save(raceRecord);
        return raceRecordClient.save(raceRecord);

    }

    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, RaceRecord raceRecord) {



        model.addAttribute("raceRecord",raceRecord);


        return "/classinfo/raceRecord";
    }

    /**
     * 删除
     *
     * @param raceRecord
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, RaceRecord raceRecord) {
        String response = raceRecordClient.delete(raceRecord.getPkClassinfo());
        return response;
    }

    @RequestMapping(value = "/findStu",method = RequestMethod.GET)
    public String findStu(HttpServletRequest request,String pkClassinfo,Model model){
        String num = request.getParameter("num");
        model.addAttribute("pkClassinfo",pkClassinfo);
        model.addAttribute("num",num);
        return "/model/studentlistModelDouble";
    }



}
