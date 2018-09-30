package com.wuxue.api.mapper;

import com.wuxue.model.junhwa.StudentNutritionAnalysis;
import com.wuxue.model.junhwa.StudentNutritionAnalysisExample;
import com.wuxue.model.junhwa.StudentNutritionAnalysisKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentNutritionAnalysisMapper {
    /**
     * 按条件计数
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    long countByExample(StudentNutritionAnalysisExample example);

    /**
     * 按条件删除
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int deleteByExample(StudentNutritionAnalysisExample example);

    /**
     * 按主键删除
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int deleteByPrimaryKey(StudentNutritionAnalysisKey key);

    /**
     * 插入数据 所有字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int insert(StudentNutritionAnalysis record);

    /**
     * 插入数据 值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int insertSelective(StudentNutritionAnalysis record);

    /**
     * 按条件查询
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    List<StudentNutritionAnalysis> selectByExample(StudentNutritionAnalysisExample example);

    /**
     * 按主键查询
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    StudentNutritionAnalysis selectByPrimaryKey(StudentNutritionAnalysisKey key);

    /**
     * 按条件更新值不为 null 的字段
     * @param record 操作 实体 bean 对象
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByExampleSelective(@Param("record") StudentNutritionAnalysis record, @Param("example") StudentNutritionAnalysisExample example);

    /**
     * 按条件更新所有字段
     * @param record 操作 实体 bean 对象
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByExample(@Param("record") StudentNutritionAnalysis record, @Param("example") StudentNutritionAnalysisExample example);

    /**
     * 按主键更新值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByPrimaryKeySelective(StudentNutritionAnalysis record);

    /**
     * 按主键更新所有字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByPrimaryKey(StudentNutritionAnalysis record);
}