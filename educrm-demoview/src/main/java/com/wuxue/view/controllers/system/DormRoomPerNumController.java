package com.wuxue.view.controllers.system;

import com.wuxue.model.DormRoomPerNum;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.DormRoomClient;
import com.wuxue.view.client.system.DormRoomPerNumClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IQueryByPagingController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping(value = "/system/dormRoomPerNum")
public class DormRoomPerNumController extends BaseController
        implements IQueryController<DormRoomPerNum, String>,IQueryByPagingController<DormRoomPerNum,Map<String,Object>> {


    @Autowired
    private DormRoomClient dormRoomClient;
    @Autowired
    private DormRoomPerNumClient dormRoomPerNumClient;

    /**
     * 列表页面
     *
     * @param model
     * @return
     * @throws ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, DormRoomPerNum dormRoomPerNum) {

//        schoolBusStudentNumClient.find(schoolBusStudentNum);

        return "/system/dormRoomPerNumList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, DormRoomPerNum dormRoomPerNum, Integer sEcho, Integer start, Integer length) {
        dormRoomPerNum.setPageNo((start/length)+1);
        dormRoomPerNum.setPageSize(length);
        dormRoomPerNum.setPkDomain(SessionCache.getPkdomain());
        Response<List<DormRoomPerNum>> listResponse = dormRoomPerNumClient.find(dormRoomPerNum);
        List<DormRoomPerNum> data = listResponse.getData();

        if(data==null) {
            data = new ArrayList<>();
        }

        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }



}
