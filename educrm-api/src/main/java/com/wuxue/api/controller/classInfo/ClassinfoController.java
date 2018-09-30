package com.wuxue.api.controller.classInfo;

import com.wuxue.api.interfaces.*;
import com.wuxue.model.Classinfo;
import com.wuxue.api.service.ClassinfoService;
import com.wuxue.model.ClassinfoStudent;
import com.wuxue.model.Schedule;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Rogue on 2018/01/02.
 */
@RestController
@RequestMapping(value = "api/classinfo/classinfo")
public class ClassinfoController implements IFindController<Classinfo>,
        ISaveController<Classinfo>,IDeleteController<String>,IAuditController<Classinfo>,ISubmitController<Classinfo> {
    @Autowired
    private ClassinfoService classinfoService;

    @Override
    public Response Find(@RequestBody Request<Classinfo> classinfo) {
        return classinfoService.find(classinfo);
    }

    @Override
    public Response Save(@RequestBody Request<Classinfo> classinfo) {
        return classinfoService.save(classinfo);
    }

    @Override
    public Response Delete(@RequestBody Request<String> classinfo) {
        return classinfoService.delete(classinfo);
    }

    @RequestMapping(value = "/studentgoclass",method = RequestMethod.POST)
    public Response StudentGoClass(@RequestBody Request<Classinfo> classinfo) {
        return classinfoService.studentGoClass(classinfo);
    }

    @RequestMapping(value = "/updatestatus",method = RequestMethod.POST)
    public Response updateStatus(@RequestBody Request<Classinfo> classinfo) {
        return classinfoService.updateStatus(classinfo);
    }

    @RequestMapping(value = "/studentgointerestclass",method = RequestMethod.POST)
    public Response studentGoInterestClass(@RequestBody Request<Classinfo> classinfo) {
        return classinfoService.studentGoInterestClass(classinfo);
    }

    @Override
    public Response Audit(@RequestBody Request<Classinfo> classinfo) {
        return classinfoService.audit(classinfo);
    }

    @Override
    public Response Submit(@RequestBody Request<Classinfo> classinfo) {
        return classinfoService.submit(classinfo);
    }

    @RequestMapping(value = "/findclassstudentscores",method = RequestMethod.POST)
    public Response findClassStudentScores(@RequestBody Request<ClassinfoStudent> classinfoStudentRequest){
        return classinfoService.findClassStudentScores(classinfoStudentRequest);
    }
    @RequestMapping(value = "/findbyispay",method = RequestMethod.POST)
    public Response findByIsPay(@RequestBody Request<Classinfo> classinfoRequest) {
        return classinfoService.findByIsPay(classinfoRequest);
    }

    @RequestMapping(value = "/selectivecheck",method = RequestMethod.POST)
    public Response selectiveCheck(@RequestBody Request<Schedule> Schedule){
        return classinfoService.selectiveCheck(Schedule);
    }

    @RequestMapping(value = "/selectby",method = RequestMethod.POST)
    public Response selectBy(@RequestBody Request<Classinfo> classinfo){
        return classinfoService.selectBy(classinfo);
    }

    @RequestMapping(value = "/gotoschool",method = RequestMethod.POST)
    public Response goToSchool(@RequestBody Request<Classinfo> classinfo){
        return classinfoService.goToSchool(classinfo);
    }

    @RequestMapping(value = "/findclassforcourse",method = RequestMethod.POST)
    public Response findClassForCourse(@RequestBody Request<Classinfo> classinfoRequest) {
        return classinfoService.findClassForCourse(classinfoRequest);
    }
    @RequestMapping(value = "/classon",method = RequestMethod.POST)
    public Response classOn(@RequestBody Request<Classinfo> classinfoRequest) {
        return classinfoService.classOn(classinfoRequest);
    }
    @RequestMapping(value = "/saveforsche",method = RequestMethod.POST)
    public Response saveForSche(@RequestBody Request<String> classinfoRequest) {
        return classinfoService.saveForSche(classinfoRequest);
    }
    @RequestMapping(value = "/getclassinfo",method = RequestMethod.POST)
    public Response getClassinfo(@RequestBody Request<Classinfo> classinfoRequest) {
        return classinfoService.getClassinfo(classinfoRequest);
    }
    @RequestMapping(value = "/saveelective",method = RequestMethod.POST)
    public Response saveElective(@RequestBody Request<Classinfo> classinfoRequest) {
        return classinfoService.saveElective(classinfoRequest);
    }



//    @RequestMapping(value = "/saveforsche",method = RequestMethod.POST)
//    public Response saveForSche(@RequestBody Request<List<String>> classinfoRequest) {
//        return classinfoService.saveForSche(classinfoRequest);
//    }
}
