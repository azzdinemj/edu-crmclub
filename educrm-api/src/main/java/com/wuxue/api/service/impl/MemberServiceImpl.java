package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.MemberMapper;
import com.wuxue.api.mapper.StudentMapper;
import com.wuxue.api.service.MemberService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.Member;
import com.wuxue.model.Student;
import com.wuxue.utils.common.MD5Util;
import com.wuxue.utils.common.SendEmailUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("memberService")
public class MemberServiceImpl implements MemberService {
    @Autowired
    MemberMapper memberMapper;

    @Autowired
    UtilsService utilsService;

    @Autowired
    StudentMapper studentMapper;

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
            Member member = memberMapper.selectByPrimaryKey(primaryKey);
            member.setIsdel(0);
            memberMapper.updateByPrimaryKeySelective(member);
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
    public Response find(Request<Member> tParams) {
        Response response = Response.newResponse();
        Member member = tParams.getData();

        if(member== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = member.getPkMember();
        if(primaryKey !=null && !primaryKey.equals("")){
            member = memberMapper.selectByPrimaryKey(primaryKey);
            response.setData(member);
        }else{
            PageHelper.startPage(member.getPageNo(),member.getPageSize());
            List<Member> list = memberMapper.select(member);

            Student student;
            List<Member> memberList = new ArrayList<>();
            if (list.size() > 0) {
                for (Member member1 : list) {
                    student = new Student();
                    student.setPhone(member1.getPhone());
                    List<Student> select = studentMapper.select(student);
                    member1.setStatus(1);
                    if (select.size() > 0) {
                        member1.setStatus(0);
                    }
                    memberList.add(member1);
                }
            }
            PageInfo<Member> pageInfo = new PageInfo<>(memberList);
            response.setData(pageInfo);
        }
        return response;
    }

    @Override
    public Response save(Request<Member> tParams) {
        Response response = Response.newResponse();
        Member member = tParams.getData();

        if(member== null){
            return  response.PARAMS_ISNULL();
        }
        String primaryKey = member.getPkMember();
        Member select = null;
        if (primaryKey != null && !primaryKey.equals("")) {
            select = memberMapper.selectByPrimaryKey(primaryKey);
        }
        int iReuslt = 1;
        String message= "";
        try {
            if (select != null) {
                iReuslt = memberMapper.updateByPrimaryKeySelective(member);
            } else {
                member.setIsdel(1);
                iReuslt = memberMapper.insertSelective(member);
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

    @Override
    public Response audit(Request<Member> tParams) {
        Response response = Response.newResponse();
        Member member = tParams.getData();

        if(member== null){
            return  response.PARAMS_ISNULL();
        }

        String message;
        try {
            String primaryKey = member.getPkMember();
            Member byPrimaryKey = memberMapper.selectByPrimaryKey(primaryKey);
            Student student = new Student();
            student.setPkStudent(GuidUtils.getGuid());
            student.setPhone(byPrimaryKey.getPhone());
            student.setPassword(MD5Util.string2MD5("123456"));
            student.setCaption(byPrimaryKey.getCaption());
            student.setCreator(member.getCreator());
            student.setCreationDate(new Date());
            student.setModifier(member.getModifier());
            student.setPkDomain("1");
            student.setCode(student.getPkStudent());
            student.setShortCode(student.getPkStudent());
            student.setLasteditDate(new Date());
            studentMapper.insertSelective(student);

            member.setIsdel(0);
            memberMapper.updateByPrimaryKeySelective(member);
        }catch (Exception ex){
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }

        return response;
    }

    @Override
    public Response applyForTrial(Request<Member> tParams) {
        Response response = Response.newResponse();
        Member member = tParams.getData();

        if(member== null){
            return  response.PARAMS_ISNULL();
        }

        String message = "";
        String content = member.getCaption() + "," + member.getPhone();
        try {
            SendEmailUtils.sendEmail(content);
        } catch (Exception e) {
            message = e.getMessage();
            return  response.SERVER_ERROR(message);
        }

        return response;
    }
}
