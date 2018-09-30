package com.wuxue.api.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;

import java.text.SimpleDateFormat;

/**
 * Created by Jamie on 2018/1/1.
 */
@ControllerAdvice
public class GuidUtils {
    public static String getGuid() {  
          
//        Common.Guid+=1;
  
  
        long now = System.currentTimeMillis();    
        //获取4位年份数字
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy");
        //获取时间戳    
        String time=dateFormat.format(now);  
        String info=now+"";  
        //获取三位随机数    
        int ran=(int) ((Math.random()*9+1)*100);
        //要是一段时间内的数据连过大会有重复的情况，所以做以下修改  
//        int ran=0;
//        if(Common.Guid>999){
//            Common.Guid=100;
//        }
//        ran=Common.Guid;

        return time + info.substring(2, info.length()) + ran;
    }

    public static void main(String[] args) {
        System.out.println(getGuid());
    }
}
