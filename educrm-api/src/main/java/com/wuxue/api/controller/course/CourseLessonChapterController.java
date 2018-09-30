package com.wuxue.api.controller.course;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.CourseLessonChapterService;
import com.wuxue.model.CourseLessonChapter;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Rogue on 2018/03/15.
 */
@RestController
@RequestMapping(value = "api/course/courseLessonChapter")
public class CourseLessonChapterController implements IFindController<CourseLessonChapter>,
        ISaveController<CourseLessonChapter>,IDeleteController<String>{


    @Autowired
    private CourseLessonChapterService courseLessonChapterService;

    @Override
    public Response Find(@RequestBody Request<CourseLessonChapter> courseLessonChapter) {
        return courseLessonChapterService.find(courseLessonChapter);
    }

    @Override
    public Response Save(@RequestBody Request<CourseLessonChapter> courseLessonChapter) {
        return courseLessonChapterService.save(courseLessonChapter);
    }

    @Override
    public Response Delete(@RequestBody Request<String> courseLessonChapter) {
        return courseLessonChapterService.delete(courseLessonChapter);
    }

    /**
     * 课程章节排序
     * */
    @RequestMapping(value = "/updatesort",method = RequestMethod.POST)
    public Response updateSort(@RequestBody Request<String> courseLessonChapter) {
        return courseLessonChapterService.updateSort(courseLessonChapter);
    }
}
