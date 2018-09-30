package com.wuxue.api.service.impl;

import com.wuxue.api.service.CardsService;
import com.wuxue.api.utils.GsonUtil;
import com.wuxue.api.utils.HttpUtil;
import com.wuxue.api.utils.TransApi;
import com.wuxue.base.Page;
import com.wuxue.model.card.*;
import com.wuxue.utils.contract.Response;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service("cardsService")
public class CardsServiceImpl implements CardsService {

    @Override
    public Response recharge(Trans trans) throws Exception {
        Response response = Response.newResponse();
        // 模拟数据
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        trans.setMoney(BigDecimal.valueOf(1));
//        trans.setMoney(BigDecimal.valueOf(400.00));
        trans.setTransType(2);
        trans.setW_ID(1);
        trans.setPos_ID(1);
        trans.setTransTag(0);
//        trans.setTransNo(1);

//        获取账号信息
        Student student = new Student();
        student.setAccountNo(trans.getAccountNo());
        Response accounts = getAccounts(student);
        student = (Student) accounts.getData();
        trans.setA_Accounts(student.getAccountId());
//        trans.setTransDate(sdf.format(new Date()));
        // 将对象转换成json字符串
        Map<String, Object> stringObjectMap = GsonUtil.objectToMap(trans);
//        String jsonParam = GsonUtil.GsonString(trans);
        // 调用接口新增交易
        String jsonObject = "";
        try {
            jsonObject = HttpUtil.sendPost2(TransApi.trans_url, stringObjectMap, "UTF-8");
        }catch (Exception ex){
            jsonObject = ex.getMessage();
        }

        if (!jsonObject.equals("")){
            return response.SERVER_ERROR("服務錯誤,請聯繫管理員。");
        }

        return response;
    }

    @Override
    public void getTransList(Trans trans){
        try {
            // 模拟数据
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            trans.setBeginDate(sdf.format(sdf.parse("2018-01-15 00:00:00")));
            trans.setEndDate(sdf.format(sdf.parse("2018-07-15 00:00:00")));
            trans.setPageSize(100);
            trans.setPage(1);
            // 将对象转换成json字符串
//        Map<String, Object> stringObjectMap = new HashMap<>();
            Map<String, Object> stringObjectMap = GsonUtil.objectToMap(trans);
//        String decode = URLDecoder.decode(urlParam, "utf-8");
            String jsonParam = "?beginDate=" + trans.getBeginDate() + "&endDate=" + trans.getEndDate() + "&pageSize=100&page=1";
            // 调用接口新增交易
            String result = HttpUtil.httpClientGet(TransApi.trans_list_url, stringObjectMap, "UTF-8");
            if (!result.equals("")) {
                JSONObject jsonObject = JSONObject.fromObject(result);
                JSONObject object = jsonObject.getJSONObject("page");
                Page page = (Page) JSONObject.toBean(object, Page.class);

                List<Consumption> consumptionList = insertConsumption(page, trans);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public List<Consumption> insertConsumption(Page page, Trans trans){
        List<Consumption> consumptionList = new ArrayList<>();
        try {
            if (page.getTotalCount() != 0) {
                for (int i = 1; i <= page.getTotalPage(); i++) {
                    trans.setPage(i);
                    // 将对象转换成json字符串
                    Map<String, Object> stringObjectMap = GsonUtil.objectToMap(trans);
                    // 调用接口新增交易
                    String result = HttpUtil.httpClientGet(TransApi.trans_url, stringObjectMap, "UTF-8");
                    if (!result.equals("")) {
                        JSONObject jsonObject = JSONObject.fromObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        if (jsonArray != null && jsonArray.size() > 0) {
                            for (int j = 0; j < jsonArray.size(); j++) {
                                JSONObject jo = JSONObject.fromObject(jsonArray.get(j));
                                Consumption consumption = (Consumption) JSONObject.toBean(jo, Consumption.class);
                                consumptionList.add(consumption);
                            }
                        }
                    }
                }
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return consumptionList;
    }

    @Override
    public void getDoorsList(Doors doors){
        try {
            // 模拟数据
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            doors.setBeginDate(sdf.format(sdf.parse("2018-01-15 00:00:00")));
            doors.setEndDate(sdf.format(sdf.parse("2018-07-30 00:00:00")));
            doors.setPageSize(100);
            doors.setPage(1);
            // 将对象转换成json字符串
            Map<String, Object> stringObjectMap = GsonUtil.objectToMap(doors);
            // 调用接口新增交易
            String result = HttpUtil.httpClientGet(TransApi.doors_list_url, stringObjectMap, "UTF-8");
            if (!result.equals("")) {
                JSONObject jsonObject = JSONObject.fromObject(result);
                JSONObject object = jsonObject.getJSONObject("page");
                Page page = (Page) JSONObject.toBean(object, Page.class);

                List<Doors> doorsList = insertDoors(page, doors);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public List<Doors> insertDoors(Page page, Doors doors){
        List<Doors> doorsList = new ArrayList<>();
        try {
            if (page.getTotalCount() != 0) {
                for (int i = 1; i <= page.getTotalPage(); i++) {
                    doors.setPage(i);
                    // 将对象转换成json字符串
                    Map<String, Object> stringObjectMap = GsonUtil.objectToMap(doors);
                    // 调用接口
                    String result = HttpUtil.httpClientGet(TransApi.doors_list_url, stringObjectMap, "UTF-8");
                    if (!result.equals("")) {
                        JSONObject jsonObject = JSONObject.fromObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        if (jsonArray != null && jsonArray.size() > 0) {
                            for (int j = 0; j < jsonArray.size(); j++) {
                                JSONObject jo = JSONObject.fromObject(jsonArray.get(j));
                                Doors doors1 = (Doors) JSONObject.toBean(jo, Doors.class);
                                doorsList.add(doors1);
                            }
                        }
                    }
                }
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return doorsList;
    }

    @Override
    public void getAttendanceList(Attendance attendance){
        try {
            // 模拟数据
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            attendance.setBeginDate(sdf.format(sdf.parse("2018-01-15 00:00:00")));
            attendance.setEndDate(sdf.format(sdf.parse("2018-03-30 00:00:00")));
            attendance.setPageSize(100);
            attendance.setPage(1);
            // 将对象转换成json字符串
            Map<String, Object> stringObjectMap = GsonUtil.objectToMap(attendance);
            // 调用接口新增交易
            String result = HttpUtil.httpClientGet(TransApi.attendance_list_url, stringObjectMap, "UTF-8");
            if (!result.equals("")) {
                JSONObject jsonObject = JSONObject.fromObject(result);
                JSONObject object = jsonObject.getJSONObject("page");
                Page page = (Page) JSONObject.toBean(object, Page.class);
                List<Attendance> attendanceList = insertAttendance(page, attendance);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public List<Attendance> insertAttendance(Page page, Attendance attendance){
        List<Attendance> attendanceList = new ArrayList<>();
        try {
            if (page.getTotalCount() != 0) {
                for (int i = 1; i <= page.getTotalPage(); i++) {
                    attendance.setPage(i);
                    // 将对象转换成json字符串
                    Map<String, Object> stringObjectMap = GsonUtil.objectToMap(attendance);
                    // 调用接口
                    String result = HttpUtil.httpClientGet(TransApi.attendance_list_url, stringObjectMap, "UTF-8");
                    if (!result.equals("")) {
                        JSONObject jsonObject = JSONObject.fromObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");
                        if (jsonArray != null && jsonArray.size() > 0) {
                            for (int j = 0; j < jsonArray.size(); j++) {
                                JSONObject jo = JSONObject.fromObject(jsonArray.get(j));
                                Attendance attendance1 = (Attendance) JSONObject.toBean(jo, Attendance.class);
                                attendanceList.add(attendance1);
                            }
                        }
                    }
                }
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return attendanceList;
    }

//    public Accounts getAccounts(Accounts accounts) throws Exception {
//        // 将对象转换成json字符串
//        Map<String, Object> stringObjectMap = new HashMap<>();
//        // 调用接口新增交易
//        String result = HttpUtil.httpClientGet(TransApi.accounts_url+"37", stringObjectMap, "UTF-8");
//        JSONObject jsonObject=JSONObject.fromObject(result);
//        Accounts account=(Accounts)JSONObject.toBean(jsonObject, Accounts.class);
//        return account;
//    }

    @Override
    public Response getAccounts(Student student){
        Response response = Response.newResponse();
        String jsonObjects = "";
        try {
            // 将对象转换成json字符串
            Map<String, Object> stringObjectMap = new HashMap<>();
            // 调用接口新增交易
            String result = HttpUtil.httpClientGet(TransApi.student_url + student.getAccountNo(), stringObjectMap, "UTF-8");
            if (!result.equals("")) {
                JSONObject jsonObject = JSONObject.fromObject(result);
//                Student student1 = (Student) JSONObject.toBean(jsonObject, Student.class);
                response.setData(jsonObject);
//                return student1;
            }
        }catch (Exception ex){
            jsonObjects = ex.getMessage();
        }

        if (!jsonObjects.equals("")){
            System.out.println(jsonObjects);
            return response.SERVER_ERROR("服務錯誤,請聯繫管理員。");
        }
        return response;
    }

    @Override
    public Response addAccounts(Student student){
        Response response = Response.newResponse();
        String jsonObject = "";

        // 模拟数据
//        student.setAccountNo("2012000317");
//        student.setDepartmentId(3);
//        student.setName("陈家辉");

        // 将对象转换成json字符串
        try {
            Map<String, Object> stringObjectMap = GsonUtil.objectToMap(student);
//        String jsonParam = GsonUtil.GsonString(trans);
            // 调用接口新增用户
            jsonObject = HttpUtil.httpClientPOST(TransApi.add_account_url, stringObjectMap, "UTF-8");
        }catch (Exception ex){
            jsonObject = ex.getMessage();
        }

        if (!jsonObject.equals("")){
            System.out.println(jsonObject);
            return response.SERVER_ERROR("服務錯誤,請聯繫管理員。");
        }

        return response;
    }
}
