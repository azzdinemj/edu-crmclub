package com.wuxue.view.controllers.system;

import com.wuxue.model.Task;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.TaskClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.DomainUtils;
import com.wuxue.view.utils.GuidUtils;
import com.wuxue.view.utils.SessionCache;
import com.wuxue.view.utils.SysAutoCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 费用项目管理
 */
@Controller
@RequestMapping(value = "/finance/task")
public class TaskController extends BaseController
        implements IQueryController<Task,String>,ISaveController<Task,String>,IQueryByPagingController<Task,Map<String,Object>>,
        ICreateController<Task,String>,IEditController<Task,String>,IDeleteController<Task,String> {


    @InitBinder("task")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("exp.");
    }

    @Autowired
    private TaskClient taskClient;
    @Autowired
    private DomainUtils domainUtils;
    @Autowired
    private SysAutoCodeUtils sysAutoCodeUtils;

    /**
     * 费用项目列表页面
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, Task task)  {
//        Response<List<Task>> listResponse = taskClient.find(task);
//
//        model.addAttribute("list",listResponse.getData() );
        return "/financial/taskList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Task task, Integer sEcho, Integer start, Integer length) {
        task.setPageNo((start/length)+1);
        task.setPageSize(length);

        Response<List<Task>> listResponse = taskClient.find(task);
        List<Task> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    public void initPageAttribute(Model model,String pkDomain){
        domainUtils.setModeAttribute(model,"domain",pkDomain);
    }

    @Override
    public String create(HttpServletRequest request, Model model, Task task) {
        return "/financial/task";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, Task task) {

        return taskClient.delete(task.getPkTask());
}
    @Override
    public String edit(HttpServletRequest request, Model model, Task task) {
        return "/financial/task";
    }




    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Task task) {
        if(task.getPkTask() == null || "".equals(task.getPkTask())){
            task.setPkTask(GuidUtils.getGuid());
            task.setCreator(SessionCache.getUserCode());
            task.setModifier(SessionCache.getUserCode());
        }else {
            task.setModifier(SessionCache.getUserCode());
        }
        return taskClient.save(task);
    }


}
