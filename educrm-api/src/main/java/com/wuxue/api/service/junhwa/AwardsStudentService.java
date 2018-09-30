package com.wuxue.api.service.junhwa;

import com.wuxue.model.junhwa.AwardsStudent;

import java.util.List;
import java.util.Map;

/**
 * 竞赛学生奖项service
 * @author tly
 * @date 2018-08-03
 */
public interface AwardsStudentService {

    /**
     * 通过奖项id获取获奖的学生信息
     * @param awardsIds
     * @return
     */
    Map<String, List<AwardsStudent>> getAwardsIdAndStudentMap(List<String> awardsIds);

}