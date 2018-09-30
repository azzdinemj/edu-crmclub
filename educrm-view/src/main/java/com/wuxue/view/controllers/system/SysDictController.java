package com.wuxue.view.controllers.system;

import com.wuxue.model.SysDict;
import com.wuxue.model.SysUser;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.SysDicClient;
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
import java.util.List;

/**
 * 数据字典
 */
@Controller
@RequestMapping(value = "/system/sysDict")
public class SysDictController extends BaseController
        implements IQueryController<SysDict,String>,ISaveController<SysDict, String>,
        ICreateController<SysDict, String>,IEditController<SysDict, String>,IDeleteController<SysDict, String> {

    @InitBinder("sysDict")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("dic.");
    }


    @Autowired
    private SysDicClient sysDicClient;
    @Autowired
    private SysUserClient sysUserClient;



    /**
     * 数据字典列表页面
     * @param model
     * @return
     * @throws
     */
    @Override
    public String query(HttpServletRequest request, Model model, SysDict sysDict) {
        Response<List<SysDict>> listResponse = sysDicClient.find(sysDict);

        model.addAttribute("list",listResponse.getData() );
        return "/system/SysDicList";
    }

    /**
     * 修改页面
     * @param sysDict
     * @param model
     * @return
     */
    @Override
    public String  edit(HttpServletRequest request, Model model,SysDict sysDict){
        Response<SysDict> byPrimaryKey = sysDicClient.findByPrimaryKey(sysDict);
        model.addAttribute("sysDict",byPrimaryKey.getData());
        return "/system/editSysDic";
    }


    /**
     * 保存
     * @param sysDict
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model,SysDict sysDict){
        String userCode = SessionCache.getUserCode();
        if(sysDict.getPkSysDict() != null){
            sysDict.setCreator(userCode);
        }else {
            sysDict.setCreator(userCode);
            sysDict.setModifier(userCode);
        }
        String response = sysDicClient.save(sysDict);
        return response;

    }

    /**
     * 添加页面
     * @return
     */
   @Override
    public String create(HttpServletRequest request, Model model,SysDict sysDict){
       String userCode = SessionCache.getUserCode();

       SysUser sysUser = new SysUser();
       sysUser.setPkSysUser(userCode);
//       Response<SysUser> byPrimaryKey = sysUserClient.findByPrimaryKey(sysUser);
       //sysDict.setPkDomain(byPrimaryKey.getData().getPkDomain());
       model.addAttribute("sysDict",sysDict);

       return "/system/addSysDic";
    }

    /**
     * 删除
     * @param sysDict
     * @return
     */
   @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model,SysDict sysDict){
        String response = sysDicClient.delete(sysDict.getPkSysDict());
        return response;
    }

}
