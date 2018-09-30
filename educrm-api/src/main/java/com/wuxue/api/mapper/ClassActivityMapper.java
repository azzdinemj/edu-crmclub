package com.wuxue.api.mapper;

import com.wuxue.model.junhwa.ClassActivity;
import com.wuxue.model.junhwa.ClassActivityExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ClassActivityMapper {
    /**
     * 按条件计数
	 * @param example 条件
     *
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    long countByExample(ClassActivityExample example);

    /**
     * 按条件删除
	 * @param example 条件
     *
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int deleteByExample(ClassActivityExample example);

    /**
     * 按主键删除
     *
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int deleteByPrimaryKey(String activityId);

    /**
     * 插入数据 所有字段
     * @param record 操作 实体 bean 对象
     *
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int insert(ClassActivity record);

    /**
     * 插入数据 值不为 null 的字段
     * @param record 操作 实体 bean 对象
     *
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int insertSelective(ClassActivity record);

    /**
     * 按条件查询
	 * @param example 条件
     *
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    List<ClassActivity> selectByExample(ClassActivityExample example);

    /**
     * 按主键查询
     *
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    ClassActivity selectByPrimaryKey(String activityId);

    /**
     * 按条件更新值不为 null 的字段
     * @param record 操作 实体 bean 对象
	 * @param example 条件
     *
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByExampleSelective(@Param("record") ClassActivity record, @Param("example") ClassActivityExample example);

    /**
     * 按条件更新所有字段
     * @param record 操作 实体 bean 对象
	 * @param example 条件
     *
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByExample(@Param("record") ClassActivity record, @Param("example") ClassActivityExample example);

    /**
     * 按主键更新值不为 null 的字段
     * @param record 操作 实体 bean 对象
     *
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByPrimaryKeySelective(ClassActivity record);

    /**
     * 按主键更新所有字段
     * @param record 操作 实体 bean 对象
     *
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByPrimaryKey(ClassActivity record);

    /**
     * 根据班级id查询班级活动列表
     * @param ids 班级id集合
     * @return
     */
    List<ClassActivity> selectActivityListByIds(@Param("ids") List<String> ids);
}