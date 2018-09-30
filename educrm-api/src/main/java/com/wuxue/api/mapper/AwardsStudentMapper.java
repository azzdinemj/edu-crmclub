package com.wuxue.api.mapper;

import com.wuxue.model.junhwa.AwardsStudent;
import com.wuxue.model.junhwa.AwardsStudentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AwardsStudentMapper {
    /**
     * 按条件计数
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    long countByExample(AwardsStudentExample example);

    /**
     * 按条件删除
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int deleteByExample(AwardsStudentExample example);

    /**
     * 按主键删除
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int deleteByPrimaryKey(String awardsStudentId);

    /**
     * 插入数据 所有字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int insert(AwardsStudent record);

    /**
     * 插入数据 值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int insertSelective(AwardsStudent record);

    /**
     * 按条件查询
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    List<AwardsStudent> selectByExample(AwardsStudentExample example);

    /**
     * 按主键查询
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    AwardsStudent selectByPrimaryKey(String awardsStudentId);

    /**
     * 按条件更新值不为 null 的字段
     * @param record 操作 实体 bean 对象
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByExampleSelective(@Param("record") AwardsStudent record, @Param("example") AwardsStudentExample example);

    /**
     * 按条件更新所有字段
     * @param record 操作 实体 bean 对象
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByExample(@Param("record") AwardsStudent record, @Param("example") AwardsStudentExample example);

    /**
     * 按主键更新值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByPrimaryKeySelective(AwardsStudent record);

    /**
     * 按主键更新所有字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByPrimaryKey(AwardsStudent record);

    
    List<AwardsStudent> getAwardsStudentListByIds(@Param("ids") List<String> ids);
}