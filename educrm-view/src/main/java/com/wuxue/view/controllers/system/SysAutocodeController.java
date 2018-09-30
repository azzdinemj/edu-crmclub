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
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 编码规则
 */
@Controller
@RequestMapping(value = "/system/sysAutocode")
public class SysAutocodeController extends BaseController
        implements IQueryController<SysAutocode,String>,ISaveController<SysAutocode,String>,IQueryByPagingController<SysAutocode,Map<String,Object>>,
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
//        Response<List<SysAutocode>> listResponse = sysAutocodeClient.find(sysAutocode);
//
//        model.addAttribute("list",listResponse.getData() );
        return "/system/sysAutocodeList";
    }


    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, SysAutocode sysAutocode, Integer sEcho, Integer start, Integer length) {
        sysAutocode.setPageNo((start/length)+1);
        sysAutocode.setPageSize(length);
        Response<List<SysAutocode>> listResponse = sysAutocodeClient.find(sysAutocode);
        List<SysAutocode> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
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
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        Date careatedate=null;
        Date editdate=null;
        try {
             careatedate = sdf.parse( sysAutocode.getCreateDateStr() );
             editdate = sdf.parse( sysAutocode.getLastEditDateStr() );
        } catch (ParseException e) {
             e.printStackTrace();
        }

        if(sysAutocode.getPkSysAutocode()==null || "".equals(sysAutocode.getPkSysAutocode())){
            //新增
            sysAutocode.setCreator(SessionCache.getUserCode());//创建人
            sysAutocode.setModifier(SessionCache.getUserCode());//修改人
            sysAutocode.setPkSysAutocode(GuidUtils.getGuid());//pk
            sysAutocode.setLasteditDate(editdate);            //修改时间
            sysAutocode.setCreationDate(careatedate);         //创建时间
        }else{
            //编辑
            sysAutocode.setCreator(null);            //不修改创建人
            sysAutocode.setModifier(SessionCache.getUserCode()); //修改人
            sysAutocode.setLasteditDate(editdate);               //修改时间
        }
        sysAutocode.setKind(sysAutocode.getDate());
        return sysAutocodeClient.save(sysAutocode);
    }


}
