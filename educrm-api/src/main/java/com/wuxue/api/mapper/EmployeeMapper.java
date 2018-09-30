package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper extends IInsertMapper<Employee>,ICountMapper<Employee,Integer>,
        IUpdateMapper<Employee>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,Employee>,ISelectMapper<Employee,List<Employee>> {
	Employee selectByUser(String userCode);
	int updateByPrimaryKey(Employee employee);

	/**
	 * 根据学生id查询班主任信息
	 * @param pkStudent 学生主键
	 * @return
	 */
	Employee findEmployeeByPkStudent(String pkStudent);

	/**
	 * 根据id集合获取教师信息
	 * @param ids
	 * @return
	 */
	List<Employee> selectTeachersByIds(@Param("ids")List<String> ids);

	/**
	 * 根据老师id查询老师岗位
	 * @param teacherId
	 * @return
	 */
	String selectTeacherJobPost(String teacherId);

    String getCode();

    List<Map<String,Object>> selectForMap(Employee data);
}