package com.wuxue.view.utils;

import com.wuxue.model.ClassRoom;
import com.wuxue.model.ExpenseItem;
import com.wuxue.model.Student;
import com.wuxue.model.StudentSignupDetails;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ClassRoomClient;
import com.wuxue.view.client.product.ExpenseItemClient;
import com.wuxue.view.client.student.StudentClient;
import com.wuxue.view.client.student.StudentSignupDetailsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentUtils {


    @Autowired
    private StudentClient studentClient;
    @Autowired
    private ExpenseItemClient expenseItemClient;
    @Autowired
    private ClassRoomClient classRoomClient;
    @Autowired
    private StudentSignupDetailsClient studentSignupDetailsClient;

    /**
     * 获取学生信息
     * @param pkStudent
     * @return
     */
    private Student setStudent(String pkStudent){
        Student student = new Student();
        student.setPkStudent(pkStudent);
        Response<Student> byPrimaryKey = studentClient.findByPrimaryKey(student);
        return byPrimaryKey.getData();
    }

    public Student getStudent(String pkStudent){
        return setStudent(pkStudent);
    }


    /**
     * 获取费用项目
     * @return
     */
//    public List<ExpenseItem> getExpenseItems(){
//        return setExpenseItems();
//
//    }
    public void setExpenseItemModeAttribute(Model model,String name){
        if(model==null || name ==null || "".equals(name)){
            return ;
        }

        model.addAttribute(name,setExpenseItems());
    }


    private List<ExpenseItem> setExpenseItems(){
        ExpenseItem expenseItem =new ExpenseItem();
        Response<List<ExpenseItem>> listResponse = expenseItemClient.find(expenseItem);
        return listResponse.getData();

    }

    /**
     * 获取教室信息
     * @return
     */
    public List<ClassRoom> getClassRooms(){
        return setClassRooms();

    }
    private List<ClassRoom> setClassRooms(){
        ClassRoom classRoom = new ClassRoom();
        Response<List<ClassRoom>> listResponse = classRoomClient.find(classRoom);
        return listResponse.getData();

    }


    /**
     * 获取所有学生
     * @return
     */
    public List<Student> getStudentList(){
        return setStudentList();
    }
    private List<Student> setStudentList(){
        Student student = new Student();
        Response<List<Student>> listResponse = studentClient.find(student);
        return listResponse.getData();
    }

    /**
     * 费用明细
     * @param pkStudentSignup
     * @return
     */
    private List<StudentSignupDetails> getDetails(String pkStudentSignup){
        List<ExpenseItem> expenseItems = setExpenseItems();
        StudentSignupDetails details = new StudentSignupDetails();
        Response<List<StudentSignupDetails>> listResponse = studentSignupDetailsClient.find(details);
        List<StudentSignupDetails> data = listResponse.getData();
        List<StudentSignupDetails> detailsList = new ArrayList<>();
        if (data != null && data.size() > 0) {
            for (StudentSignupDetails detailsDatum : data) {
                if (pkStudentSignup.equals(detailsDatum.getPkStudentSignup())) {
                    for (ExpenseItem item : expenseItems) {
                        if(item.getPkExpenseItem().equals(detailsDatum.getPkExpenseItem())){
                            detailsDatum.put("itemCaption",item.getCaption());
                            detailsList.add(detailsDatum);
                        }
                    }
                }
            }
        }

        return detailsList;
    }

    public void setDetailsModeAttribute(Model model,String name,String pkStudentSignup){
        if(pkStudentSignup ==null || "".equals(pkStudentSignup)||model==null || name ==null || "".equals(name)){
            return ;
        }

        model.addAttribute(name,getDetails(pkStudentSignup));
    }



}
