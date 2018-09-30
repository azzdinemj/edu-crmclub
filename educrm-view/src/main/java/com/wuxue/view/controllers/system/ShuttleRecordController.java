package com.wuxue.view.controllers.system;

import com.wuxue.model.Notice;
import com.wuxue.model.shuttle.BoardingRecord;
import com.wuxue.model.shuttle.DebusRecord;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.shuttle.BoardingRecordClient;
import com.wuxue.view.client.shuttle.DebusRecordClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.DateTimeUtils;
import com.wuxue.view.utils.DomainUtils;
import com.wuxue.view.utils.SysAutoCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * 接送记录
 */
@Controller
@RequestMapping(value = "/system/shuttleRecord")
public class ShuttleRecordController extends BaseController
        implements IQueryController<Notice,String>,IQueryByPagingController<String,Map<String,Object>>  {


    @Autowired
    private BoardingRecordClient boardingRecordClient;

    @Autowired
    private DebusRecordClient debusRecordClient;
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
    public String query(HttpServletRequest request, Model model, Notice notice)  {
//        Response<List<Notice>> listResponse = noticeClient.find(notice);
//
//        model.addAttribute("list",listResponse.getData() );
        return "/shuttle/shuttleList";
    }

    /**
     * 上车记录
     * @param request
     * @param response
     * @param shuttleType
     * @param sEcho
     * @param start
     * @param length
     * @return
     */
    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, String shuttleType, Integer sEcho, Integer start, Integer length) {

        String dateTime = request.getParameter("dateTime");


        if (shuttleType.equals("0")){ //上车
            BoardingRecord boardingRecord = new BoardingRecord();
            boardingRecord.setPageNo((start/length)+1);
            boardingRecord.setPageSize(length);
            if(dateTime != null && !"".equals(dateTime)){
                boardingRecord.setFromDate(DateTimeUtils.StringSubToDate(dateTime).get(0));
                boardingRecord.setToDate(DateTimeUtils.StringSubToDate(dateTime).get(1));
            }
            Response<List<BoardingRecord>> listResponse = boardingRecordClient.find(boardingRecord);
            List<BoardingRecord> data = listResponse.getData();
            if(data==null) {
                data = new ArrayList<>();
            }
            return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
        }else {  //下车
            DebusRecord debusRecord = new DebusRecord();
            debusRecord.setPageNo((start/length)+1);
            debusRecord.setPageSize(length);
            if(dateTime != null && !"".equals(dateTime)){
                debusRecord.setFromDate(DateTimeUtils.StringSubToDate(dateTime).get(0));
                debusRecord.setToDate(DateTimeUtils.StringSubToDate(dateTime).get(1));
            }

            Response<List<DebusRecord>> listResponse = debusRecordClient.find(debusRecord);
            List<DebusRecord> data = listResponse.getData();
            if(data==null) {
                data = new ArrayList<>();
            }
            return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
        }


    }



    public void initPageAttribute(Model model,String pkDomain){
        domainUtils.setModeAttribute(model,"domain",pkDomain);
    }


}
