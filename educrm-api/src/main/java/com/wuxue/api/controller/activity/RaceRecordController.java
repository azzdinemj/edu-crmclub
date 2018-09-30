package com.wuxue.api.controller.activity;

import com.wuxue.api.service.junhwa.RaceRecordService;
import com.wuxue.model.Employee;
import com.wuxue.model.Student;
import com.wuxue.model.junhwa.RaceRecord;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 竞赛记录
 * @author tly
 * @date 2018-08-08
 */
@RestController
@RequestMapping(value = "api/activity/raceRecord")
public class RaceRecordController {
    @Autowired
    private RaceRecordService raceRecordService;

    /**
     * 保存或修改竞赛记录
     * @param request
     * @return
     */
    @RequestMapping(value = "/saveorupdate",method = RequestMethod.POST)
    public Response saveOrUpdateRaceRecord(@RequestBody Request<RaceRecord> request){
        return raceRecordService.saveOrUpdateRaceRecord(request.getData());
    }

    /**
     * 查询竞赛记录详情
     * @param request
     * @return
     */
    @RequestMapping(value = "/findinfo",method = RequestMethod.POST)
    public Response findRaceRecordInfoById(@RequestBody  Request<RaceRecord> request){
        return raceRecordService.findRaceRecordInfoById(request.getData());
    }

    /**
     * 查询竞赛记录列表
     * @param request
     * @return
     */
    @RequestMapping(value = "/findlist",method = RequestMethod.POST)
    public Response findRaceRecordListByClassInfoId(@RequestBody  Request<RaceRecord> request){
        return raceRecordService.findRaceRecordPageByClassInfoId(request.getData());
    }

    //*********教师端*********

    /**
     * 根据教师id查询竞赛记录列表分页
     * @param employee
     * @return 竞赛记录列表
     */
    @RequestMapping(value = "/findRaceRecordListByTeacherId",method = RequestMethod.POST)
    public Response findRaceRecordListByTeacherId(@RequestBody Employee employee){
        return raceRecordService.findRaceRecordListByTeacherId(employee);
    }

    //***********家长端***********
    /**
     * 根据学生id查询竞赛记录列表分页
     * @param student
     * @return 竞赛记录列表
     */
    @RequestMapping(value = "/findRaceRecordListByStudentId",method = RequestMethod.POST)
    public Response findRaceRecordListByStudentId(@RequestBody Student student){
        return raceRecordService.findRaceRecordListByStudentId(student);
    }


}

