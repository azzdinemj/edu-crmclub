package com.wuxue.view.controllers.classinfo;

import com.wuxue.model.ClassTime;
import com.wuxue.model.SysUser;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ClassTimeClient;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 上课时间
 */
@Controller
@RequestMapping(value = "/classinfo/classTime")
public class ClassTimeController extends BaseController 
        implements IQueryController<ClassTime, String>, ISaveController<ClassTime, String>,IQueryByPagingController<ClassTime,Map<String,Object>>,
        ICreateController<ClassTime, String>, IEditController<ClassTime, String>, IDeleteController<ClassTime, String> {

    @InitBinder("classTime")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("time.");
    }

    @Autowired
    private ClassTimeClient classTimeClient;
    @Autowired
    private SysUserClient sysUserClient;
    @Autowired
    private SysDictUtils sysDictUtils;


    /**
     * 新生报名初始化下拉框
     */
    private void initReportPageAttribute(Model model,String pkDomain){
        //学部
        sysDictUtils.setModeAttribute(model, "division", SysDicEmnuUtils.DIVISION);
    }
    /**
     * 上课时间列表页面
     * @param model
     * @return
     * @throws java.text.ParseException
     */
   @Override
    public String query(HttpServletRequest request, Model model, ClassTime classTime) {
//        Response<List<ClassTime>> listResponse = classTimeClient.find(classTime);
//
//        model.addAttribute("list",listResponse.getData() );
        return "/classinfo/classTimeList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, ClassTime classTime, Integer sEcho, Integer start, Integer length) {
        classTime.setPageNo((start/length)+1);
        classTime.setPageSize(length);
        classTime.setIsvalid(1);
        Response<List<ClassTime>> listResponse = classTimeClient.find(classTime);
        List<ClassTime> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    /**
     * 修改页面
     * @param classTime
     * @param model
     * @return
     */
    @Override
    public String  edit(HttpServletRequest request, Model model,ClassTime classTime){
        Response<ClassTime> byPrimaryKey = classTimeClient.findByPrimaryKey(classTime);
        model.addAttribute("classTime",byPrimaryKey.getData());
        initReportPageAttribute(model,SessionCache.getPkdomain());

        return "/classinfo/classTime";
    }


    /**
     * 保存
     * @param classTime
     * @param request
     * @return
     */
   @Override
   @ResponseBody
    public String save(HttpServletRequest request, Model model,ClassTime classTime){
       if(classTime.getPkClassTime()== null || "".equals(classTime.getPkClassTime()) ){
           classTime.setPkClassTime(GuidUtils.getGuid());
           classTime.setCreator(SessionCache.getUserCode());
       }
       classTime.setModifier(SessionCache.getUserCode());


       String response = classTimeClient.save(classTime);
        return response;

    }

    /**
     * 添加页面
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model,ClassTime classTime){
        classTime = new ClassTime();
        //classTime.setIsvalid(1);
        classTime.setPkDomain(SessionCache.getPkdomain());
        classTime.put(LinkEntity.CREATOR_ENTITY, EmployeeUtils.getSysUser());
        classTime.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
        classTime.setCreationDate(new Date());
        classTime.setLasteditDate(new Date());
        model.addAttribute("classTime",classTime);
        initReportPageAttribute(model,SessionCache.getPkdomain());

        return "/classinfo/classTime";
    }

    /**
     * 删除
     * @param classTime
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model,ClassTime classTime){
        String response = classTimeClient.delete(classTime.getPkClassTime());
        return response;
    }





}
