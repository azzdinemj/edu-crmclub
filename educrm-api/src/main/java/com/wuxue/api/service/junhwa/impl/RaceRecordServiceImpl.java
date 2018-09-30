package com.wuxue.api.service.junhwa.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuxue.api.mapper.ActivityImgMapper;
import com.wuxue.api.mapper.AwardsStudentMapper;
import com.wuxue.api.mapper.RaceAwardsMapper;
import com.wuxue.api.mapper.RaceRecordMapper;
import com.wuxue.api.service.ClassinfoService;
import com.wuxue.api.service.junhwa.ActivityImgService;
import com.wuxue.api.service.junhwa.RaceAwardsService;
import com.wuxue.api.service.junhwa.RaceRecordService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.base.ResultEntity;
import com.wuxue.model.Employee;
import com.wuxue.model.Student;
import com.wuxue.model.junhwa.*;
import com.wuxue.utils.contract.Response;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 竞赛记录service实现类
 *
 * @author tly
 * @date 2018-08-03
 */
@Service("raceRecordService")
public class RaceRecordServiceImpl implements RaceRecordService {

    @Autowired
    private RaceRecordMapper raceRecordMapper;
    @Autowired
    private ActivityImgMapper activityImgMapper;
    @Autowired
    private RaceAwardsMapper raceAwardsMapper;
    @Autowired
    private AwardsStudentMapper awardsStudentMapper;
    @Autowired
    private ClassinfoService classinfoService;
    @Autowired
    private RaceAwardsService raceAwardsService;
    @Autowired
    private ActivityImgService activityImgService;

    @Override
    public Response findRaceRecordPageByClassInfoId(RaceRecord raceRecord) {
        Response response = Response.newResponse();
        String classInfoId = raceRecord.getPkClassinfo();
        if (null == classInfoId || "".equals(classInfoId)) {
            return response.PARAMS_ISNULL();
        }

        Integer pageNo = raceRecord.getPageNo();
        Integer pageSize = raceRecord.getPageSize();
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        RaceRecordExample example = new RaceRecordExample();
        example.setOrderByClause("race_date desc");
        example.createCriteria().andPkClassinfoEqualTo(classInfoId);
        Page<Object> page = PageHelper.startPage(pageNo, pageSize);
        List<RaceRecord> raceRecords = raceRecordMapper.selectByExample(example);
        response.setData(raceRecords);
        response.setTotal(page.getTotal());
        return response;
    }

    @Override
    public Response saveOrUpdateRaceRecord(RaceRecord raceRecord) {
        Response response = Response.newResponse();
        if (null == raceRecord || "".equals(raceRecord)) {
            return response.PARAMS_ISNULL();
        }
        if (null == raceRecord.getPkClassinfo() || "".equals(raceRecord.getPkClassinfo())) {
            return response.PARAMS_ISNULL();
        }

        String primaryKey = raceRecord.getRaceId();
        RaceRecord oldRaceRecord = null;
        if (null != primaryKey && !"".equals(primaryKey)) {
            oldRaceRecord = raceRecordMapper.selectByPrimaryKey(primaryKey);
        }
        try {
            if (null == oldRaceRecord) {
                String raceId = GuidUtils.getGuid();
                raceRecord.setRaceId(raceId);
                raceRecord.setCreationDate(new Date());
                int insertResult = raceRecordMapper.insertSelective(raceRecord);
                if (insertResult >= 0) {
                    //新增图片
                    insertActivityImg(raceRecord.getActivityImgList(), raceId);
                    //新增奖项
                    insertRaceAwardsAndWinningStudent(raceRecord.getRaceAwardsList(), raceId);
                }
            } else {
                raceRecord.setLasteditDate(new Date());
                int updateResult = raceRecordMapper.updateByPrimaryKeySelective(raceRecord);
                if (updateResult >= 0) {
                    //修改图片
                    String raceId = raceRecord.getRaceId();
                    ActivityImgExample example = new ActivityImgExample();
                    example.createCriteria().andRelIdEqualTo(raceId);
                    int delectResult = activityImgMapper.deleteByExample(example);
                    if (delectResult >= 0) {
                        insertActivityImg(raceRecord.getActivityImgList(), raceId);
                    }

                    //删除奖项
                    RaceAwardsExample raceAwardsExample = new RaceAwardsExample();
                    raceAwardsExample.createCriteria().andRaceIdEqualTo(raceId);
                    List<RaceAwards> raceAwardsList = raceAwardsMapper.selectByExample(raceAwardsExample);
                    List<String> awardsIds = new ArrayList<>();
                    for (RaceAwards awards : raceAwardsList) {
                        awardsIds.add(awards.getAwardsId());
                    }

                    AwardsStudentExample awardsStudentExample = new AwardsStudentExample();
                    awardsStudentExample.createCriteria().andAwardsIdIn(awardsIds);
                    int deleteResult = awardsStudentMapper.deleteByExample(awardsStudentExample);
                    if (deleteResult >= 0) {
                        raceAwardsMapper.deleteByExample(raceAwardsExample);
                    }
                    //修改奖项
                    insertRaceAwardsAndWinningStudent(raceRecord.getRaceAwardsList(), raceId);

                }
            }

        } catch (Exception e) {
            return response.SAVE_FAIL(e.getMessage());
        }
        return response;
    }

    //保存图片
    private void insertActivityImg(List<ActivityImg> activityImgList, String raceId) {
        if (CollectionUtils.isNotEmpty(activityImgList)) {
            for (int i = 0; i < activityImgList.size(); i++) {
                ActivityImg activityImg = new ActivityImg();
                activityImg.setRelId(raceId);
                activityImg.setActivityImgId(GuidUtils.getGuid());
                activityImg.setImgOrder(i);
                activityImg.setImgUrl(activityImgList.get(i).getImgUrl());
                activityImg.setType(1);
                activityImg.setCreationDate(new Date());
                activityImgMapper.insertSelective(activityImg);
            }
        }
    }

    //保存奖项
    private void insertRaceAwardsAndWinningStudent(List<RaceAwards> raceAwardsList, String raceId) {
        if (CollectionUtils.isNotEmpty(raceAwardsList)) {
            for (int i = 0; i < raceAwardsList.size(); i++) {
                RaceAwards raceAwards = raceAwardsList.get(i);
                String awardsId = GuidUtils.getGuid();
                raceAwards.setAwardsId(awardsId);
                raceAwards.setRaceId(raceId);
                raceAwards.setCreationDate(new Date());
                int insertResult = raceAwardsMapper.insertSelective(raceAwards);
                if (insertResult >= 0) {
                    //保存获奖学生
                    List<AwardsStudent> awardsStudentList = raceAwards.getAwardsStudentList();
                    if (CollectionUtils.isNotEmpty(awardsStudentList)) {
                        for (AwardsStudent awardsStudent : awardsStudentList) {
                            awardsStudent.setAwardsStudentId(GuidUtils.getGuid());
                            awardsStudent.setAwardsId(awardsId);
                            awardsStudent.setCreationDate(new Date());
                            awardsStudentMapper.insertSelective(awardsStudent);
                        }
                    }
                }
            }
        }
    }

    @Override
    public Response findRaceRecordInfoById(RaceRecord raceRecord) {
        Response response = Response.newResponse();
        String raceId = raceRecord.getRaceId();
        if (null == raceId || "".equals(raceId)) {
            return response.PARAMS_ISNULL();
        }
        ActivityImgExample imgExample = new ActivityImgExample();
        imgExample.createCriteria().andRelIdEqualTo(raceId);
        RaceRecord raceRecordInfo = raceRecordMapper.selectRaceRecordInfoByRaceId(raceId);
        raceRecordInfo.setActivityImgList(activityImgMapper.selectByExample(imgExample));//找出图片并设置
        response.setData(raceRecordInfo);
        return response;
    }

    @Override
    public Response findRaceRecordListByTeacherId( Employee employee) {
        Response response = Response.newResponse();
        String teacherId = employee.getPkEmployee();
        if (null == teacherId || "".equals(teacherId)) {
            return response.PARAMS_ISNULL();
        }

        List<String> classIds = classinfoService.getClassIdsByTeacherId(teacherId);
        RaceRecordExample example = new RaceRecordExample();
        example.setOrderByClause("race_date desc");
        example.createCriteria().andPkClassinfoIn(classIds);

        Integer pageNo = employee.getPageNo();
        Integer pageSize = employee.getPageSize();
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;

        Page<Object> page = PageHelper.startPage(pageNo, pageSize);
        List<RaceRecord> raceRecords = raceRecordMapper.selectByExample(example);
//
//        if(CollectionUtils.isEmpty(raceRecords)){
//            return response;
//        }
//
//        List<String> raceIds = new ArrayList<>();
//        for(RaceRecord raceRecord:raceRecords){
//            raceIds.add(raceRecord.getRaceId());
//        }
//
//        Map<String, List<RaceAwards>> raceIdAndAwardsMap = raceAwardsService.getRaceIdAndAwardsMap(raceIds);
//        Map<String, List<ActivityImg>> activityIdAndImgListMap = activityImgService.getActivityIdAndImgListMap(raceIds);
//        for(RaceRecord raceRecord:raceRecords){
//            String raceId = raceRecord.getRaceId();
//            raceRecord.setRaceAwardsList(raceIdAndAwardsMap.get(raceId));
//            raceRecord.setActivityImgList(activityIdAndImgListMap.get(raceId));
//        }

        response.setData(raceRecords);
        response.setTotal(page.getTotal());
        return response;
    }

    @Override
    public Response findRaceRecordListByStudentId(Student student) {
        Response response = Response.newResponse();
        String studentId = student.getPkStudent();
        if (null == studentId || "".equals(studentId)) {
            return response.PARAMS_ISNULL();
        }
        Integer pageNo = student.getPageNo();
        Integer pageSize = student.getPageSize();
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;

        Page<Object> page = PageHelper.startPage(pageNo, pageSize);
        List<ResultEntity> entities = raceRecordMapper.selectStudentAwardsListByStudentId(studentId);
        if (CollectionUtils.isEmpty(entities)) {
            return response;
        }
        response.setData(entities);
        response.setTotal(page.getTotal());
        return response;
    }
}
