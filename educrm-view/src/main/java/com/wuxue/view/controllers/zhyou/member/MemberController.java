package com.wuxue.view.controllers.zhyou.member;

import com.github.pagehelper.PageInfo;
import com.wuxue.constant.Contsants;
import com.wuxue.model.Member;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.MemberClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.GuidUtils;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

/**
 * 预约管理
 */
@Controller
@RequestMapping(value = "/zhyou/member")
public class MemberController extends BaseController implements
        IQueryController<Member,String>,IEditController<Member,String>,
        IDeleteController<Member,String>,ISaveController<Member,String>,IAuditController<Member,Response>{

    @Autowired
    MemberClient memberClient;

    /**
     * 预约列表页面
     * @param model
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, Member member)  {
        //页码，每页数量
        String str=request.getParameter("pageNo");
        if(str!=null&&str!=""){
            int pageNo=Integer.parseInt(str);
            member.setPageNo(pageNo);
        }else{
            member.setPageNo(Contsants.PAGE_NO);
        }
        member.setPageSize(Contsants.PAGE_SIZE);

        Response<PageInfo<Member>> response=memberClient.find(member);
        PageInfo<Member> pageInfo=response.getData();

        model.addAttribute("member", pageInfo);
        return "/zhyou/member/memberlist";
    }

    /**
     * 编辑预约
     * @param request
     * @param model
     * @param member
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, Member member) {
        Response<Member> response=memberClient.findByPrimaryKey(member);
        model.addAttribute("member",response.getData());
        return "/zhyou/member/addmembers";
    }

    @RequestMapping(value = "/successlist",method = RequestMethod.GET)
    public String success(HttpServletRequest request, Model model, Member member) {
        return "/zhyou/member/successlist";
    }

    /**
     * 删除预约
     * @param request
     * @param imodel
     * @param member
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model imodel, Member member) {
        String  pk=member.getPkMember();
        return memberClient.delete(pk);
    }

    /**
     * 添加预约
     * @param request
     * @param model
     * @param member
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Member member) {
        //若主键为空，则执行添加对象赋值
        if(member.getPkMember()==null||member.getPkMember()==""){
            SessionCache.setUserCode("admin");
            SessionCache.setPassword("123456");
            member.setPkMember(GuidUtils.getGuid());
            member.setContnet("预约活动");
        }

        return memberClient.save(member);
    }


    @Override
    @ResponseBody
    public Response audit(HttpServletRequest request, Model model, Member member) {

        member.setModifier(SessionCache.getUserCode());
        member.setCreator(SessionCache.getUserCode());
        Response response = memberClient.audit(member);
        return response;
    }
}
