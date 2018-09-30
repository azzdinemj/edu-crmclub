package com.wuxue.view.controllers.classinfo;

import com.wuxue.model.ClassinfoActivity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ClassInfoActivityClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
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
 * 实践活动
 */
@Controller
@RequestMapping(value = "/classinfo/classinfoActivity")
public class ClassInfoActivityController extends BaseController
        implements IQueryController<ClassinfoActivity,String>,ISaveController<ClassinfoActivity,String>,
        ICreateController<ClassinfoActivity,String>,IEditController<ClassinfoActivity,String>,IDeleteController<ClassinfoActivity,String> {


    @InitBinder("classinfoActivity")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("exp.");
    }

    @Autowired
    private ClassInfoActivityClient classinfoActivityClient;

    /**
     * 列表页面
     * @param model
     * @return
     * @throws
     */
    @Override
    public String query(HttpServletRequest request, Model model, ClassinfoActivity classinfoActivity)  {
        Response<List<ClassinfoActivity>> listResponse = classinfoActivityClient.find(classinfoActivity);

        model.addAttribute("list",listResponse.getData() );
        return "/student/classinfoActivityList";
    }



    @Override
    public String create(HttpServletRequest request, Model model, ClassinfoActivity classinfoActivity) {

        classinfoActivity.setCreator(SessionCache.getUserCode());
        classinfoActivity.setCreationDate(new Date());
        classinfoActivity.setModifier(SessionCache.getUserCode());
        classinfoActivity.setLasteditDate(new Date());
        model.addAttribute("activity",classinfoActivity);

        return "/student/addActivity";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, ClassinfoActivity classinfoActivity) {
        return classinfoActivityClient.delete(classinfoActivity.getPkClassinfoActivity());
    }

    @Override
    public String edit(HttpServletRequest request, Model model, ClassinfoActivity classinfoActivity) {
        Response<ClassinfoActivity> byPrimaryKey = classinfoActivityClient.findByPrimaryKey(classinfoActivity);
        model.addAttribute("classinfoActivity",byPrimaryKey.getData());
        return "/student/editActivity";
    }




    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, ClassinfoActivity classinfoActivity) {
        if (classinfoActivity.getPkClassinfoActivity()==null || "".equals(classinfoActivity.getPkClassinfoActivity())){
            classinfoActivity.setPkClassinfoActivity(GuidUtils.getGuid());
            classinfoActivity.setCreator(SessionCache.getUserCode());
        }
        classinfoActivity.setModifier(SessionCache.getUserCode());
        return classinfoActivityClient.save(classinfoActivity);
    }


}
