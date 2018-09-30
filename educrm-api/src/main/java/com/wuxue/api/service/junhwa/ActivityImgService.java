package com.wuxue.api.service.junhwa;

import com.wuxue.model.junhwa.ActivityImg;

import java.util.List;
import java.util.Map;

/**
 * 班级活动图片service
 * @author tly
 * @date 2018-08-03
 */
public interface ActivityImgService {


    /**
     * 通过班级活动集合返回活动对应的图片集合
     * @param activityIds
     * @return
     */
    Map<String, List<ActivityImg>> getActivityIdAndImgListMap(List<String> activityIds);

}
