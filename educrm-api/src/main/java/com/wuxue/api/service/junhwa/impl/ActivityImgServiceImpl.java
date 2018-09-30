package com.wuxue.api.service.junhwa.impl;

import com.wuxue.api.mapper.ActivityImgMapper;
import com.wuxue.api.service.junhwa.ActivityImgService;
import com.wuxue.model.junhwa.ActivityImg;
import com.wuxue.model.junhwa.ActivityImgExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 班级活动图片service实现类
 *
 * @author tly
 * @date 2018-08-03
 */
@Service("activityImgService")
public class ActivityImgServiceImpl implements ActivityImgService {

    @Autowired
    private ActivityImgMapper activityImgMapper;


    @Override
    public Map<String, List<ActivityImg>> getActivityIdAndImgListMap(List<String> activityIds) {
        ActivityImgExample imgExample =new ActivityImgExample();
        imgExample.createCriteria().andRelIdIn(activityIds);
        List<ActivityImg> activityImgs = activityImgMapper.selectByExample(imgExample);
        if(CollectionUtils.isEmpty(activityImgs)){
            return new HashMap<>(0);
        }
        Map<String, List<ActivityImg>> map = new HashMap<>();
        for (ActivityImg activityImg :activityImgs){
            String id = activityImg.getRelId();
            List<ActivityImg> imgList =  map.get(id);
            if(imgList == null){
                imgList = new ArrayList<>();
            }
            imgList.add(activityImg);
            map.put(id, imgList);
        }
        return map;
    }
}
