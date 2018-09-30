package com.wuxue.view.controllers.tkproduct;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.model.*;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.product.ProductClient;
import com.wuxue.view.client.product.ScheduleClient;
import com.wuxue.view.client.product.TkLearnRecordsClient;
import com.wuxue.view.client.product.TkProductOrderClient;
import com.wuxue.view.client.system.TkPayRecordsClient;
import com.wuxue.view.client.talkcloud.TalkCloudRoomClient;
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
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * description: 课程预约
 * @auther: wh
 * @date: 2018/6/15 12:55
 */
@Controller
@RequestMapping(value = "/guanqiao/productorder")
public class tkProductOrderController extends BaseController
        implements IQueryController<TkProductOrder, String>, ISaveController<TkProductOrder, Response>,IQueryByPagingController<TkProductOrder,Map<String,Object>>,
        IDeleteController<TkProductOrder, Response>{

    @Autowired
    private TkProductOrderClient tkProductOrderClient;
    @Autowired
    private ScheduleClient scheduleClient;
    @Autowired
    private ProductClient productClient;
    @Autowired
    private TkLearnRecordsClient tkLearnRecordsClient;
    @Autowired
    private TkPayRecordsClient tkPayRecordsClient;
    @Autowired
    private TalkCloudRoomClient talkCloudRoomClient;

    /**
     *   跳转页面
     * @param model
     * @param student
     * @return
     */
    @Override
    public String query(HttpServletRequest request, Model model, TkProductOrder student) {
        return "/student/query";
    }


    //计算我剩余的课时
    public Integer myclasshours(String pkStudent){
        Integer total=0;//总数
        Integer use=0; //已使用
        Integer overplus=0; //剩余

        if(pkStudent!=null) {
            //学生支付订单
            TkPayRecords tkPayRecords = new TkPayRecords();
            tkPayRecords.setPkStudent(pkStudent);
            tkPayRecords.setStatus(0);
            Response<List<TkPayRecords>> recordsResponse = tkPayRecordsClient.find(tkPayRecords);
            if (recordsResponse.getData()!=null&&recordsResponse.getData().size()>0) {
                List<TkPayRecords> tkPayRecordsList = recordsResponse.getData();
                for (TkPayRecords t : tkPayRecordsList) {
                    Map<String,Object> maps=t.getMap();
                    Object object=maps.get(Light.TKSETMEAL);
                    if(object!=null){
                        total=total+(Integer)object;//总共购买的课时
                    }
                }
            }

            //学生 学习记录
            TkLearnRecords tkLearnRecords = new TkLearnRecords();
            tkLearnRecords.setPkStudent(pkStudent);
            Response<List<TkLearnRecords>> listResponse = tkLearnRecordsClient.find(tkLearnRecords);

            if (listResponse.getData()!=null&&listResponse.getData().size()>0) {
                for (TkLearnRecords tkLearnRecords1:listResponse.getData()) {
                    Schedule schedule=new Schedule();
                    schedule.setPkSchedule(tkLearnRecords1.getPkSchedule());
                    Response<Schedule> scheduleResponse=scheduleClient.findguanqiaoBypk(schedule);
                    use=use+scheduleResponse.getData().getProductUseclasshours();
                }

            }
        }
        overplus=total-use;
        return overplus;
    }



    /**
     * 保存  --预约课程
     * @param request
     * @param model
     * @param tkProductOrder
     * @return
     */
    @Override
    @ResponseBody
    public Response save(HttpServletRequest request, Model model, TkProductOrder tkProductOrder) {
        Response response=Response.newResponse();
        Schedule schedule=new Schedule();
        schedule.setPkSchedule(tkProductOrder.getPkSchedule());
        Response<Schedule> scheduleResponse=scheduleClient.findByPrimaryKey(schedule); //预约的排课对象

        Product product1=new Product();
        product1.setPkProduct(scheduleResponse.getData().getPkProduct());
        Response<Product> productResponse= productClient.findguanqiaobyPrimarykey(product1);
        if(productResponse.getData().getProductType().equals("201830497523622781")){ //体验课

        }else if(tkProductOrder.getPkStudent()!=null&&!"".equals(tkProductOrder.getPkStudent())){ //一对一
            if(myclasshours(tkProductOrder.getPkStudent())<=0){ //预约前查询 课时余额
                response.setCode(1);
                response.setMessage("课时余额不足!!!");
                return response;
            }
            schedule.setPkStudent(tkProductOrder.getPkStudent());  //排课记录 插入学生
            scheduleClient.save(schedule);
        }else{
            return  response.PARAMS_ISNULL();
        }

        response= tkProductOrderClient.save(tkProductOrder);
        if(response.getCode()==0){
            if(response.getData()!=null){//预约量增加
                Product p=productResponse.getData();
                p.setProductNum(p.getProductNum()+1);
                return JSON.parseObject(productClient.save(p), new TypeReference<Response<Object>>(){});
            }
        }
        return  response;
    }



    /**
     * 保存  --批量预约 公开课课程
     *
     * 参数 ； pkProduct  pkStudent
     * @param request
     * @param model
     * @param tkProductOrder
     * @return
     */
    @RequestMapping("/saveAll")
    @ResponseBody
    public Response saveAll(HttpServletRequest request, Model model, Schedule tkProductOrder) {
        Response response=Response.newResponse();

        Schedule schedule=new Schedule();
        schedule.setPkProduct(tkProductOrder.getPkProduct());
        schedule.setPkStudent(tkProductOrder.getPkStudent());
        //该学生 对于该产品的所有未预约的排课
        Response<List<Schedule>> scheduleResponse=scheduleClient.findguanqiaoByNotOrder(schedule);

        int i=0; //计算预约全部课程消耗的课时
        if(scheduleResponse.getData().size()>0){
            for (Schedule s:scheduleResponse.getData()) {
                i=i+s.getProductUseclasshours();
            }
        }
        if(myclasshours(tkProductOrder.getPkStudent())<i){ //预约前查询 课时余额
            response.setCode(1);
            response.setMessage("课时余额不足!!!");
            return response;
        }
        for(Schedule s:scheduleResponse.getData()){
            if(s.getStatus()==0){ //未开始的课程  生成预约记录
                TkProductOrder productOrder=new TkProductOrder();
                productOrder.setPkStudent(tkProductOrder.getPkStudent());
                productOrder.setPkSchedule(s.getPkSchedule());
                productOrder.setType(1); //公开课
                response= tkProductOrderClient.save(productOrder);

                if(response.getCode()==0){
                    Product product=new Product();
                    product.setPkProduct(tkProductOrder.getPkProduct());

                    Response<Product> res=productClient.findByPrimaryKey(product);
                    if(res.getData().getProductNum()==null){
                        res.getData().setProductNum(1);
                    }else {
                        res.getData().setProductNum(res.getData().getProductNum()+1);
                    }
                    productClient.save(res.getData());
                    //return JSON.parseObject(productClient.save(p), new TypeReference<Response<Object>>(){});
                }
            }else if(s.getStatus()==1){ //已经结束的课程  生成学习记录
                TkLearnRecords tkLearnRecords=new TkLearnRecords();
                tkLearnRecords.setType("1");
                tkLearnRecords.setPkStudent(tkProductOrder.getPkStudent());
                tkLearnRecords.setPkSchedule(s.getPkSchedule());
                String str=tkLearnRecordsClient.save(tkLearnRecords);
            }

        }

        return  response;
    }




    /**
     * 教师点击课程结束回调通知。
     *
     *     拓客云后台设置回调地址:
     *     登录拓客云后台>>>企业信息>>>企业设置
     * @param request
     * @return
     */
    @RequestMapping("/callback")
    @ResponseBody
    public Response callback(HttpServletRequest request) {
        Response response=Response.newResponse();

        String serial= request.getParameter("serial");
        TalkCloudRoom talkCloudRoom=new TalkCloudRoom();
        talkCloudRoom.setSerial(serial);

        //获取该拓客云教室在本地的储存的对象
        Response<List<TalkCloudRoom>> talkCloudRoomResponse=talkCloudRoomClient.find(talkCloudRoom);

        //查询关联到的排课记录
        Schedule schedule=new Schedule();
        schedule.setPkTalkCloudRoom(talkCloudRoomResponse.getData().get(0).getPkTalkCloudRoom());
        Response<List<Schedule>> listResponse=scheduleClient.find(schedule);

        Schedule sch=new Schedule(); //排课  修改为已完成
        sch.setPkSchedule(listResponse.getData().get(0).getPkSchedule());
        sch.setStatus(1);
        Response<Schedule> response1 = JSON.parseObject(scheduleClient.save(sch), new TypeReference<Response<Schedule>>() {});

        Product product1=new Product(); //查找出排课的产品
        product1.setPkProduct(listResponse.getData().get(0).getPkProduct());
        Response<Product> productResponse=productClient.findguanqiaobyPrimarykey(product1);

        if(productResponse.getData().getProductTypetalkcloudroom()==0){
               //一对一
            if(response1.getCode()==0){
                TkProductOrder tkProductOrder=new TkProductOrder();//根据排课查找该条预约记录
                tkProductOrder.setPkSchedule(listResponse.getData().get(0).getPkSchedule());
                Response<List<TkProductOrder>> listResponse1=tkProductOrderClient.find(tkProductOrder);

                //修改 已预约为已完成
                tkProductOrder.setPkProductOrder(listResponse1.getData().get(0).getPkProductOrder());
                tkProductOrder.setStatus(1);
                response= tkProductOrderClient.save(tkProductOrder);
                //创建学习记录
                if(response.getCode()==0){
                    //创建学习记录
                    TkLearnRecords tkLearnRecords=new TkLearnRecords();
                    tkLearnRecords.setProductType(0); //正常
                    tkLearnRecords.setPkStudent(listResponse1.getData().get(0).getPkStudent());
                    tkLearnRecords.setPkSchedule(listResponse1.getData().get(0).getPkSchedule());
                    response=JSON.parseObject( tkLearnRecordsClient.save(tkLearnRecords), new TypeReference<Response<Schedule>>() {});
                }

            }

        }else{
               //一对多
            if(response1.getCode()==0){
                TkProductOrder tkProductOrder=new TkProductOrder();//预约记录集合
                tkProductOrder.setPkSchedule(listResponse.getData().get(0).getPkSchedule());
                Response<List<TkProductOrder>> listResponse1=tkProductOrderClient.find(tkProductOrder);
                for (TkProductOrder productOrder:listResponse1.getData()) {
                    productOrder.setStatus(1);
                    //修改 已预约为已完成
                    response=tkProductOrderClient.save(productOrder);

                    if(response.getCode()==0){
                        //创建学习记录
                        TkLearnRecords tkLearnRecords=new TkLearnRecords();
                        tkLearnRecords.setPkStudent(productOrder.getPkStudent());
                        tkLearnRecords.setProductType(0); //正常
                        tkLearnRecords.setPkSchedule(productOrder.getPkSchedule());
                        response=JSON.parseObject( tkLearnRecordsClient.save(tkLearnRecords), new TypeReference<Response<Schedule>>() {});
                    }
                }

            }
            return response;

        }

        return  response;
    }



    /**
     * 删除(取消预约)
     *
     * @param tkProductOrder
     * @return
     */
    @Override
    @ResponseBody
    public Response delete(HttpServletRequest request, Model model, TkProductOrder tkProductOrder) {
       return JSON.parseObject(tkProductOrderClient.delete(tkProductOrder), new TypeReference<Response>() {});
    }

    /**
     * 分页
     * @param request
     * @param response
     * @param tkProductOrder
     * @param sEcho
     * @param start
     * @param length
     * @return
     */
    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, TkProductOrder tkProductOrder, Integer sEcho, Integer start, Integer length) {
        tkProductOrder.setPageNo((start/length)+1);
        tkProductOrder.setPageSize(length);
        Response<List<TkProductOrder>> listResponse = tkProductOrderClient.find(tkProductOrder);
        List<TkProductOrder> data = listResponse.getData();

        if(data==null) {
            data = new ArrayList<>();
        }
        Collections.sort(data);
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }
}
