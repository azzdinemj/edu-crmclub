package com.wuxue.api.controller.student;

import com.wuxue.api.interfaces.IDeleteController;
import com.wuxue.api.interfaces.IFindController;
import com.wuxue.api.interfaces.ISaveController;
import com.wuxue.model.Classinfo;
import com.wuxue.model.Linkman;
import com.wuxue.api.service.LinkmanService;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Rogue on 2018/01/02.
 */
@RestController
@RequestMapping(value = "api/student/linkman")
public class LinkmanController implements IFindController<Linkman>,
        ISaveController<Linkman>,IDeleteController<String> {
    @Autowired
    private LinkmanService linkmanService;

    @Override
    public Response Find(@RequestBody Request<Linkman> linkman) {
        return linkmanService.find(linkman);
    }

    @Override
    public Response Save(@RequestBody Request<Linkman> linkman) {
        return linkmanService.save(linkman);
    }
    @Override

    public Response Delete(@RequestBody Request<String> linkman) {
        return linkmanService.delete(linkman);

    }

    @RequestMapping(value = "/selectstudentlist",method = RequestMethod.POST)
    public Response selectStudentList(@RequestBody Linkman linkman) {
        return linkmanService.selectStudentList(linkman);
    }

    @RequestMapping(value = "/findbystudent",method = RequestMethod.POST)
    public Response findByStudent(@RequestBody Request<Linkman> linkman) {
        return linkmanService.findByStudent(linkman);
    }
}
