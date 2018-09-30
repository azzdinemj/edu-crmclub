package com.wuxue.api.service;


import com.wuxue.api.interfaces.IDeleteService;
import com.wuxue.api.interfaces.IFindService;
import com.wuxue.api.interfaces.ISaveService;
import com.wuxue.model.CourseLessonChapter;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;

import java.util.List;

public interface CourseLessonChapterService extends ISaveService<CourseLessonChapter>,IFindService<CourseLessonChapter>,IDeleteService<String> {
    /**
     * 课程章节排序
     * */
    Response updateSort(Request<String> courseLessonChapter);
}
