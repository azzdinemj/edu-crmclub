package com.wuxue.api.service.junhwa;

import com.wuxue.model.junhwa.ShuttleTime;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

import java.util.List;

/**
 * 家长接送
 * @author tly
 * @date 2018-07-30
 */
public interface ParentShuttleService {

    /**
     * 获取学生正式班級放学时间
     * @param request 学生id
     * @return
     */
    Response getStudentHomeTime(Request<String> request);

    List<ShuttleTime> getHomeTime(String studentId);
}
