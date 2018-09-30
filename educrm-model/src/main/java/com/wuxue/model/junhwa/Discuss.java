package com.wuxue.model.junhwa;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wuxue.base.Page;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * TABLE  t_discuss
 * MyBatis Generator Create
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Discuss extends Page implements Serializable {
    /**
     * discuss_id
     * 主键：留言/回复id
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String discussId;

    /**
     * user_id
     * 用户id：家长id/老师id
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String userId;

    /**
     * student_id
     * 学生id
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String studentId;

    /**
     * content
     * 留言内容
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String content;

    /**
     * type
     * 留言类型：0-教学/1-生活
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Integer type;

    /**
     * create_time
     * 留言时间
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private Date createTime;

    /**
     * parent_discuss_id
     * 父留言id ：默认-1
     * 
     * WARNING - @mbg.generated MyBatis Generator Create
     */
    private String parentDiscussId;

    //老师回复集合
    private List<Discuss> teacherReplyList;
    //用户头像
    private String  userImg;
    //用户称呼
    private String userAppellation;

    private static final long serialVersionUID = 1L;

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getUserAppellation() {
        return userAppellation;
    }

    public void setUserAppellation(String userAppellation) {
        this.userAppellation = userAppellation;
    }

    public List<Discuss> getTeacherReplyList() {
        return teacherReplyList;
    }

    public void setTeacherReplyList(List<Discuss> teacherReplyList) {
        this.teacherReplyList = teacherReplyList;
    }

    public String getDiscussId() {
        return discussId;
    }

    public void setDiscussId(String discussId) {
        this.discussId = discussId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getParentDiscussId() {
        return parentDiscussId;
    }

    public void setParentDiscussId(String parentDiscussId) {
        this.parentDiscussId = parentDiscussId;
    }
}