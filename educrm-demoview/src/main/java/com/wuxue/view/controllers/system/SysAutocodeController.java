package com.wuxue.view.controllers.system;

import com.wuxue.model.SysAutocode;
import com.wuxue.model.SysUser;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.DomainClient;
import com.wuxue.view.client.system.SysAutcodeClient;
import com.wuxue.view.client.system.SysUserClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.EmployeeUtils;
import com.wuxue.view.utils.GuidUtils;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 编码规则
 */
@Controller
@RequestMapping(value = "/system/sysAutocode")
public class SysAutocodeController extends BaseController
        implements IQueryController<SysAutocode,String>,ISaveController<SysAutocode,String>,
        ICreateController<SysAutocode,String>,IEditController<SysAutocode,String>,IDeleteController<SysAutocode,String> {


    @InitBinder("sysAutocode")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("sysA.");
    }

    @Autowired
    private SysAutcodeClient sysAutocodeClient;
    @Autowired
    private DomainClient domainClient;
    @Autowired
    private SysUserClient sysUserClient;

    /**
     * 宿舍列表页面
     * @param model
     * @return
     * @throws
     */
    @Override
    public String query(HttpServletRequest request, Model model, SysAutocode sysAutocode)  {
        Response<List<SysAutocode>> listResponse = sysAutocodeClient.find(sysAutocode);

        model.addAttribute("list",listResponse.getData() );
        return "/system/sysAutocodeList";
    }



    @Override

    public String create(HttpServletRequest request, Model model, SysAutocode sysAutocode) {
        String userCode = SessionCache.getUserCode();
        SysUser sysUser = new SysUser();
        sysUser.setPkSysUser(userCode);
        Response<SysUser> byPrimaryKey = sysUserClient.findByPrimaryKey(sysUser);
        sysAutocode.setPkDomain(byPrimaryKey.getData().getPkDomain());
        sysAutocode.put(LinkEntity.CREATOR_ENTITY, EmployeeUtils.getSysUser());
        sysAutocode.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
        sysAutocode.setCreationDate(new Date());
        sysAutocode.setLasteditDate(new Date());
        model.addAttribute("sysAutocode",sysAutocode);

        return "/system/sysAutocode";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, SysAutocode sysAutocode) {
        return sysAutocodeClient.delete(sysAutocode.getPkSysAutocode());
    }

    @Override
    public String edit(HttpServletRequest request, Model model, SysAutocode sysAutocode) {
        Response<SysAutocode> byPrimaryKey = sysAutocodeClient.findByPrimaryKey(sysAutocode);
        model.addAttribute("sysAutocode",byPrimaryKey.getData());
        return "/system/sysAutocode";
    }




    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, SysAutocode sysAutocode) {
        if(sysAutocode.getPkSysAutocode()==null || "".equals(sysAutocode.getPkSysAutocode())){
            sysAutocode.setCreator(SessionCache.getUserCode());
            sysAutocode.setPkSysAutocode(GuidUtils.getGuid());
        }
        sysAutocode.setModifier(SessionCache.getUserCode());

        sysAutocode.setKind(sysAutocode.getDate());
        return sysAutocodeClient.save(sysAutocode);
    }


}
