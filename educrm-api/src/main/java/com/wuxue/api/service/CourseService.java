package com.wuxue.api.service;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.Course;

/**
* @Description: 课程 Service接口
* @author wanghao
* @date  12:28 2018/3/13
* @version V1.0
*/
public interface CourseService  extends ISaveService<Course>,IFindService<Course>,
        IDeleteService<String>,IAuditService<Course>{

}
