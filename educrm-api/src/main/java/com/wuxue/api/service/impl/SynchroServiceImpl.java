package com.wuxue.api.service.impl;

import com.wuxue.api.service.SynchroService;
import com.wuxue.utils.contract.Response;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Jamie on 2018/1/21.
 */
@Service("synchroService")
public class SynchroServiceImpl implements SynchroService {


    public static Response DatabaseSynchronization(String name){
        Response response = Response.newResponse();
        String msg = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            msg = e.getMessage();
            return response.SAVE_FAIL(msg);
        }

        //一开始必须填一个已经存在的数据库
        System.out.println("-------------------创建新库start------------------------");
        String url = "jdbc:mysql://119.27.172.73:3306/wx_pro_erp?useSSL=false&useUnicode=true&characterEncoding=utf8";
        Connection conn = null;
        Statement stat = null;
        try {
            conn = DriverManager.getConnection(url, "root", "shmy6451");
            stat = conn.createStatement();


            //创建数据库wx-pro-erp-sh-test
            stat.executeUpdate("create database "+name);

            stat.close();
            conn.close();
        } catch (SQLException e) {
            msg = e.getMessage();
            return response.SAVE_FAIL(msg);
        }
        System.out.println("-------------------创建新库end------------------------");

        System.out.println("-------------------数据库同步start------------------------");
        Date d = new Date();
        long start = d.getTime();
        System.out.println("-------------------获得生产库------------------------");
        try {
            conn = getConnection();
        } catch (Exception e1) {
            msg = "获取生产库链接失败";
            return response.SAVE_FAIL(msg);
        }
        System.out.println("-------------------获得客户库------------------------");
        try {
            stat = getTestConnection(name);
        } catch (Exception e1) {
            msg = "获取客户库链接失败";
            return response.SAVE_FAIL(msg);
        }
        if("".equals(msg)){
            try {
                //开启事务
                System.out.println("-------------------开启事务------------------------");
                conn.setAutoCommit(false);

                //查询出生产库所有表的名称
                System.out.println("-------------------查询生产库所有表的名称------------------------");
                List<String> list_table_names = new ArrayList<String>();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'wx_pro_erp' ORDER BY table_name DESC");
                while(rs.next()){
                    list_table_names.add(rs.getString(1));
                }
                rs.close();
                stmt.close();

                //查询客户库所有表的名称
                List<String> list_table_names_test = new ArrayList<String>();
                stmt = stat;
//                rs = stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = "+ name +" ORDER BY table_name DESC");
//                while(rs.next()){
//                    list_table_names_test.add(rs.getString(1));
//                }

                //拼接往客户库每个表插数据的sql
                System.out.println("-------------------拼接插入sql------------------------");
                String table_name = "";
                List<String> sql_create = new ArrayList<String>();
                for(int i =0;i<list_table_names.size();i++){
                    table_name = list_table_names.get(i);
                    sql_create.add("create table "+name+"."+table_name+" AS SELECT * FROM wx_pro_erp."+table_name+"");
                }

                //拼接删除客户库表的sql
                List<String> sql_drop = new ArrayList<String>();
                for(int i =0;i<list_table_names_test.size();i++){
                    table_name = list_table_names_test.get(i);
                    sql_drop.add("drop table "+name+"." + table_name);
                }

                //删除客户库的数据
                stmt = conn.createStatement();
                for(int i = 0 ;i<sql_drop.size();i++){
                    stmt.addBatch(sql_drop.get(i));
                }

                //执行删除sql
                int[] result = stmt.executeBatch();
                System.out.println("执行删除表：" + result.length);
                stmt.close();

                //重新创建客户库的数据
                stmt = stat;
                for(int i = 0 ;i<sql_create.size();i++){
                    stmt.addBatch(sql_create.get(i));
                }

                //执行创建sql
                System.out.println("-------------------执行插入sql start------------------------");
                result = stmt.executeBatch();
                System.out.println("执行创建表：" + result.length);
                stmt.close();
                System.out.println("-------------------执行插入sql end------------------------");


                //提交事务
                System.out.println("-------------------提交事务------------------------");
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                msg = e.getMessage();
                return response.SAVE_FAIL(msg);
            }finally{
                try {
                    conn.close();
                    stat.close();
                } catch (SQLException e) {
                    msg = e.getMessage();
                    return response.SAVE_FAIL(msg);
                }
            }
        }else{
            return response.SAVE_FAIL(msg);
        }

        d = new Date();
        long end = d.getTime();

        System.out.println("执行时间：" + (end-start) + "毫秒");
        System.out.println("-------------------数据库同步end------------------------");
        return Response.newResponse();

    }


    //获取生产库链接
    private static Connection getConnection() throws Exception{
        Connection ct = null;
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://119.27.172.73:3306/wx_pro_erp?useSSL=false&useUnicode=true&characterEncoding=utf8";
        ct = DriverManager.getConnection(url, "root", "shmy6451");
        return ct;
    }

    //获取客户库链接
    private static Statement getTestConnection(String name) throws Exception{
        Statement stat = null;
        Connection ct = null;
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://119.27.172.73:3306/"+ name +"?useSSL=false&useUnicode=true&characterEncoding=utf8";
        ct = DriverManager.getConnection(url, "root", "shmy6451");
        stat = ct.createStatement();
        return stat;
    }


//    public static void main(String[] args){
//        String name = "wx_pro_erp_test";
//        Response response = DatabaseSynchronization(name);
//        System.out.println(response.toString());
//    }

}
