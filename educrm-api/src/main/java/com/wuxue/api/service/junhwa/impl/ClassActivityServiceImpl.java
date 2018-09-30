package com.wuxue.api.service.junhwa.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuxue.api.mapper.ActivityImgMapper;
import com.wuxue.api.mapper.ClassActivityMapper;
import com.wuxue.api.mapper.ClassinfoMapper;
import com.wuxue.api.service.ClassinfoEmployeeService;
import com.wuxue.api.service.ClassinfoService;
import com.wuxue.api.service.junhwa.ActivityImgService;
import com.wuxue.api.service.junhwa.ClassActivityService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.Employee;
import com.wuxue.model.Student;
import com.wuxue.model.junhwa.ActivityImg;
import com.wuxue.model.junhwa.ActivityImgExample;
import com.wuxue.model.junhwa.ClassActivity;
import com.wuxue.model.junhwa.ClassActivityExample;
import com.wuxue.utils.contract.Response;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 班级活动service实现类
 *
 * @author tly
 * @date 2018-08-03
 */
@Service("classActivityService")
public class ClassActivityServiceImpl implements ClassActivityService {
    @Autowired
    private ClassActivityMapper classActivityMapper;
    @Autowired
    private ActivityImgMapper activityImgMapper;
    @Autowired
    private ClassinfoMapper classinfoMapper;
    @Autowired
    private ActivityImgService activityImgService;
    @Autowired
    private ClassinfoService classinfoService;
    @Autowired
    private ClassinfoEmployeeService classinfoEmployeeService;

    @Override
    public Response saveOrUpdateClassActivity(ClassActivity activity) {
        Response response = Response.newResponse();
        if (null == activity || "".equals(activity)) {
            return response.PARAMS_ISNULL();
        }
        if (null == activity.getPkClassinfo() || "".equals(activity.getPkClassinfo())) {
            return response.PARAMS_ISNULL();
        }

        String primaryKey = activity.getActivityId();
        ClassActivity oldClassActivity = null;
        if (null != primaryKey && !"".equals(primaryKey)) {
            oldClassActivity = classActivityMapper.selectByPrimaryKey(primaryKey);
        }
        try {
            if (null == oldClassActivity) {
                String uuid = GuidUtils.getGuid();
                activity.setActivityId(uuid);
                activity.setCreationDate(new Date());
                int insertResult = classActivityMapper.insert(activity);
                if (insertResult >= 0) {//活动保存成功
                    insertActivityImg(activity.getActivityImgList(), uuid);
                }
            } else {
                activity.setLasteditDate(new Date());
                int updateResult = classActivityMapper.updateByPrimaryKeySelective(activity);
                if (updateResult >= 0) {//活动修改成功
                    ActivityImgExample example = new ActivityImgExample();
                    example.createCriteria().andRelIdEqualTo(activity.getActivityId());
                    int delectResult = activityImgMapper.deleteByExample(example);
                    if (delectResult >= 0) {
                        insertActivityImg(activity.getActivityImgList(), activity.getActivityId());
                    }
                }
            }
        } catch (Exception e) {
            return response.SAVE_FAIL(e.getMessage());
        }
        return response;
    }

    //保存图片
    private void insertActivityImg(List<ActivityImg> activityImgList, String activityId) {
        if (CollectionUtils.isNotEmpty(activityImgList)) {
            for (int i = 0; i < activityImgList.size(); i++) {
                ActivityImg activityImg = new ActivityImg();
                activityImg.setRelId(activityId);
                activityImg.setActivityImgId(GuidUtils.getGuid());
                activityImg.setImgOrder(i);
                activityImg.setImgUrl(activityImgList.get(i).getImgUrl());
                activityImg.setType(0);
                activityImg.setCreationDate(new Date());
                activityImgMapper.insert(activityImg);
            }
        }
    }

    @Override
    public Response findClassActivityInfoById(ClassActivity activity) {
        Response response = Response.newResponse();
        String activityId = activity.getActivityId();
        if (null == activityId || "".equals(activityId)) {
            return response.PARAMS_ISNULL();
        }
        ClassActivity classActivity = classActivityMapper.selectByPrimaryKey(activityId);
        ActivityImgExample example = new ActivityImgExample();
        example.createCriteria().andRelIdEqualTo(activityId);
        classActivity.setClassName(classinfoMapper.selectByPrimaryKey(classActivity.getPkClassinfo()).getCaption());
        classActivity.setActivityImgList(activityImgMapper.selectByExample(example));
        response.setData(classActivity);
        return response;
    }

    @Override
    public Response findClassActivityPageByClassInfoId(ClassActivity activity) {
        Response response = Response.newResponse();
        String classInfoId = activity.getPkClassinfo();
        if (null == classInfoId || "".equals(classInfoId)) {
            return response.PARAMS_ISNULL();
        }

        Integer pageNo = activity.getPageNo();
        Integer pageSize = activity.getPageSize();
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        ClassActivityExample example = new ClassActivityExample();
        example.setOrderByClause("activity_time desc");
        example.createCriteria().andPkClassinfoEqualTo(classInfoId);

        Page<Object> page = PageHelper.startPage(pageNo, pageSize);
        List<ClassActivity> classActivities = classActivityMapper.selectByExample(example);
        response.setData(classActivities);
        response.setTotal(page.getTotal());
        return response;
    }

    @Override
    public Response findClassActivityListByTeacherId(Employee employee) {
        Response response = Response.newResponse();
        String teacherId = employee.getPkEmployee();
        if (null == teacherId || "".equals(teacherId)) {
            return response.PARAMS_ISNULL();
        }
        //根据老师id查询班级信息
        List<String> classIds = classinfoService.getClassIdsByTeacherId(teacherId);
        classIds.addAll(classinfoEmployeeService.findClassInfoIdsByEmployeeId(teacherId));
        if (null == classIds || classIds.size() <= 0) {
            return response.ILLEGAL_REQUEST();
        }
        Integer pageNo = employee.getPageNo();
        Integer pageSize = employee.getPageSize();
        List<ClassActivity> classActivities = geClasstActivityByClassIds(classIds, pageNo, pageSize,response);
        response.setData(classActivities);
        return response;
    }

    /**
     * 根据班级id动找出班级活动
     *
     * @param classIds 班级信息
     * @param pageNo     页数
     * @param pageSize   每页显示条数
     * @return
     */
    private List<ClassActivity> geClasstActivityByClassIds(List<String> classIds, Integer pageNo, Integer pageSize,Response response) {
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        Page<Object> page = PageHelper.startPage(pageNo, pageSize);
        List<ClassActivity> classActivities = classActivityMapper.selectActivityListByIds(classIds);
        if (CollectionUtils.isNotEmpty(classActivities)) {

            List<String> activityIds = new ArrayList<>();
            for (ClassActivity classActivity : classActivities) {
                activityIds.add(classActivity.getActivityId());
            }

            Map<String, List<ActivityImg>> activityIdAndImgListMap = activityImgService.getActivityIdAndImgListMap(activityIds);
            for (ClassActivity classActivity : classActivities) {
                classActivity.setActivityImgList(activityIdAndImgListMap.get(classActivity.getActivityId()));
            }
        }
        response.setTotal(page.getTotal());
        return classActivities;
    }

    @Override
    public Response findClassActivityListByStudentId(Student student) {
        Response response = Response.newResponse();
        String studentId = student.getPkStudent();
        if (null == studentId || "".equals(studentId)) {
            return response.PARAMS_ISNULL();
        }
        List<String> classIds = classinfoService.getClassIdsByStudentId(studentId);
        if (null == classIds || classIds.size() <= 0) {
            return response.ILLEGAL_REQUEST();
        }
        Integer pageNo = student.getPageNo();
        Integer pageSize = student.getPageSize();
        List<ClassActivity> classActivities = geClasstActivityByClassIds(classIds, pageNo, pageSize,response);
        response.setData(classActivities);

        return response;
    }


}
