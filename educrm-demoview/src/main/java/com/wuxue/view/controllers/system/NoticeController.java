package com.wuxue.view.controllers.system;

import com.wuxue.base.KeyValue;
import com.wuxue.model.Notice;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.NoticeClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.DomainUtils;
import com.wuxue.view.utils.GuidUtils;
import com.wuxue.view.utils.SessionCache;
import com.wuxue.view.utils.SysAutoCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 费用项目管理
 */
@Controller
@RequestMapping(value = "/system/notice")
public class NoticeController extends BaseController
        implements IQueryController<Notice,String>,ISaveController<Notice,String>,IQueryByPagingController<Notice,Map<String,Object>>,
        ICreateController<Notice,String>,IEditController<Notice,String>,IDeleteController<Notice,String> {


    @Autowired
    private NoticeClient noticeClient;
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
        return "/system/noticeList";
    }

    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Notice notice, Integer sEcho, Integer start, Integer length) {
        notice.setPageNo((start/length)+1);
        notice.setPageSize(length);

        Response<List<Notice>> listResponse = noticeClient.find(notice);
        List<Notice> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    public void initPageAttribute(Model model,String pkDomain){
        domainUtils.setModeAttribute(model,"domain",pkDomain);
    }

    @Override
    public String create(HttpServletRequest request, Model model, Notice notice) {
        notice.setPkNotice(GuidUtils.getGuid());
        KeyValue keyValue = new KeyValue();
        keyValue.setCaption(SessionCache.getUserName());
        notice.put("userEntity",keyValue);
        notice.put("creatorEntity",keyValue);
        notice.put("modifierEntity",keyValue);
        notice.setCreationDate(new Date());
        notice.setLasteditDate(new Date());
        model.addAttribute("notice",notice);
        return "/system/notice";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, Notice notice) {

        return noticeClient.delete(notice.getPkNotice());
}
    @Override
    public String edit(HttpServletRequest request, Model model, Notice notice) {
        Response<Notice> byPrimaryKey = noticeClient.findByPrimaryKey(notice);
        model.addAttribute("notice",byPrimaryKey.getData());
        return "/system/notice";
    }




    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Notice notice) {
        return noticeClient.save(notice);
    }


}
