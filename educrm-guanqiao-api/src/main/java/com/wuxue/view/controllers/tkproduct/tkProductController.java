package com.wuxue.view.controllers.tkproduct;


import com.wuxue.model.Product;
import com.wuxue.model.Schedule;
import com.wuxue.model.SysDictValues;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.product.ProductClient;
import com.wuxue.view.client.product.ScheduleClient;
import com.wuxue.view.client.system.SysDictValuesClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IDeleteController;
import com.wuxue.view.interfaces.IQueryByPagingController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.interfaces.ISaveController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * description: 课程（产品）
 * @auther: wh
 * @date: 2018/6/15 12:55
 */
@Controller
@RequestMapping(value = "/guanqiao/product")
public class tkProductController extends BaseController
        implements IQueryController<Product, String>, ISaveController<Product, String>,IQueryByPagingController<Product,Map<String,Object>>,
        IDeleteController<Product, String>{

    @Autowired
    private ProductClient productClient;

    /**
     * 添加页面/save/edit
     *
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    public String edit(HttpServletRequest request, Model model, Product product) {
        if(product.getPkProduct()!=null){
            request.setAttribute("pkProduct",product.getPkProduct());
        }
        return "jiedianhtml/product/product";
    }

    /**
     * 保存
     * @param request
     * @param model
     * @param paramObj
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, Product paramObj) {
        if(paramObj.getProductType().equals("1")){//公开课
            //paramObj.setProductType(" ");
            paramObj.setProductTypetalkcloudroom(3);
        }else{
            paramObj.setProductTypetalkcloudroom(0);
        }
        return productClient.save(paramObj);
    }

    @Autowired
    SysDictValuesClient sysDictValuesClient;

    /**
     * 课程类型
     * @return
     */
    @RequestMapping(value="/getSysdictsValues",method = RequestMethod.GET)
    @ResponseBody
    public  Response getSysdictsValues(SysDictValues sysDictValues){
         Response<List<SysDictValues>> response=sysDictValuesClient.find(sysDictValues);
         return response;
    }

    /**
     *   跳转页面 列表
     * @param model
     * @param paramObj
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, Product paramObj) {
        return "jiedianhtml/product/query";
    }

    /**
     * 分页 前端 一对一课程（不包含体验课）
     * @param request
     * @param response
     * @param paramObj
     * @param sEcho
     * @param start
     * @param length
     * @return
     */
    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Product paramObj, Integer sEcho, Integer start, Integer length) {
        paramObj.setPageNo((start/length)+1);
        paramObj.setPageSize(length);
        Response<List<Product>> listResponse = productClient.findguanqiao(paramObj);
        List<Product> data = listResponse.getData();
        List<Product> data2=new ArrayList<>();
        for (Product p:data) {
            if(!p.getProductType().equals("201830497523622781")){
                data2.add(p);
            }
        }
        if(data2==null) {
            data2 = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data2);
    }

    /**
     * 分页  后台 全部课程
     * @param request
     * @param response
     * @param paramObj
     * @param sEcho
     * @param start
     * @param length
     * @return
     */
    @RequestMapping(value = "/queryByPagingList",method =RequestMethod.GET )
    @ResponseBody
    public Map<String, Object> queryByPagingList(HttpServletRequest request, HttpServletResponse response, Product paramObj, Integer sEcho, Integer start, Integer length) {
        paramObj.setPageNo((start/length)+1);
        paramObj.setPageSize(length);
        Response<List<Product>> listResponse = productClient.findguanqiao(paramObj);
        List<Product> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }


    /**
     * 删除
     *
     * @param paramObj
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, Product paramObj) {
        String response = productClient.delete(paramObj.getPkProduct());
        return response;
    }

    @Autowired
    ScheduleClient scheduleClient;

    /**
     * 公共课产品
     * @param request
     * @param response
     * @param paramObj
     * @param sEcho
     * @param start
     * @param length
     * @return
     */
    @RequestMapping(value = "/queryByPaging2",method =RequestMethod.GET )
    @ResponseBody
    public Map<String, Object> queryByPaging2(HttpServletRequest request, HttpServletResponse response, Product paramObj, Integer sEcho, Integer start, Integer length) {
        paramObj.setPageNo((start/length)+1);
        paramObj.setPageSize(length);
        Response<List<Product>> listResponse = productClient.findguanqiao(paramObj);
        List<Product> data = listResponse.getData();
        List<Product> data2 = new ArrayList<>();
        if(data!=null){
            for(Product product1:data){
                //未开始的公开课
                Schedule schedule=new Schedule();
                schedule.setPkProduct(product1.getPkProduct());
                schedule.setFromDate(new Date());
                Response<List<Schedule>> response1=scheduleClient.findguanqiao(schedule);
                product1.put("schedule",response1.getData());

                //结束的公开课
                Schedule schedule2=new Schedule();
                schedule2.setPkProduct(product1.getPkProduct());
                schedule2.setToDate(new Date());
                Response<List<Schedule>> response2=scheduleClient.findguanqiao(schedule2);
                product1.put("schedule2",response2.getData());

                //if(response1.getData().size()>0&&response2.getData().size()>0){ //没有排课记录不显示
                   data2.add(product1);
                //}
            }
        }

        if(data2==null) {
            data2 = new ArrayList<>();
        }

        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data2);
    }



    /**
     * 根据主键查找课程
     * @param paramObj
     * @return
     */
    @RequestMapping("/getProductByPk")
    @ResponseBody
    public Response getproductbypk(Product paramObj){
      return  productClient.findguanqiaobyPrimarykey(paramObj);
    }


}
