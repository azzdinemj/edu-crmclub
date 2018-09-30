package com.wuxue.view.controllers.tkschedule;

import com.wuxue.model.Employee;
import com.wuxue.model.Product;
import com.wuxue.model.Schedule;
import com.wuxue.model.SysDictValues;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.product.ProductClient;
import com.wuxue.view.client.product.ScheduleClient;
import com.wuxue.view.client.system.EmployeeClient;
import com.wuxue.view.client.system.SysDictValuesClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IQueryByPagingController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.utils.DateTimeUtils;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 课程记录
 */
@Controller
@RequestMapping(value = "/guanqiao/scheduleList")
public class tkScheduleListController extends BaseController
        implements IQueryController<Schedule, String>,
        IQueryByPagingController<Schedule,Map<String,Object>>,Serializable{

    @Autowired
    private ScheduleClient scheduleClient;

    /**
     * 课程记录列表---跳转页面
     *
     * @param model
     * @return
     * @throws
     */
    @Override
    public String query(HttpServletRequest request, Model model, Schedule student) {
        return "jiedianhtml/schedule/scheduleList";
    }

    /**
     * 课程记录列表---跳转页面  user
     *
     * @param model
     * @return
     * @throws
     */
    @RequestMapping("queryuser")
    public String query2(HttpServletRequest request, Model model, Schedule student) {
        return "jiedianhtml/schedule/scheduleListuser";
    }

    /**
     * 上传课件---跳转页面
     *
     * @param model
     * @return
     * @throws
     */
    @RequestMapping("uploadfile")
    public String uploadfile(HttpServletRequest request, Model model, Schedule student) {
        return "jiedianhtml/schedule/addFile";
    }


    /**
     *  根据条件查找出排课记录(条件：课程主键，日期，老师)
     * @param request
     * @param response
     * @param schedule
     * @param sEcho
     * @param start
     * @param length
     * @return
     */
    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, Schedule schedule, Integer sEcho, Integer start, Integer length) {
        schedule.setPageNo((start/length)+1);
        schedule.setPageSize(length);

        String dateTime = request.getParameter("dateTime");
        if (dateTime != null && !"".equals(dateTime)) {
            String substring = dateTime.substring(0, 10);
            String substring1 = dateTime.substring(12);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date strTime = simpleDateFormat.parse(substring);
                Date endTime = simpleDateFormat.parse(substring1);
                schedule.setFromDate(strTime);
                schedule.setToDate(endTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Response<List<Schedule>> listResponse = scheduleClient.findguanqiao(schedule);
        List<Schedule> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }


    @Autowired
    EmployeeClient employeeClient;

    @Autowired
    ProductClient productClient;

    @Autowired
    SysDictValuesClient sysDictValuesClient;

    /**
     * 初始化，查找某一个课程的所有排课的老师的国籍.
     *        查找该课程的caption.
     * @param schedule  （pkProduct)
     *  @return
     */
    @RequestMapping("/getteachercountry")
    @ResponseBody
    public  Map<String,String>  getteacher(Schedule schedule){
        Map<String,String> map=new HashMap<>();

        schedule.setPageNo(1);
        schedule.setPageSize(1000);
        Response<List<Schedule>> listResponse = scheduleClient.findguanqiao(schedule); //该课程的所有排课
        List<Schedule> data = listResponse.getData();
        if(data!=null){
            for(Schedule s:data){ //循环排课，查找老师
                Employee employee=new Employee();
                employee.setPkEmployee(s.getPkEmployee());
                Response<Employee> employeeResponse=employeeClient.findByPrimaryKey(employee);
                if(employeeResponse.getData()!=null&&employeeResponse.getData().getNationality()!=null&&!"".equals(employeeResponse.getData().getNationality())){
                    //查找老师的国籍
                    SysDictValues sysDictValues=new SysDictValues();
                    sysDictValues.setPkSysDictValues(employeeResponse.getData().getNationality());
                    Response<SysDictValues> sysDictValuesResponse= sysDictValuesClient.findByPrimaryKey(sysDictValues);

                    if(!map.containsKey(sysDictValuesResponse.getData().getPkSysDictValues())){
                        map.put(sysDictValuesResponse.getData().getPkSysDictValues(),sysDictValuesResponse.getData().getCaption());
                    }
                }
            }
        }

        Product product1=new Product();
        product1.setPkProduct(schedule.getPkProduct());
        Response<Product> response=productClient.findguanqiaobyPrimarykey(product1);
        if(response.getData()!=null){
            map.put("product",response.getData().getCaption());
        }else{
            map.put("product","未查找到该课程！");
        }
         return map;
    }


    /**
     *  根据条件查找出排课记录(老师为基础查找该老师所有的排课记录)
     * @param request
     * @param response
     * @param schedule 国籍，日期，课程主键
     * @param sEcho
     * @param start   分页 从第几条数据开始
     * @param length  分页 每页的数量
     * @return
     */
   @RequestMapping("/queryByPaging2")
    @ResponseBody
    public Map<String, Object> queryByPaging2(HttpServletRequest request, HttpServletResponse response, Schedule schedule, Integer sEcho, Integer start, Integer length) {
        schedule.setPageNo((start/length)+1);
        schedule.setPageSize(length);
        Date startTime = DateTimeUtils.stringToDate(schedule.getStartTimes());
        schedule.setFromDate(startTime);
        Response<List<Employee>> listResponse = scheduleClient.findguanqiaoby(schedule);
        List<Employee> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }


    /**
     * 查出所有体验课
     * @param request
     * @param response
     * @param schedule
     * @param sEcho
     * @param start   分页 从第几条数据开始
     * @param length  分页 每页的数量
     * @return
     */
    @RequestMapping("/queryByPaging3")
    @ResponseBody
    public Map<String, Object> queryByPaging3(HttpServletRequest request, HttpServletResponse response, Schedule schedule, Integer sEcho, Integer start, Integer length) {
        schedule.setPageNo((start/length)+1);
        schedule.setPageSize(length);
        Date startTime = DateTimeUtils.stringToDate(schedule.getStartTimes());
        schedule.setFromDate(startTime);
        Date endTime = DateTimeUtils.stringToDate(schedule.getEndTimes());
        schedule.setToDate(endTime);

        schedule.setPkProduct("201830497630307774"); //体验课
        Response<List<Schedule>> listResponse = scheduleClient.findguanqiao(schedule);
        List<Schedule> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }


    @RequestMapping("/queryByPaging4")
    @ResponseBody
    public Map<String, Object> queryByPaging4(HttpServletRequest request, HttpServletResponse response, Schedule schedule, Integer sEcho, Integer start, Integer length) throws ParseException {
        schedule.setPageNo((start/length)+1);
        schedule.setPageSize(length);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Date startTime = sdf.parse(schedule.getStartTimes());
        schedule.setStartTime(startTime);
        Date endTime = sdf.parse(schedule.getEndTimes());
        schedule.setEndTime(endTime);
        Response<List<Schedule>> listResponse = scheduleClient.findguanqiao(schedule);
        List<Schedule> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }


}
