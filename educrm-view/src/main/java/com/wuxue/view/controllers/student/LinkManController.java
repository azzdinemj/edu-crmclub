package com.wuxue.view.controllers.student;

import com.wuxue.model.Linkman;
import com.wuxue.model.SysUser;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.LinkManClient;
import com.wuxue.view.client.system.SysUserClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 管理
 */
@Controller
@RequestMapping(value = "/student/linkman")
public class LinkManController extends BaseController
        implements IQueryController<Linkman,String>,ISaveController<Linkman,String>,IQueryByPagingController<Linkman,Map<String,Object>>,
        ICreateController<Linkman,String>,IEditController<Linkman,String>,IDeleteController<Linkman,String> {


    @InitBinder("linkman")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("man.");
    }

    @Autowired
    private LinkManClient linkmanClient;
    @Autowired
    private SysUserClient sysUserClient;

    /**
     * 列表页面
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, Linkman linkman)  {
        Response<List<Linkman>> listResponse = linkmanClient.find(linkman);

        model.addAttribute("list",listResponse.getData() );
        return "/student/linkmanList";
    }

    /**
     * 关联家长列表
     * @param request
     * @param model
     * @param linkman
     * @return
     */
    @RequestMapping(value = "/findLinkman",method = RequestMethod.GET)
    public String findLinkman(HttpServletRequest request, Model model, Linkman linkman)  {

        return "/model/linkmanListModel";
    }



    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Linkman linkman, Integer sEcho, Integer start, Integer length) {

        linkman.setPageNo((start/length)+1);
        linkman.setPageSize(length);
        Response<List<Linkman>> listResponse = linkmanClient.find(linkman);
        List<Linkman> data = listResponse.getData();

        if(data==null) {
            data = new ArrayList<>();
        }

        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }



    @Override
    public String create(HttpServletRequest request, Model model, Linkman linkman) {
        String userCode = SessionCache.getUserCode();
        SysUser sysUser = new SysUser();
        sysUser.setPkSysUser(userCode);
        Response<SysUser> sysUserpkDomain = sysUserClient.findByPrimaryKey(sysUser);
        linkman.setPkDomain(sysUserpkDomain.getData().getPkDomain());
        model.addAttribute("linkman",linkman);

        return "/student/addLinkman";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, Linkman linkman) {
        return linkmanClient.delete(linkman.getPkLinkman());
    }

    @Override
    public String edit(HttpServletRequest request, Model model, Linkman linkman) {
        Response<Linkman> byPrimaryKey = linkmanClient.findByPrimaryKey(linkman);
        model.addAttribute("linkman",byPrimaryKey.getData());
        return "/student/editLinkman";
    }




    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Linkman linkman) {
        String userName = SessionCache.getUserName();
        if(linkman.getPkLinkman() != null){
            linkman.setModifier(userName);
        }else {
            linkman.setCreator(userName);
            linkman.setModifier(userName);
        }

        return linkmanClient.save(linkman);
    }


}
