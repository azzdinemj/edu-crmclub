package com.wuxue.api.controller.system;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.api.service.TaskService;
import com.wuxue.model.Task;
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
@RequestMapping(value = "api/system/task")
public class TaskController implements IFindController<Task>,
        ISaveController<Task>,IDeleteController<String> {
    @Autowired
    private TaskService taskService;

    @Override
    public Response Find(@RequestBody Request<Task> task) {
        return taskService.find(task);
    }

    @Override
    public Response Save(@RequestBody Request<Task> task) {
        return taskService.save(task);
    }
    @Override

    public Response Delete(@RequestBody Request<String> task) {
        return taskService.delete(task);

    }
}
