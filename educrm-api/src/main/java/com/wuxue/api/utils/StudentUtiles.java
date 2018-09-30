package com.wuxue.api.utils;

import com.wuxue.api.mapper.LinkmanMapper;
import com.wuxue.api.mapper.StudentLinkmanMapper;
import com.wuxue.api.mapper.StudentMapper;
import com.wuxue.model.Linkman;
import com.wuxue.model.Student;
import com.wuxue.model.StudentLinkmanKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentUtiles {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentLinkmanMapper studentLinkmanMapper;
    @Autowired
    private LinkmanMapper linkmanMapper;

    /**
     * 根据家长账号获取学生id
     * @param userCode
     * @return
     */
    public String getPkStudent(String userCode){
        Student student = studentMapper.selectByPrimaryKey(userCode);
        String pkStudent = "";
        if (student != null) {
            pkStudent = student.getPkStudent();
        } else {
            StudentLinkmanKey studentLinkmanKey = studentLinkmanMapper.selectStuByParPhone(userCode);
            if (studentLinkmanKey != null){
                pkStudent = studentLinkmanKey.getPkStudent();
            }
            else {
                Linkman linkman = linkmanMapper.selectByPhone(userCode);
                if (linkman != null) {
                    pkStudent = linkman.getPkStudent();
                }
            }
        }

        return pkStudent;
    }


}
