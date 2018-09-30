package com.wuxue.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.wuxue.api.mapper.RechargeMapper;
import com.wuxue.api.service.CardService;
import com.wuxue.api.utils.GsonUtil;
import com.wuxue.api.utils.TransApi;
import com.wuxue.model.Recharge;
import com.wuxue.model.card.Student;
import com.wuxue.model.card.Trans;
import com.wuxue.utils.common.HttpUtil;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("cardService")
public class CardServiceImpl implements CardService {
    @Autowired
    private RechargeMapper rechargeMapper;

    @Override
    public Response selectByRechargeList(Recharge request) {
        Response response = Response.newResponse();

        List<Recharge> select = rechargeMapper.select(request);

        response.setData(select);
        return response;
    }

    @Override
    public Response save(Request<Recharge> tParams) {
        Response response = Response.newResponse();
        Recharge recharge = tParams.getData();
        if (recharge == null) {
            return response.PARAMS_ISNULL();
        }
        Integer primaryKey = recharge.getPkRecharge();
        Recharge select = null;
        if (primaryKey != null) {
            select = rechargeMapper.selectByPrimaryKey(primaryKey);
        }

        try {
            if (select != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                rechargeMapper.updateByPrimaryKeySelective(recharge);

                Trans trans = new Trans();
//                trans.setAccountNo(select.getStuNo());
                trans.setMoney(select.getMoney());
                trans.setTransDate(sdf.format(new Date()));
                trans.setTransNo(select.getPkRecharge());

                trans.setTransType(2);
                trans.setW_ID(1);
                trans.setPos_ID(1);
                trans.setTransTag(0);
                //        获取账号信息
                Student student = new Student();
                student.setAccountNo(select.getStuNo());
                Response accounts = getAccounts(student);
                student = (Student) accounts.getData();
                trans.setA_Accounts(student.getAccountId());
//                trans.setA_Accounts(500);

                // 将对象转换成json字符串
                Map<String, Object> stringObjectMap = GsonUtil.objectToMap(trans);
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
                select.setStatus(2);
                rechargeMapper.updateByPrimaryKeySelective(select);
            } else {
                recharge.setType(2);
                recharge.setTag(0);
                recharge.setCreationDate(new Date());
                rechargeMapper.insertSelective(recharge);
            }

        } catch (Exception ex) {
            return response.SAVE_FAIL(ex.getMessage());
        }

        response.setData(recharge);
        return response;
    }

    public Response getAccounts(Student student){
        Response response = Response.newResponse();
        String jsonObjects = "";
        try {
            // 将对象转换成json字符串
            Map<String, Object> stringObjectMap = new HashMap<>();
            // 调用接口新增交易
            String result = com.wuxue.api.utils.HttpUtil.httpClientGet(TransApi.student_url + student.getAccountNo(), stringObjectMap, "UTF-8");
            if (!result.equals("")) {
                JSONObject jsonObject = JSONObject.fromObject(result);
                Student student1 = (Student) JSONObject.toBean(jsonObject, Student.class);
                response.setData(student1);
//                return student1;
            }
        }catch (Exception ex){
            jsonObjects = ex.getMessage();
        }

//        if (!jsonObjects.equals("")){
//            System.out.println(jsonObjects);
//            return response.SERVER_ERROR("服務錯誤,請聯繫管理員。");
//        }
        return response;
    }
}
