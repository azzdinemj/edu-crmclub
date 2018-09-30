package com.wuxue.api.controller.smallroutine.utils;

import com.wuxue.api.controller.smallroutine.client.wxOpenid.DatavalidClient;
import com.wuxue.model.Datavalid;
import com.wuxue.utils.contract.Response;

/**
 * @Description: 通用 方法
 */
public class  WXUtilsMethod  {

    //判断用户是否登录
    public static boolean validUserbingDing(String string){
        DatavalidClient datavalidClient =new DatavalidClient();
        boolean flag=false;
        String []strs=string.split(",");
        if(strs.length<2){
            return flag;
        }
        Datavalid datavalid=new Datavalid();
        datavalid.setId(strs[0]);
        Response<Datavalid> response=datavalidClient.findByPrimaryKey(datavalid);
        if(response.getData()!=null){
            String  str=response.getData().getId()+","+response.getData().getPkempstu();
            if(str.equals(string)){
                flag=true;
            }
        }
        return  flag;
    }


    //获取偶数
    public static String  getEven(String s){
        String  str="";
        String  str2="";
        for(int i=0;i<s.length();i++){
            if(i%2==0){
                str+=s.substring(i,i+1);
            } else{
                str2+=s.substring(i,i+1);
            }
        }
        return str;
    }
}
