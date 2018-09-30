package com.wuxue.api.service.junhwa.impl;

import com.wuxue.api.mapper.AwardsStudentMapper;
import com.wuxue.api.service.junhwa.AwardsStudentService;
import com.wuxue.model.junhwa.AwardsStudent;
import com.wuxue.model.junhwa.AwardsStudentExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 竞赛学生奖项service实现类
 *
 * @author tly
 * @date 2018-08-03
 */
@Service("awardsStudentService")
public class AwardsStudentServiceImpl implements AwardsStudentService {

    @Autowired
    private AwardsStudentMapper awardsStudentMapper;

    @Override
    public Map<String, List<AwardsStudent>> getAwardsIdAndStudentMap(List<String> awardsIds) {
        AwardsStudentExample example = new AwardsStudentExample();
        example.createCriteria().andAwardsIdIn(awardsIds);
        List<AwardsStudent> awardsStudents = awardsStudentMapper.getAwardsStudentListByIds(awardsIds);
        if(CollectionUtils.isEmpty(awardsStudents)){
            return new HashMap<>(0);
        }

        Map<String, List<AwardsStudent>> map = new HashMap<>();
        for(AwardsStudent student:awardsStudents){
            String awardsId = student.getAwardsId();
            List<AwardsStudent> students = map.get(awardsId);
            if(null == students){
                students = new ArrayList<>();
            }
            students.add(student);
            map.put(awardsId, students);
        }
        return map;
    }
}
