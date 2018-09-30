package com.wuxue.api.controller.student;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.DropExpenseRecordService;
import com.wuxue.model.DropExpenseRecord;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jie 2018/07/10
 */
@RestController
@RequestMapping(value = "api/student/dropExpenseRecord")
public class DropExpenseRecordController implements IFindController<DropExpenseRecord>,
        ISaveController<DropExpenseRecord>,IDeleteController<Long>{
    @Autowired
    private DropExpenseRecordService dropExpenseRecordService;

    @Override
    public Response Find(@RequestBody Request<DropExpenseRecord> dropExpenseRecord) {

        return dropExpenseRecordService.find(dropExpenseRecord);
    }

    @Override
    public Response Save(@RequestBody Request<DropExpenseRecord> dropExpenseRecord) {
        return dropExpenseRecordService.save(dropExpenseRecord);
    }

    @Override
    public Response Delete(@RequestBody Request<Long> dropExpenseRecord) {
        return dropExpenseRecordService.delete(dropExpenseRecord);
    }


}
