package com.wuxue.api.service.junhwa.impl;

import com.wuxue.api.mapper.ClassinfoStudentMapper;
import com.wuxue.api.mapper.ShuttleTimeMapper;
import com.wuxue.api.service.junhwa.ParentShuttleService;
import com.wuxue.api.utils.DateUtil;
import com.wuxue.model.junhwa.ShuttleTime;
import com.wuxue.model.junhwa.ShuttleTimeExample;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 家长接送
 *
 * @author tly
 * @date 2018-07-30
 */
@Service("parentShuttleService")
public class ParentShuttleServiceImpl implements ParentShuttleService {

    @Autowired
    private ClassinfoStudentMapper classinfoStudentMapper;
    @Autowired
    private ShuttleTimeMapper shuttleTimeMapper;

    @Override
    public Response getStudentHomeTime(Request<String> request) {
        Response response = Response.newResponse();
        String studentId = request.getData();
        if (studentId == null || "".equals(studentId)) {
            return response.PARAMS_ISNULL();
        }
        List<ShuttleTime> shuttleTimes = getHomeTime(studentId);
        if (CollectionUtils.isEmpty(shuttleTimes) || shuttleTimes.size() <= 0) {
            return response.PARAMS_ISNULL();
        }
        ShuttleTime shuttleTime = shuttleTimes.get(0);
        if (null != shuttleTime.getStartTime()) {
            shuttleTime.setStartTime(DateUtil.changeToCurrentTime(shuttleTime.getStartTime()));
        }
        if (null != shuttleTime.getEndTime()) {
            shuttleTime.setEndTime(DateUtil.changeToCurrentTime(shuttleTime.getEndTime()));
        }
        response.setData(shuttleTime);
        return response;
    }

    public List<ShuttleTime> getHomeTime(String studentId){
        String classInfoId = classinfoStudentMapper.selectClassInfoIdByStudentId(studentId);
        ShuttleTimeExample example = new ShuttleTimeExample();
        example.createCriteria().andPkClassinfoEqualTo(classInfoId);
        List<ShuttleTime> shuttleTimes = shuttleTimeMapper.selectByExample(example);
        return shuttleTimes;
    }


}
