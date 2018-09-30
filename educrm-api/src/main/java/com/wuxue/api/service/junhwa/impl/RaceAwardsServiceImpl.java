package com.wuxue.api.service.junhwa.impl;

import com.wuxue.api.mapper.RaceAwardsMapper;
import com.wuxue.api.service.junhwa.AwardsStudentService;
import com.wuxue.api.service.junhwa.RaceAwardsService;
import com.wuxue.model.junhwa.AwardsStudent;
import com.wuxue.model.junhwa.RaceAwards;
import com.wuxue.model.junhwa.RaceAwardsExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 竞赛奖项service实现类
 *
 * @author tly
 * @date 2018-08-03
 */
@Service("raceAwardsService")
public class RaceAwardsServiceImpl implements RaceAwardsService {

    @Autowired
    private RaceAwardsMapper raceAwardsMapper;
    @Autowired
    private AwardsStudentService awardsStudentService;


    @Override
    public Map<String, List<RaceAwards>> getRaceIdAndAwardsMap(List<String> raceIds) {

        RaceAwardsExample example = new RaceAwardsExample();
        example.createCriteria().andRaceIdIn(raceIds);
        List<RaceAwards> raceAwardsList = raceAwardsMapper.selectByExample(example);

        if(CollectionUtils.isEmpty(raceAwardsList)){
            return new HashMap<>(0);
        }

        List<String> awardsIdList = new ArrayList<>(raceAwardsList.size());
        for(RaceAwards raceAwards:raceAwardsList){
            awardsIdList.add(raceAwards.getAwardsId());
        }

        Map<String, List<AwardsStudent>> awardsIdAndStudentMap = awardsStudentService.getAwardsIdAndStudentMap(awardsIdList);

        for(RaceAwards raceAwards:raceAwardsList){
            raceAwards.setAwardsStudentList(awardsIdAndStudentMap.get(raceAwards.getAwardsId()));
        }

        Map<String, List<RaceAwards>> map = new HashMap<>();
        for(RaceAwards raceAwards:raceAwardsList){
            String raceId = raceAwards.getRaceId();
            List<RaceAwards> awards = map.get(raceId);
            if(null == awards){
                awards = new ArrayList<>();
            }
            awards.add(raceAwards);
            map.put(raceId, awards);
        }

        return map;
    }
}
