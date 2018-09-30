package com.wuxue.api.mapper;

import com.wuxue.base.ResultEntity;
import com.wuxue.model.junhwa.Discuss;
import com.wuxue.model.junhwa.DiscussExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DiscussMapper {
    /**
     * 按条件计数
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    long countByExample(DiscussExample example);

    /**
     * 按条件删除
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int deleteByExample(DiscussExample example);

    /**
     * 按主键删除
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int deleteByPrimaryKey(String discussId);

    /**
     * 插入数据 所有字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int insert(Discuss record);

    /**
     * 插入数据 值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int insertSelective(Discuss record);

    /**
     * 按条件查询
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    List<Discuss> selectByExample(DiscussExample example);

    /**
     * 按主键查询
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    Discuss selectByPrimaryKey(String discussId);

    /**
     * 按条件更新值不为 null 的字段
     * @param record 操作 实体 bean 对象
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByExampleSelective(@Param("record") Discuss record, @Param("example") DiscussExample example);

    /**
     * 按条件更新所有字段
     * @param record 操作 实体 bean 对象
	 * @param example 条件
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByExample(@Param("record") Discuss record, @Param("example") DiscussExample example);

    /**
     * 按主键更新值不为 null 的字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByPrimaryKeySelective(Discuss record);

    /**
     * 按主键更新所有字段
     * @param record 操作 实体 bean 对象
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    int updateByPrimaryKey(Discuss record);

    /**
     * 根据时间和学生姓名查询学生信息和最后留言时间
     * @param entity
     * @return
     */
    List<ResultEntity> selectStudentInfoAndLastTime(ResultEntity entity);

    /**
     * 查询学生家长留言次数
     * @param type
     * @param studentIds
     * @return
     */
    List<ResultEntity> selectStudentLeaveMsgCount(@Param("type")Integer type,@Param("ids")List<String> studentIds);

    /**
     * 查询老师回复次数
     * @param type
     * @param studentIds
     * @return
     */
    List<ResultEntity> selectTeacherReplyCount(@Param("type")Integer type,@Param("ids")List<String> studentIds);

    List<ResultEntity> selectByHeaderTeacher(ResultEntity data);

    List<ResultEntity> selectByLifeEmployee(ResultEntity data);
}