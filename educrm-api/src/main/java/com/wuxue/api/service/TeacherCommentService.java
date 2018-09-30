package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.TeacherComment;

public interface TeacherCommentService extends ISaveService<TeacherComment>,IFindService<TeacherComment>,IDeleteService<String> {
}
