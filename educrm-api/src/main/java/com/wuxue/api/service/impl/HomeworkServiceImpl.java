package com.wuxue.api.service.impl;

import com.wuxue.api.mapper.EmployeeMapper;
import com.wuxue.api.mapper.HomeworkMapper;
import com.wuxue.api.service.HomeworkService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.api.utils.JobPostEnum;
import com.wuxue.base.KeyValue;
import com.wuxue.model.Employee;
import com.wuxue.model.Homework;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("homeworkService")
public class HomeworkServiceImpl implements HomeworkService {

    @Autowired
    private HomeworkMapper homeworkMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private UtilsService utilsService;


    @Override
    public Response findStuWork(String pkStudent,String workType){

        Response response = Response.newResponse();

        Map map = new HashMap();
        map.put("pkStudent",pkStudent);
        map.put("workType",workType);

        List<Homework> list = homeworkMapper.selectStuWork(map);

        response.setData(list);

        return response;
    }

    @Override
    public Response findBykey(String pkHomework) {
        Response response = Response.newResponse();

        if (pkHomework == null || "".equals(pkHomework)){
            return response.PARAMS_ISNULL();
        }
        Homework homework = homeworkMapper.selectByPrimaryKey(pkHomework);
        response.setData(homework);

        return response;
    }

    @Override
    public Response save(Homework homework,List<String> pkClassinfos) {

        Response response = Response.newResponse();

        String message="";

        if (homework == null  || pkClassinfos == null || pkClassinfos.size()==0){
            response.PARAMS_ISNULL();
        }

        String pkHomework = homework.getPkHomework();
        try {
            Homework select = null;
            if (pkHomework != null || !"".equals(pkHomework) ){
                select = homeworkMapper.selectByPrimaryKey(pkHomework);
            }
            if (select != null){
                homework.setLasteditDate(new Date());

                homeworkMapper.updateByPrimaryKeySelective(homework);
            }else {
                for (String pkClassinfo : pkClassinfos) {
                    if(pkHomework == null || "".equals(pkHomework)){
                        homework.setPkHomework(GuidUtils.getGuid());
                        homework.setCreationDate(new Date());
                        homework.setLasteditDate(new Date());
                        homework.setClassinfo(pkClassinfo);
                        homeworkMapper.insertSelective(homework);
                    }
                }

            }

        }catch (Exception e){
            message = e.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response;
    }

    @Override
    public List<Homework> findHomeworkByTea(Homework homework, String jobPost,String pkEmployee) {

        if (JobPostEnum.HEAD_TEACHER_EMPLOYEE.equals(jobPost)){
            Employee employee = employeeMapper.selectByPrimaryKey(pkEmployee);
            //班主任
            homework.setCreator(employee.getSysUser());
            List<Homework> list = homeworkMapper.selectByHeadTea(homework);
            if (list.size() >0){
                for (Homework homework1 : list) {
                    utilsService.setClassInfoKeyValue(homework1,homework1.getClassinfo(), LinkEntity.CLASS_INFO_ENTITY);
                }
            }
            return list;
        }else {
            //任课教师
            Employee employee = employeeMapper.selectByPrimaryKey(pkEmployee);
            if (employee != null){
                homework.setCreator(employee.getSysUser());
            }
            List<Homework> list = homeworkMapper.selectByTea(homework);
            if (list.size() >0){
                for (Homework homework1 : list) {
                    utilsService.setClassInfoKeyValue(homework1,homework1.getClassinfo(), LinkEntity.CLASS_INFO_ENTITY);
                }
            }
            return list;
        }
    }


}
