package com.wuxue.view.controllers.tkproduct;


import com.wuxue.model.TkProductMark;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.product.TkProductMarkClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IDeleteController;
import com.wuxue.view.interfaces.IQueryByPagingController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.interfaces.ISaveController;
import com.wuxue.view.utils.GuidUtils;
import com.wuxue.view.utils.SessionCache;
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
 * description: 课程收藏 （废弃）
 * @auther: wh
 * @date: 2018/6/15 12:55
 */
@Controller
@RequestMapping(value = "/guanqiao/productmark")
public class tkProductMarkController extends BaseController
        implements IQueryController<TkProductMark, String>, ISaveController<TkProductMark, String>,IQueryByPagingController<TkProductMark,Map<String,Object>>,
        IDeleteController<TkProductMark, String>{

    @Autowired
    private TkProductMarkClient tkProductOrderClient;

    /**
     *   跳转页面
     * @param model
     * @param student
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, TkProductMark student) {

        return "/student/query";
    }


    /**
     * 保存
     *
     * @param request
     * @param model
     * @param tkProductMark
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, TkProductMark tkProductMark) {
        //Date date = DateTimeUtils.stringToDate(student.getBirthdayTime());
        tkProductMark.setModifier(SessionCache.getUserCode());
        tkProductMark.setPkStudent(GuidUtils.getGuid());
        return tkProductOrderClient.save(tkProductMark);
    }

    /**
     * 删除
     *
     * @param tkProductMark
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, TkProductMark tkProductMark) {
        String response = tkProductOrderClient.delete(tkProductMark.getPkProductMark());
        return response;
    }

    /**
     * 分页
     * @param request
     * @param response
     * @param tkProductMark
     * @param sEcho
     * @param start
     * @param length
     * @return
     */
    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, TkProductMark tkProductMark, Integer sEcho, Integer start, Integer length) {
        tkProductMark.setPageNo((start/length)+1);
        tkProductMark.setPageSize(length);
        Response<List<TkProductMark>> listResponse = tkProductOrderClient.find(tkProductMark);
        List<TkProductMark> data = listResponse.getData();

        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }


}
