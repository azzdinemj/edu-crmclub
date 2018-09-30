package com.wuxue.api.mapper;

import com.wuxue.model.WeekTableDouble;

import java.util.List;

public interface WeekTableDoubleMapper {
    int insert(WeekTableDouble record);

    int insertSelective(WeekTableDouble record);

    List<WeekTableDouble> select(WeekTableDouble weekTableDouble);

    int insertBySchedule(String classinfoid);

    int deleteByPkClassinfo(String classinfoid);

    List<WeekTableDouble> selectByEmployee(WeekTableDouble weekTableDouble);
}