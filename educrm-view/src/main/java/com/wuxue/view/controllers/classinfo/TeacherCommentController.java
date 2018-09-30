package com.wuxue.view.controllers.classinfo;

import com.wuxue.model.TeacherComment;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.TeacherCommentClient;
import com.wuxue.view.client.system.SysUserClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 上课时间
 */
@Controller
@RequestMapping(value = "/classinfo/teacherComment")
public class TeacherCommentController extends BaseController
        implements IQueryController<TeacherComment, String>, ISaveController<TeacherComment, String>,IQueryByPagingController<TeacherComment,Map<String,Object>>,
        ICreateController<TeacherComment, String>, IEditController<TeacherComment, String>, IDeleteController<TeacherComment, String> {

    @Autowired
    private TeacherCommentClient teacherCommentClient;
    @Autowired
    private SysDictUtils sysDictUtils;


    /**
     * 新生报名初始化下拉框
     */
    private void initReportPageAttribute(Model model,String pkDomain){
        //学部
//        sysDictUtils.setModeAttribute(model, "division", SysDicEmnuUtils.DIVISION);
    }
    /**
     * 上课时间列表页面
     * @param model
     * @return
     * @throws java.text.ParseException
     */
   @Override
    public String query(HttpServletRequest request, Model model, TeacherComment teacherComment) {
//        Response<List<TeacherComment>> listResponse = teacherCommentClient.find(teacherComment);
//
//        model.addAttribute("list",listResponse.getData() );
        return "/classinfo/teacherCommentList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, TeacherComment teacherComment, Integer sEcho, Integer start, Integer length) {
        teacherComment.setPageNo((start/length)+1);
        teacherComment.setPageSize(length);
        Response<List<TeacherComment>> listResponse = teacherCommentClient.find(teacherComment);
        List<TeacherComment> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    /**
     * 修改页面
     * @param teacherComment
     * @param model
     * @return
     */
    @Override
    public String  edit(HttpServletRequest request, Model model,TeacherComment teacherComment){
        Response<TeacherComment> byPrimaryKey = teacherCommentClient.findByPrimaryKey(teacherComment);
        model.addAttribute("teacherComment",byPrimaryKey.getData());
        sysDictUtils.setModeAttribute(model,"course",SysDicEmnuUtils.STU_DISCIPLINE);

        return "/classinfo/scoreEvaluate";
    }


    /**
     * 保存
     * @param teacherComment
     * @param request
     * @return
     */
   @Override
   @ResponseBody
    public String save(HttpServletRequest request, Model model,TeacherComment teacherComment) throws ParseException {
       if(teacherComment.getPkTeacherComment()== null || "".equals(teacherComment.getPkTeacherComment()) ){
           teacherComment.setPkTeacherComment(GuidUtils.getGuid());
       }

       SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
       if(teacherComment.getYears() != null){
            teacherComment.setYear(sdf.parse(teacherComment.getYears()));
       }
        return teacherCommentClient.save(teacherComment);

    }

    /**
     * 添加页面
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model,TeacherComment teacherComment){
        teacherComment = new TeacherComment();
        teacherComment.setPkDomain(SessionCache.getPkdomain());
        teacherComment.put(LinkEntity.CREATOR_ENTITY, EmployeeUtils.getSysUser());
        teacherComment.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
        teacherComment.setCreationDate(new Date());
        teacherComment.setLasteditDate(new Date());
        model.addAttribute("teacherComment",teacherComment);
        initReportPageAttribute(model,SessionCache.getPkdomain());

        return "/classinfo/teacherComment";
    }

    /**
     * 删除
     * @param teacherComment
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model,TeacherComment teacherComment){
        String response = teacherCommentClient.delete(teacherComment.getPkTeacherComment());
        return response;
    }





}
