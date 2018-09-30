package com.wuxue.view.controllers.system;

import com.wuxue.model.SysLog;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.SysLogClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping(value = "/system/sysLog")
public class SysLogController extends BaseController
        implements IQueryController<SysLog,String>,IQueryByPagingController<SysLog,Map<String,Object>>{


    @Autowired
    private SysLogClient sysLogClient;


    /**
     * 费用项目列表页面
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, SysLog sysLog)  {
//        sysLog.setpkId(primaryKey);
//        sysLog.setTableName("schedule");
        Response<List<SysLog>> byPrimaryKey2 = sysLogClient.find(sysLog);

        model.addAttribute("syslog", byPrimaryKey2.getData());
        return "/system/sysLogList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, SysLog sysLog, Integer sEcho, Integer start, Integer length) {
        sysLog.setPageNo((start/length)+1);
        sysLog.setPageSize(length);

        Response<List<SysLog>> listResponse = sysLogClient.find(sysLog);
        List<SysLog> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

}
