package com.wuxue.view.controllers.system;

import com.wuxue.constant.Contsants;
import com.wuxue.model.AskForLeave;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.student.AskForLeaveClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IEditController;
import com.wuxue.view.interfaces.IQueryByPagingController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.interfaces.ISaveController;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 老师请假
 */
@Controller
@RequestMapping(value = "/system/employeeaskforleave")
public class EmployeeAskForLeaveController extends BaseController
        implements IQueryController<AskForLeave, String>, ISaveController<AskForLeave, String>,
         IQueryByPagingController<AskForLeave,Map<String,Object>>, IEditController<AskForLeave, String> {

    @Autowired
    private AskForLeaveClient askForLeaveClient;


    /**
     * 列表
     * @param model
     * @return
     * @throws ParseException
     */

    public String query(HttpServletRequest request, Model model, AskForLeave student) {
        return "/system/employeeAskForLeaveList";
    }

    /**
     * 修改
     *
     * @param model
     * @param askForLeave
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, AskForLeave askForLeave) {
        if(askForLeave.getPkAskForLeave()!=null&&askForLeave.getPkAskForLeave()!=""){
            Response<AskForLeave> response=askForLeaveClient.findByPrimaryKey(askForLeave);
            model.addAttribute("stuAskFL",response.getData());
        }
        return "/system/employeeAskForLeave";
    }


    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, AskForLeave askForLeave, Integer sEcho, Integer start, Integer length) {
        askForLeave.setPageNo((start/length)+1);
        askForLeave.setPageSize(length);
        askForLeave.setTypes(Contsants.ASK_FOR_LEAVE_TEACHER);
        Response<List<AskForLeave>> listResponse = askForLeaveClient.find(askForLeave);
        List<AskForLeave> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }


    /**
     * 保存
     *
     * @param request
     * @param model
     * @param askForLeave
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, AskForLeave askForLeave) {

        //时间格式化
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            askForLeave.setStartTime(format.parse(askForLeave.getStartTimeStr()));
            askForLeave.setEndTime(format.parse(askForLeave.getEndTimeStr()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(askForLeave.getPkAskForLeave()==null||askForLeave.getPkAskForLeave().equals("")){
            askForLeave.setCreator(SessionCache.getUserName());
            askForLeave.setModifier(SessionCache.getUserName());
            askForLeave.setTypes(Contsants.ASK_FOR_LEAVE_TEACHER);
        }else{
            askForLeave.setModifier(SessionCache.getUserName());
        }
        return askForLeaveClient.save(askForLeave);
    }



}
