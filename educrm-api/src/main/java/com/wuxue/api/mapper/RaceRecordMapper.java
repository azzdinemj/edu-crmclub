package com.wuxue.api.mapper;

import com.wuxue.base.ResultEntity;
import com.wuxue.model.junhwa.RaceRecord;
import com.wuxue.model.junhwa.RaceRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RaceRecordMapper {
    /**
     * 按条件计数
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    long countByExample(RaceRecordExample example);

    /**
     * 按条件删除
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int deleteByExample(RaceRecordExample example);

    /**
     * 按主键删除
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int deleteByPrimaryKey(String raceId);

    /**
     * 插入数据 所有字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int insert(RaceRecord record);

    /**
     * 插入数据 值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int insertSelective(RaceRecord record);

    /**
     * 按条件查询
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    List<RaceRecord> selectByExample(RaceRecordExample example);

    /**
     * 按主键查询
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    RaceRecord selectByPrimaryKey(String raceId);

    /**
     * 按条件更新值不为 null 的字段
     * @param record 操作 实体 bean 对象
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByExampleSelective(@Param("record") RaceRecord record, @Param("example") RaceRecordExample example);

    /**
     * 按条件更新所有字段
     * @param record 操作 实体 bean 对象
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByExample(@Param("record") RaceRecord record, @Param("example") RaceRecordExample example);

    /**
     * 按主键更新值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByPrimaryKeySelective(RaceRecord record);

    /**
     * 按主键更新所有字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByPrimaryKey(RaceRecord record);

    /**
     * 根据班级id集合查询竞赛记录列表
     * @param ids
     * @return
     */
    List<RaceRecord> selectRaceRecordByClassIds(@Param("ids") List<String> ids);

    /**
     * 根据竞赛id找出竞赛记录
     * @param raceId
     * @return
     */
    RaceRecord selectRaceRecordInfoByRaceId(String raceId);

    /**
     * 根据学生id查询家长端竞赛记录列表
     * @param studentId
     * @return
     */
    List<ResultEntity> selectStudentAwardsListByStudentId(String studentId);
}