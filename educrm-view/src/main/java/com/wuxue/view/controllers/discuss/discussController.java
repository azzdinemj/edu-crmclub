package com.wuxue.view.controllers.discuss;

import com.wuxue.base.ResultEntity;
import com.wuxue.model.Employee;
import com.wuxue.model.junhwa.Discuss;
import com.wuxue.model.junhwa.Material;
import com.wuxue.model.junhwa.MealMaterial;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.discuss.DiscussClient;
import com.wuxue.view.client.system.EmployeeClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.SessionCache;
import com.wuxue.view.utils.SysDicEmnuUtils;
import com.wuxue.view.utils.SysDictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 个性化课表
 */
@Controller
@RequestMapping(value = "/discuss/discuss")
public class discussController extends BaseController implements IQueryController<Discuss, String>,
        IQueryByPagingController<ResultEntity, Map<String, Object>> ,ICreateController<Discuss, String>,ISaveController<Discuss, String>, IEditController<Discuss, String> {


    @Autowired
    private DiscussClient discussClient;
    @Autowired
    private SysDictUtils sysDictUtils;
    @Autowired
    private EmployeeClient employeeClient;

    /**
     * 列表页面
     *
     * @param model
     * @return
     * @throws ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, Discuss discuss) {

        return "/system/discussList";
    }


    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, ResultEntity discuss, Integer sEcho, Integer start, Integer length) {

        discuss.setPageNo((start / length) + 1);
        discuss.setPageSize(length);
//        Employee employee = new Employee();
//        employee.setSysUser(SessionCache.getUserCode());
//        Response<List<Employee>> listResponse2 = employeeClient.find(employee);
//        if (listResponse2.getData()!= null && listResponse2.getData().get(0).getPkEmployee() != null) {
//            discuss.setTeacherId(listResponse2.getData().get(0).getPkEmployee());
//        }
        Response<List<ResultEntity>> listResponse = discussClient.find(discuss);
        List<ResultEntity> data = listResponse.getData();
        if (data == null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(), listResponse.getTotal(), data);


    }
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model,Discuss discuss) throws ParseException {
        Employee employee = new Employee();
        employee.setSysUser(SessionCache.getUserCode());
        Response<List<Employee>> listResponse = employeeClient.find(employee);
        if (listResponse.getData()!= null && listResponse.getData().get(0).getPkEmployee() != null) {
            discuss.setUserId(listResponse.getData().get(0).getPkEmployee());
        }
        return discussClient.save(discuss);
    }

//    @ResponseBody
//    @RequestMapping(value = "saveAll", method = RequestMethod.POST)
//    public String saveall(HttpServletRequest request, Model model,Discuss discuss,DiscussList details) throws ParseException {
//        discuss.put(Light.MEAL_MATERIAL,details.getDetails());
//        return discussClient.save(discuss);
//    }
    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, Discuss discuss) {
        return "/meal/meal";
    }

    /**
     * 修改页面
     *
     * @param discuss
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, Discuss discuss) {
        Response<List<Discuss>> discussList = discussClient.getDiscussListForPC(discuss);
        model.addAttribute("discussList",discussList.getData());
        model.addAttribute("discuss",discuss);
        return "/system/discuss";
    }

    @ResponseBody
    @RequestMapping(value = "/theacherReply",method = RequestMethod.POST)
    public String theacherReply(HttpServletRequest request, Model model, Discuss discuss) {
        Employee employee = new Employee();
        employee.setSysUser(SessionCache.getUserCode());
        Response<List<Employee>> listResponse = employeeClient.find(employee);
        if (listResponse.getData()!= null && listResponse.getData().get(0).getPkEmployee() != null) {
            discuss.setUserId(listResponse.getData().get(0).getPkEmployee());
        }
//        System.out.println(discussClient.theacherReply(discuss));
        return discussClient.theacherReply(discuss);
    }

}
