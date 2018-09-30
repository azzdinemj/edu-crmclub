package com.wuxue.api.service.junhwa.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuxue.api.mapper.DiscussMapper;
import com.wuxue.api.mapper.EmployeeMapper;
import com.wuxue.api.mapper.LinkmanMapper;
import com.wuxue.api.mapper.StudentMapper;
import com.wuxue.api.service.ClassinfoService;
import com.wuxue.api.service.ClassinfoStudentService;
import com.wuxue.api.service.junhwa.DiscussService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.base.ResultEntity;
import com.wuxue.model.Employee;
import com.wuxue.model.Linkman;
import com.wuxue.model.Student;
import com.wuxue.model.junhwa.Discuss;
import com.wuxue.model.junhwa.DiscussExample;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 留言板serviceImpl
 *
 * @author tly
 * @data 2018-08-13
 */
@Service("discussService")
public class DiscussServiceImpl implements DiscussService {
    @Autowired
    private DiscussMapper discussMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private LinkmanMapper linkmanMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private ClassinfoService classinfoService;

    @Autowired
    private ClassinfoStudentService classinfoStudentService;

    @Override
    public Response parentLeaveMessage(Discuss discuss) {
        Response response = Response.newResponse();
        if (null == discuss) {
            return response.PARAMS_ISNULL();
        }
        if (null == discuss.getUserId() || "".equals(discuss.getUserId())) {
            return response.PARAMS_ISNULL();
        }
        if (null == discuss.getStudentId() || "".equals(discuss.getStudentId())) {
            return response.PARAMS_ISNULL();
        }
        if (null == discuss.getContent() || "".equals(discuss.getContent())) {
            return response.PARAMS_ISNULL();
        }
        if (null == discuss.getType() || "".equals(discuss.getType())) {
            return response.PARAMS_ISNULL();
        }

        discuss.setDiscussId(GuidUtils.getGuid());
        discuss.setCreateTime(new Date());
        try {
            discussMapper.insertSelective(discuss);
        } catch (Exception e) {
            return response.SAVE_FAIL(e.getMessage());
        }
        return response;
    }

    @Override
    public Response theacherReply(Discuss discuss) {
        Response response = Response.newResponse();
        if (null == discuss) {
            return response.PARAMS_ISNULL();
        }
        if (null == discuss.getUserId() || "".equals(discuss.getUserId())) {
            return response.PARAMS_ISNULL();
        }
        if (null == discuss.getStudentId() || "".equals(discuss.getStudentId())) {
            return response.PARAMS_ISNULL();
        }
        if (null == discuss.getContent() || "".equals(discuss.getContent())) {
            return response.PARAMS_ISNULL();
        }
//        if (null == discuss.getType() || "".equals(discuss.getType())) {
//            return response.PARAMS_ISNULL();
//        }
//        if (null == discuss.getParentDiscussId() || "".equals(discuss.getParentDiscussId())) {
//            return response.PARAMS_ISNULL();
//        }
        Integer teacherType = checkTeacherType(discuss.getUserId());
        if (teacherType == -1) {
            return response.SAVE_FAIL("您没有权限回复留言");
        }
        discuss.setType(teacherType);
        discuss.setParentDiscussId("0");
        discuss.setDiscussId(GuidUtils.getGuid());
        discuss.setCreateTime(new Date());
        try {
            discussMapper.insertSelective(discuss);
        } catch (Exception e) {
            return response.SAVE_FAIL(e.getMessage());
        }
        return response;
    }

    @Override
    public Response getDiscussListForMobile(Discuss discuss) {
        Response response = Response.newResponse();
//        if (null == discuss.getUserId() || "".equals(discuss.getUserId())) {
//            return response.PARAMS_ISNULL();
//        }
        if (null == discuss.getStudentId() || "".equals(discuss.getStudentId())) {
            return response.PARAMS_ISNULL();
        }
        if (null == discuss.getType() || "".equals(discuss.getType())) {
            return response.PARAMS_ISNULL();
        }

        Integer pageNo = discuss.getPageNo();
        Integer pageSize = discuss.getPageSize();
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;

        DiscussExample example = new DiscussExample();
        example.createCriteria().andStudentIdEqualTo(discuss.getStudentId())
                .andTypeEqualTo(discuss.getType());
        example.setOrderByClause("create_time desc");

        Page<Object> page = PageHelper.startPage(pageNo, pageSize);
        List<Discuss> discussList = discussMapper.selectByExample(example);


//        if (CollectionUtils.isEmpty(discussList)) {
//            return response;
//        }
//        List<String> discussIds = new ArrayList<>();
//        for (Discuss discusses : discussList) {
//            discussIds.add(discusses.getDiscussId());
//        }
//
//        Map<String, List<Discuss>> theacherReplyMap = getTheacherReplyMap(discussIds);
//        for (Discuss discusses : discussList) {
//            discusses.setTeacherReplyList(theacherReplyMap.get(discusses.getDiscussId()));
//        }
        response.setData(discussList);
        response.setTotal(page.getTotal());
        return response;
    }

    @Override
    public Response getDiscussCountListForPC(ResultEntity resultEntity) {
        Response response = Response.newResponse();
        if (null == resultEntity) {
            return response.PARAMS_ISNULL();
        }

        resultEntity.setTeacherId("201830077552911479");
        String teacherId = resultEntity.getTeacherId();
        if (null == teacherId || "".equals(teacherId)) {
            return response.PARAMS_ISNULL();
        }
        Integer teacherType = checkTeacherType(teacherId);
        if (teacherType < 0) {
            return response;
        }

        List<String> classIds = classinfoService.getClassIdsByTeacherId(teacherId);
        if (CollectionUtils.isEmpty(classIds)) {
            return response;
        }
        List<String> studentIdAll = classinfoStudentService.getStudentIdsByClassinfoIds(classIds);
        if (CollectionUtils.isEmpty(studentIdAll)) {
            return response;
        }
        resultEntity.setType(teacherType);
        resultEntity.setIds(studentIdAll);

        Integer pageNo = resultEntity.getPageNo();
        Integer pageSize = resultEntity.getPageSize();
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        Page<Object> page = PageHelper.startPage(pageNo, pageSize);
        List<ResultEntity> entities = discussMapper.selectStudentInfoAndLastTime(resultEntity);
        List<String> studentIds = new ArrayList<>();
//        if (CollectionUtils.isEmpty(entities)) {
//            return response;
//        }
        for (ResultEntity entity : entities) {
            studentIds.add(entity.getStudentId());
        }

        Map<String, Integer> leaveMsgCountMap = getStudentLeaveMsgCountMap(teacherType, studentIds);
        Map<String, Integer> replyCountMap = getTeacherReplyCountMap(teacherType, studentIds);
        for (ResultEntity entity : entities) {
            entity.setLeaveMsgCount(leaveMsgCountMap.get(entity.getStudentId()));
            entity.setReplyCount(replyCountMap.get(entity.getStudentId()));
        }
        response.setData(entities);
        response.setTotal(page.getTotal());
        return response;
    }

    /**
     * 检查老师岗位类型
     *
     * @param teacherId
     * @return
     */
    private Integer checkTeacherType(String teacherId) {
        //找出教师类型
        String jobPost = employeeMapper.selectTeacherJobPost(teacherId);
//        List<Integer> teacherTypeList = new ArrayList<>();
        Integer teacherType = -1;
        if (null != jobPost) {
            char[] chars = jobPost.toCharArray();
            if (chars.length >= 1 && chars[0] == '1') {
                teacherType = 0;
            }
            if (chars.length >= 12 && chars[11] == '1') {
                teacherType = 1;
            }
        }
        return teacherType;
    }

    /**
     * 获取学生家长的留言条数Map
     *
     * @param teacherType
     * @param studentIds
     */
    private Map<String, Integer> getStudentLeaveMsgCountMap(Integer teacherType, List<String> studentIds) {
        List<ResultEntity> entityCounts = discussMapper.selectStudentLeaveMsgCount(teacherType, studentIds);
        Map<String, Integer> map = new HashMap<>();
        if (CollectionUtils.isEmpty(entityCounts)) {
            return map;
        }
        for (ResultEntity entity : entityCounts) {
            String studentId = entity.getStudentId();
            map.put(studentId, entity.getLeaveMsgCount());
        }
        return map;
    }

    /**
     * 获取老师回复条数Map
     *
     * @param teacherType
     * @param studentIds
     */
    private Map<String, Integer> getTeacherReplyCountMap(Integer teacherType, List<String> studentIds) {
        List<ResultEntity> entityCounts = discussMapper.selectTeacherReplyCount(teacherType, studentIds);
        Map<String, Integer> map = new HashMap<>();
        if (CollectionUtils.isEmpty(entityCounts)) {
            return map;
        }
        for (ResultEntity entity : entityCounts) {
            String studentId = entity.getStudentId();
            map.put(studentId, entity.getReplyCount());
        }
        return map;
    }

    @Override
    public Response getParentNewDiscuss(Discuss discuss) {
        Response response = Response.newResponse();
        if (null == discuss.getStudentId() || "".equals(discuss.getStudentId())) {
            return response.PARAMS_ISNULL();
        }
        DiscussExample example = new DiscussExample();
        example.createCriteria().andStudentIdEqualTo(discuss.getStudentId())
                .andParentDiscussIdEqualTo("-1");
        example.setOrderByClause("create_time desc");
        example.setStartIndex(1);
        example.setPageSize(1);
        List<Discuss> discussList = discussMapper.selectByExample(example);
        if (CollectionUtils.isNotEmpty(discussList) && discussList.size() > 0) {
            Discuss resultDiscuss = discussList.get(0);
            String studentId = resultDiscuss.getStudentId();
            String parentId = resultDiscuss.getUserId();
            Student student = studentMapper.selectByPrimaryKey(studentId);
            Linkman linkman = linkmanMapper.selectByPrimaryKey(parentId);
            resultDiscuss.setUserImg(linkman.getImg());
            resultDiscuss.setUserAppellation(String.format("%s的%s", student.getCaption(), appellationFilter(linkman.getRelationship())));
            response.setData(resultDiscuss);
        }
        return response;
    }

    /**
     * 称谓过滤
     *
     * @param relationship 学生家长的关系
     * @return 称谓
     */
    public String appellationFilter(String relationship) {
        String appellation = null;
        String[] relationships = new String[]{"父", "爸", "母", "妈", "爷"};
        String[] appellations = new String[]{"爸爸", "爸爸", "妈妈", "妈妈", "爷爷"};
        for (int i = 0; i < relationships.length; i++) {
            if (relationship.indexOf(relationships[i]) > -1) {
                appellation = appellations[i];
            }
        }
        return appellation;
    }

    @Override
    public Response getDiscussListForPC(Discuss discuss) {
        Response response = Response.newResponse();
        if (null == discuss.getStudentId() || "".equals(discuss.getStudentId())) {
            return response.PARAMS_ISNULL();
        }
        DiscussExample example = new DiscussExample();
        example.createCriteria().andStudentIdEqualTo(discuss.getStudentId());
        example.setOrderByClause("create_time desc");

        Integer pageNo = discuss.getPageNo();
        Integer pageSize = discuss.getPageSize();
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        Page<Object> page = PageHelper.startPage(pageNo, pageSize);
        List<Discuss> discussList = discussMapper.selectByExample(example);
        List<String> linkmanIds = new ArrayList<>();
        List<String> teacherIds = new ArrayList<>();
        for (Discuss dEntity : discussList) {
            String userId = dEntity.getUserId();

            if (dEntity.getParentDiscussId().equals("-1")) {
                if (!linkmanIds.contains(userId)) {
                    linkmanIds.add(userId);
                }
            } else {
                if (!teacherIds.contains(userId)) {
                    teacherIds.add(userId);
                }
            }


        }
        Map<String, Linkman> linkmanMap = getLinkmanMap(linkmanIds);
        Map<String, Employee> teacherMap = getTeacherMap(teacherIds);
        Student student = studentMapper.selectByPrimaryKey(discuss.getStudentId());

        for (Discuss dEntity : discussList) {
            if (dEntity.getParentDiscussId().equals("-1")) {
                Linkman linkman = linkmanMap.get(dEntity.getUserId());
                if (linkman != null) {
                    if (linkman.getImg() != null) {
                        dEntity.setUserImg(linkman.getImg());
                    }
                    dEntity.setUserAppellation(String.format("%s的%s", student.getCaption(), appellationFilter(linkman.getRelationship())));
                }
            } else {
                Employee employee = teacherMap.get(dEntity.getUserId());
                if (employee != null) {
                    if (employee.getImg() != null) {
                        dEntity.setUserImg(employee.getImg());
                    }
                    dEntity.setUserAppellation(employee.getCaption());
                }
            }
        }
        response.setData(discussList);
        response.setTotal(page.getTotal());
        return response;
    }

    @Override
    public Response find(Request<ResultEntity> tParams) {

        Response response = Response.newResponse();
        ResultEntity data = tParams.getData();
        String message = "";
        String currentUser = tParams.getCurrentUser();
        if (currentUser == null || "".equals(currentUser)) {
            return response.PARAMS_ISNULL();
        }
//        Employee employee = employeeMapper.selectByUser("liwunan");
        Employee employee = employeeMapper.selectByUser("liwunan");
        String jobPost = employee.getJobPost();
        if (jobPost != null && jobPost.length() > 4) {
            String s = String.valueOf(jobPost.charAt(4));
            if ("1".equals(s)) {
                data.setTeacherId(employee.getPkEmployee());
//                data.setTeacherId("000001");
                List<ResultEntity> discusses = discussMapper.selectByHeaderTeacher(data);
                response.setData(discusses);
            }
        } else if (jobPost != null && jobPost.length() > 11) {
            String s = String.valueOf(jobPost.charAt(11));
            if ("1".equals(s)) {

                List<ResultEntity> discusses = discussMapper.selectByLifeEmployee(data);
                response.setData(discusses);
            }
        }


        return response;
    }


    /**
     * 获取教师回复
     *
     * @param discussIds
     * @return
     */
    private Map<String, List<Discuss>> getTheacherReplyMap(List<String> discussIds) {
        DiscussExample example = new DiscussExample();
        example.createCriteria().andParentDiscussIdIn(discussIds);
        List<Discuss> discussList = discussMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(discussList)) {
            return new HashMap<>(0);
        }

        Map<String, List<Discuss>> map = new HashMap<>();
        for (Discuss discusses : discussList) {
            String parentDiscussId = discusses.getParentDiscussId();
            List<Discuss> discussMapList = map.get(parentDiscussId);
            if (null == discussMapList) {
                discussMapList = new ArrayList<>();
            }
            discussMapList.add(discusses);
            map.put(parentDiscussId, discussMapList);
        }
        return map;
    }

    /**
     * 根据家长id找出所有家长信息
     *
     * @param linkmanIds 家长id集合
     * @return
     */
    private Map<String, Linkman> getLinkmanMap(List<String> linkmanIds) {
        Map<String, Linkman> map = new HashMap<>();
        List<Linkman> linkmens = linkmanMapper.selectLinkMansByIds(linkmanIds);
        for (Linkman linkman : linkmens) {
            map.put(linkman.getPkLinkman(), linkman);
        }
        return map;
    }

    /**
     * 根据老师id找出所有老师信息
     *
     * @param teacherIds 老师id集合
     * @return
     */
    private Map<String, Employee> getTeacherMap(List<String> teacherIds) {
        Map<String, Employee> map = new HashMap<>();
        List<Employee> employees = employeeMapper.selectTeachersByIds(teacherIds);
        for (Employee employee : employees) {
            map.put(employee.getPkEmployee(), employee);
        }
        return map;
    }

}
