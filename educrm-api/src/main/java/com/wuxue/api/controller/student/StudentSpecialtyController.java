package com.wuxue.api.controller.student;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.StudentSpecialty;
import com.wuxue.api.service.StudentSpecialtyService;
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
@RequestMapping(value = "api/student/studentSpecialty")
public class StudentSpecialtyController implements IFindController<StudentSpecialty>,
        ISaveController<StudentSpecialty>,IDeleteController<String> {
    @Autowired
    private StudentSpecialtyService studentSpecialtyService;

    @Override
    public Response Find(@RequestBody Request<StudentSpecialty> studentSpecialty) {
        return studentSpecialtyService.find(studentSpecialty);
    }

    @Override
    public Response Save(@RequestBody Request<StudentSpecialty> studentSpecialty) {
        return studentSpecialtyService.save(studentSpecialty);
    }
    @Override

    public Response Delete(@RequestBody Request<String> studentSpecialty) {
        return studentSpecialtyService.delete(studentSpecialty);

    }
}
