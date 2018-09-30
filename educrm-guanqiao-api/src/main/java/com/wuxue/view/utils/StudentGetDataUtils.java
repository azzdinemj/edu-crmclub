package com.wuxue.view.utils;

import com.wuxue.model.*;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentGetDataUtils {

    @Autowired
    private LinkManClient linkManClient;
    @Autowired
    private StudentSpecialtyClient studentSpecialtyClient;
    @Autowired
    private StudentEduExperienceClient eduExperienceClient;

    @Autowired
    private StudentActivityExpClient studentActivityExpClient;




    /**
     * 家长资料
     * @param pkStudent
     * @return
     */
    public List<Linkman> getLinkMans(String pkStudent){
        Linkman linkman = new Linkman();
        List<Linkman> linkmanList = new ArrayList<>();
        Response<List<Linkman>> listResponse = linkManClient.find(linkman);
        List<Linkman> data = listResponse.getData();
        if(data != null && data.size() >0){
            for (Linkman datum : data) {
                if(pkStudent.equals(datum.getPkStudent())){
                    linkmanList.add(datum);
                }
            }
        }

        return linkmanList;
    }

    /**
     * 学生特长
     * @param pkStudent
     * @return
     */
    public List<StudentSpecialty> getSpecialty(String pkStudent){
        StudentSpecialty studentSpecialty = new StudentSpecialty();
        List<StudentSpecialty> studentSpecialtyList = new ArrayList<>();
        Response<List<StudentSpecialty>> listResponse = studentSpecialtyClient.find(studentSpecialty);
        List<StudentSpecialty> data = listResponse.getData();
        if(data != null && data.size() >0){
            for (StudentSpecialty datum : data) {
                if(pkStudent.equals(datum.getPkStudent())){
                    studentSpecialtyList.add(datum);
                }
            }
        }
        return studentSpecialtyList;

    }

    /**
     * 学生教育经历
     * @param pkStudent
     * @return
     */
    public List<StudentEduExperience> getEdu(String pkStudent){

        List<StudentEduExperience> Experiencelist = new ArrayList<>();

        StudentEduExperience studentEduExperience = new StudentEduExperience();
        Response<List<StudentEduExperience>> listResponse = eduExperienceClient.find(studentEduExperience);
        if(listResponse.getData() !=null && listResponse.getData().size()>0){
            for (StudentEduExperience experience : listResponse.getData()) {
                if(pkStudent.equals(experience.getPkStudent())){
                    Experiencelist.add(experience);
                }
            }
        }
        return Experiencelist;

    }

    /**
     * 活动经历
     * @param pkStudent
     * @return
     */
    public List<StudentActivityExp> getActiviity(String pkStudent){
        List<StudentActivityExp> activityList = new ArrayList<>();
       StudentActivityExp studentActivityExp = new StudentActivityExp();
        Response<List<StudentActivityExp>> listResponse = studentActivityExpClient.find(studentActivityExp);
        List<StudentActivityExp> data = listResponse.getData();
        if(data != null && data.size() > 0){
            for (StudentActivityExp datum : data) {
                if(pkStudent.equals(datum.getPkStudent())){
                    activityList.add(datum);
                }

            }
        }
        return activityList;

    }

    /**
     * 学生考试计划成绩
     * @param pkStudent
     * @return
     */
   /* public List<StudentTestPlansScores> getPlansScores(String pkStudent){
        List<StudentTestPlansScores> list = new ArrayList<>();
        StudentTestPlansScores scores = new StudentTestPlansScores();
        Response<List<StudentTestPlansScores>> listResponse = studentTestPlansScoresClient.find(scores);
        List<StudentTestPlansScores> data = listResponse.getData();
        if (data != null && data.size()>0){
            for (StudentTestPlansScores datum : data) {
//                if(pkStudent.equals(datum.get))

            }
        }
        return list;
    }*/



}
