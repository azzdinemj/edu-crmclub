package com.wuxue.api.service.junhwa;

import com.wuxue.model.junhwa.RaceAwards;

import java.util.List;
import java.util.Map;

/**
 * 竞赛奖项service
 * @author tly
 * @date 2018-08-03
 */
public interface RaceAwardsService {


    /**
     * 通过竞赛id集合获取对应的奖项信息
     * @param raceIds
     * @return
     */
    Map<String, List<RaceAwards>> getRaceIdAndAwardsMap(List<String> raceIds);
}