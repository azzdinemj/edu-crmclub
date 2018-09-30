package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.DivisionGradeService;
import com.wuxue.model.DivisionGrade;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/01/03.
 */
@RestController
@RequestMapping(value = "api/system/divisionGrade")
public class DivisionGradeController implements IFindController<DivisionGrade>,
        ISaveController<DivisionGrade>,IDeleteController<Integer>{
    @Autowired
    private DivisionGradeService divisionGradeService;

    @Override
    public Response Find(@RequestBody Request<DivisionGrade> divisionGrade) {
        return divisionGradeService.find(divisionGrade);
    }

    @Override
    public Response Save(@RequestBody Request<DivisionGrade> divisionGrade) {
        return divisionGradeService.save(divisionGrade);
    }

    @Override
    public Response Delete(@RequestBody Request<Integer> divisionGrade) {
        return divisionGradeService.delete(divisionGrade);
    }
}
