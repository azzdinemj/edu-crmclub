package com.wuxue.api.controller.classInfo;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.TeacherCommentService;
import com.wuxue.model.TeacherComment;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/01/02.
 */
@RestController
@RequestMapping(value = "api/classinfo/teacherComment")
public class TeacherCommentController implements IFindController<TeacherComment>,
        ISaveController<TeacherComment>,IDeleteController<String> {
    @Autowired
    private TeacherCommentService teacherCommentService;

    @Override
    public Response Find(@RequestBody Request<TeacherComment> teacherComment) {
        return teacherCommentService.find(teacherComment);
    }

    @Override
    public Response Save(@RequestBody Request<TeacherComment> teacherComment) {
        return teacherCommentService.save(teacherComment);
    }
    @Override

    public Response Delete(@RequestBody Request<String> teacherComment) {
        return teacherCommentService.delete(teacherComment);

    }
}
