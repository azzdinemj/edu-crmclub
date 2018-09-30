package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.ReviewService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.*;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//import com.wuxue.api.utils.GuidUtils;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService{
    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    UtilsService utilsService;
    @Autowired
    QuestionsMapper questionsMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public Response delete(Request<String> tParams) {
        Response response = Response.newResponse();
        String primaryKey = tParams.getData();
        if(primaryKey== null || primaryKey.equals("")){
            return  response.PARAMS_ISNULL();
        }
        int iReuslt = 1;
        String message= "";
        try {
            iReuslt=reviewMapper.deleteByPrimaryKey(primaryKey);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.DELETE_FAIL(message);
    }

    @Override
    public Response find(Request<Review> tParams) {
        Response response = Response.newResponse();
        Review review = tParams.getData();

        if(review== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = review.getPkReview();
        if(primaryKey !=null && !primaryKey.equals("")){
            Review byPrimaryKey = reviewMapper.selectByPrimaryKey(primaryKey);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getCreator(), LinkEntity.CREATOR_ENTITY);
            utilsService.setSysUserKeyValue(byPrimaryKey,byPrimaryKey.getModifier(),LinkEntity.MODIFIER_ENTITY);
            response.setData(byPrimaryKey);
        }else{
//            获取当前用户绑定角色
            SysUserRoleKey userRoleKey = new SysUserRoleKey();
            userRoleKey.setPkSysUser(tParams.getCurrentUser());
            List<SysUserRoleKey> roleKeyList = sysUserRoleMapper.select(userRoleKey);
//            根据角色查询是否存在审核员等级
            SysRole sysRole;
            if (roleKeyList.size()>0) {
                for (SysUserRoleKey sysUserRoleKey : roleKeyList) {
                    sysRole = sysRoleMapper.selectByPrimaryKey(sysUserRoleKey.getPkSysRole());
                    if(sysRole.getCode().equals("ZH-RE-01")){
                        if(review.getStatus() != null && review.getStatus() == 102 && review.getStatus() != 103 && review.getStatus() != 101){
                            review.setStatus(103);
                            break;
                        }
                        review.setStatus(101);
                    }else if(sysRole.getCode().equals("ZH-RE-02")){
                        if(review.getStatus() != null && review.getStatus() == 101 && review.getStatus() != 103 && review.getStatus() != 102){
                            review.setStatus(103);
                            break;
                        }
                        review.setStatus(102);
                    }else if(sysRole.getCode().equals("admin")){
                        review.setStatus(null);
                        break;
                    }
                }
            }
            PageHelper.startPage(review.getPageNo(),review.getPageSize());
            List<Review> timeList = reviewMapper.select(review);
            if (timeList.size() > 0) {
                for (Review list : timeList) {
                    utilsService.setSysUserKeyValue(list, list.getCreator(), LinkEntity.CREATOR_ENTITY);
                    utilsService.setSysUserKeyValue(list, list.getModifier(), LinkEntity.MODIFIER_ENTITY);
                }
            }
            PageInfo<Review> pageInfo = new PageInfo<>(timeList);
            response.setData(pageInfo);
            //response.setTotal(reviewMapper.countBy(review));
        }
        return response;
    }

    @Override
    public Response save(Request<Review> tParams) {
        Response response = Response.newResponse();
        Review review = tParams.getData();

        if(review== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = review.getPkReview();
        Review select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = reviewMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                review.setLasteditDate(new Date());
                iReuslt = reviewMapper.updateByPrimaryKeySelective(review);
            } else {
                review.setPkReview(GuidUtils.getGuid());
                review.setCreationDate(new Date());
                review.setLasteditDate(new Date());
                iReuslt = reviewMapper.insertSelective(review);
            }
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if(iReuslt>0) {
            return response;
        }
        return response.SAVE_FAIL(message);
    }

    /**
     * 审核
     * 0课程 需要二次审核
     * 1题库 目前一次审核
     * 审核完成之后修改审核状态并修改题库/课程状态
     * */
    @Override
    public Response audit(Request<Review> tParams) {
        Response response = Response.newResponse();
        Review review = tParams.getData();

        if(review== null && review.getStatus() != null && review.getPkReview() != null){
            return  response.PARAMS_ISNULL();
        }

        Review byPrimaryKey = reviewMapper.selectByPrimaryKey(review.getPkReview());
        String message;
        try {
            if (byPrimaryKey.getStatus() == 0 || byPrimaryKey.getStatus() == 1) {
                if (review.getStatus() == 0) {
                    if (byPrimaryKey.getType() == 0) {
                        if (byPrimaryKey.getStatus() == 1) {
                            byPrimaryKey.setStatus(2);
                            byPrimaryKey.setCotent("课程第二次审核");
                            updateCourse(byPrimaryKey.getPkData(), 2);
                        }else{
                            byPrimaryKey.setStatus(1);
                            updateCourse(byPrimaryKey.getPkData(), 1);
                        }
                    } else {
                        byPrimaryKey.setStatus(2);
                        Questions questions = questionsMapper.selectByPrimaryKey(byPrimaryKey.getPkData());
                        questions.setStatus(2);
                        questionsMapper.updateByPrimaryKeySelective(questions);
                    }
                } else if(review.getStatus() == 1){
                    //审核驳回，课程
                    byPrimaryKey.setStatus(3);//驳回
                    if(byPrimaryKey.getType()==0){
                        updateCourse(byPrimaryKey.getPkData(), 0);//修改课程状态
                    }else if(byPrimaryKey.getType()==1){
                        Questions q=new Questions();
                        q.setPkQuestions(byPrimaryKey.getPkData());
                        q.setStatus(0);
                        questionsMapper.updateByPrimaryKeySelective(q);
                    }

                }

                byPrimaryKey.setModifier(review.getModifier());
                byPrimaryKey.setLasteditDate(new Date());
                reviewMapper.updateByPrimaryKeySelective(byPrimaryKey);
            } else {
                return response.AUDIT_DOUBLE();
            }
        }catch (Exception ex){
            message = ex.getMessage();
            return response.AUDIT_FAIL(message);
        }
        return response;
    }

    public void updateCourse(String id,int Status){
        Course course = courseMapper.selectByPrimaryKey(id);
        course.setIsissue(Status);
        courseMapper.updateByPrimaryKeySelective(course);
    }

}
