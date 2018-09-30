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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 管理
 */
@Controller
@RequestMapping(value = "/student/linkman")
public class LinkManController extends BaseController
        implements IQueryController<Linkman,String>,ISaveController<Linkman,String>,
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
