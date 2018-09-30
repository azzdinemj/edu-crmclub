package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.TeacherComment;

import java.util.List;

public interface TeacherCommentMapper extends IInsertMapper<TeacherComment>,ICountMapper<TeacherComment,Integer>,
        IUpdateMapper<TeacherComment>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,TeacherComment>,ISelectMapper<TeacherComment,List<TeacherComment>>{
}