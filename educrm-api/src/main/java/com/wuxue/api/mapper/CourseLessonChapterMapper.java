package com.wuxue.api.mapper;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.CourseLessonChapter;

import java.util.List;

public interface CourseLessonChapterMapper extends IInsertMapper<CourseLessonChapter>,ICountMapper<CourseLessonChapter,Integer>,
        IUpdateMapper<CourseLessonChapter>,IDeleteByPrimaryKeyMapper<String>,
        ISelectByPrimaryKeyMapper<String,CourseLessonChapter>,ISelectMapper<CourseLessonChapter,List<CourseLessonChapter>> {
}