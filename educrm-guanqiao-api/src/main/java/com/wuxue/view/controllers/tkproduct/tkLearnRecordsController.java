package com.wuxue.view.controllers.tkproduct;


import com.wuxue.model.TkLearnRecords;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.product.TkLearnRecordsClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IDeleteController;
import com.wuxue.view.interfaces.IQueryByPagingController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.interfaces.ISaveController;
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
 *
 * description: 学习记录
 * @auther: wh
 * @date: 2018/6/15 12:55
 */
@Controller
@RequestMapping(value = "/guanqiao/learnrecords")
public class tkLearnRecordsController extends BaseController
        implements IQueryController<TkLearnRecords, String>, ISaveController<TkLearnRecords, String>,IQueryByPagingController<TkLearnRecords,Map<String,Object>>,
        IDeleteController<TkLearnRecords, String>{

    @Autowired
    private TkLearnRecordsClient tkProductOrderClient;

    /**
     *   跳转页面
     * @param model
     * @param student
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, TkLearnRecords student) {
        return "/student/query";
    }


    /**
     * 保存
     *
     * @param request
     * @param model
     * @param tkLearnRecords
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model,TkLearnRecords tkLearnRecords) {
        return tkProductOrderClient.save(tkLearnRecords);
    }

    /**
     * 删除
     *
     * @param tkProductMark
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, TkLearnRecords tkProductMark) {
        String response = tkProductOrderClient.delete(tkProductMark.getPkLearnRecords());
        return response;
    }

    /**
     * 分页
     * @param request
     * @param response
     * @param tkLearnRecords
     * @param sEcho
     * @param start
     * @param length
     * @return
     */
    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, TkLearnRecords tkLearnRecords, Integer sEcho, Integer start, Integer length) {
        tkLearnRecords.setPageNo((start/length)+1);
        tkLearnRecords.setPageSize(length);
        Response<List<TkLearnRecords>> listResponse = tkProductOrderClient.find(tkLearnRecords);
        List<TkLearnRecords> data = listResponse.getData();

        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

}
