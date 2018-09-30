package com.wuxue.api.controller.student;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.StudentWorksPortfolio;
import com.wuxue.api.service.StudentWorksPortfolioService;
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
@RequestMapping(value = "api/student/studentWorksPortfolio")
public class StudentWorksPortfolioController implements IFindController<StudentWorksPortfolio>,
        ISaveController<StudentWorksPortfolio>,IDeleteController<String> {
    @Autowired
    private StudentWorksPortfolioService studentWorksPortfolioService;

    @Override
    public Response Find(@RequestBody Request<StudentWorksPortfolio> studentWorksPortfolio) {
        return studentWorksPortfolioService.find(studentWorksPortfolio);
    }

    @Override
    public Response Save(@RequestBody Request<StudentWorksPortfolio> studentWorksPortfolio) {
        return studentWorksPortfolioService.save(studentWorksPortfolio);
    }
    @Override

    public Response Delete(@RequestBody Request<String> studentWorksPortfolio) {
        return studentWorksPortfolioService.delete(studentWorksPortfolio);

    }
}
