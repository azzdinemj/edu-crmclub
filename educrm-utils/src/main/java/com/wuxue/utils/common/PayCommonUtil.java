package com.wuxue.utils.common;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class PayCommonUtil {
	/**
	 * 是否签名正确,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。 
	 * @Author  Rogue
	 * @param characterEncoding
	 * @param packageParams
	 * @param API_KEY
	 * @return  boolean
	 * @Date	2018年6月26日
	 * 更新日志
	 * 2018年6月26日  Rogue 首次创建
	 *
	 */
	@SuppressWarnings({ "rawtypes"})
    public static boolean isTenpaySign(String characterEncoding, SortedMap<Object, Object> packageParams, String API_KEY) throws Exception {
        StringBuffer sb = new StringBuffer();  
        Set es = packageParams.entrySet();  
        Iterator it = es.iterator();  
        while(it.hasNext()) {  
            Map.Entry entry = (Map.Entry)it.next();  
            String k = (String)entry.getKey();  
            String v = (String)entry.getValue();  
            if(!"sign".equals(k) && null != v && !"".equals(v)) {  
                sb.append(k + "=" + v + "&");  
            }  
        }  
        sb.append("key=" + API_KEY);  
        //算出摘要  
        String mysign = MD5Util.MD5(sb.toString()).toLowerCase();
        String tenpaySign = ((String)packageParams.get("sign")).toLowerCase();  
        return tenpaySign.equals(mysign);  
    }  
    /**
     * sign签名
     * @Author  Rogue
     * @param characterEncoding
     * @param packageParams
     * @param API_KEY
     * @return  String
     * @Date	2018年6月26日
     * 更新日志
     * 2018年6月26日  Rogue 首次创建
     *
     */
    @SuppressWarnings({ "rawtypes"})
	public static String createSign(String characterEncoding, SortedMap<Object, Object> packageParams, String API_KEY) throws Exception {
        StringBuffer sb = new StringBuffer();  
        Set es = packageParams.entrySet();  
        Iterator it = es.iterator();  
        while (it.hasNext()) {  
            Map.Entry entry = (Map.Entry) it.next();  
            String k = (String) entry.getKey();  
            String v = (String) entry.getValue();  
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {  
                sb.append(k + "=" + v + "&");  
            }  
        }  
        sb.append("key=" + API_KEY);  
        String sign = MD5Util.MD5(sb.toString());
        return sign;  
    }  
  
   /**
    * 将请求参数转换为xml格式的string
    * @Author  Rogue
    * @param parameters
    * @return  String
    * @Date	2018年6月26日
    * 更新日志
    * 2018年6月26日  Rogue 首次创建
    *
    */
    @SuppressWarnings({ "rawtypes"})
    public static String getRequestXml(SortedMap<Object, Object> parameters) {  
        StringBuffer sb = new StringBuffer();  
        sb.append("<xml>");  
        Set es = parameters.entrySet();  
        Iterator it = es.iterator();  
        while (it.hasNext()) {  
            Map.Entry entry = (Map.Entry) it.next();  
            String k = (String) entry.getKey();  
            String v = (String) entry.getValue();  
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {  
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");  
            } else {  
                sb.append("<" + k + ">" + v + "</" + k + ">");  
            }  
        }  
        sb.append("</xml>");  
        return sb.toString();  
    }  
  
   /**
    * 取出一个指定长度大小的随机正整数. 
    * @Author  Rogue
    * @param length
    * @return  int
    * @Date	2018年6月26日
    * 更新日志
    * 2018年6月26日  Rogue 首次创建
    *
    */
    public static int buildRandom(int length) {  
        int num = 1;  
        double random = Math.random();  
        if (random < 0.1) {  
            random = random + 0.1;  
        }  
        for (int i = 0; i < length; i++) {  
            num = num * 10;  
        }  
        return (int) ((random * num));  
    }  
  
    /** 
     * 获取当前时间 yyyyMMddHHmmss 
     *  
     * @return String 
     */  
    public static String getCurrTime() {  
        Date now = new Date();  
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
        String s = outFormat.format(now);  
        return s;  
    }

    /**
     * 除法
     */
    public static BigDecimal divide(String arg1, String arg2) {
        if (StringUtils.isEmpty(arg1)) {
            arg1 = "0.0";
        }
        if (StringUtils.isEmpty(arg2)) {
            arg2 = "0.0";
        }
        BigDecimal big3 = new BigDecimal("0.00");
        if (Double.parseDouble(arg2) != 0) {
            BigDecimal big1 = new BigDecimal(arg1);
            BigDecimal big2 = new BigDecimal(arg2);
            big3 = big1.divide(big2, 2, BigDecimal.ROUND_HALF_EVEN);
        }
        return big3;
    }

    /**
     * 乘法
     */
    public static BigDecimal mul(String arg1, String arg2) {
        if (StringUtils.isEmpty(arg1)) {
            arg1 = "0.0";
        }
        if (StringUtils.isEmpty(arg2)) {
            arg2 = "0.0";
        }
        BigDecimal big1 = new BigDecimal(arg1);
        BigDecimal big2 = new BigDecimal(arg2);
        BigDecimal big3 = big1.multiply(big2);
        return big3;
    }

    /**
     * 减法
     */
    public static BigDecimal sub(String arg1, String arg2) {
        if (StringUtils.isEmpty(arg1)) {
            arg1 = "0.0";
        }
        if (StringUtils.isEmpty(arg2)) {
            arg2 = "0.0";
        }
        BigDecimal big1 = new BigDecimal(arg1);
        BigDecimal big2 = new BigDecimal(arg2);
        BigDecimal big3 = big1.subtract(big2);
        return big3;
    }

    /**
     * 加法
     */
    public static BigDecimal add(String arg1, String arg2) {
        if (StringUtils.isEmpty(arg1)) {
            arg1 = "0.0";
        }
        if (StringUtils.isEmpty(arg2)) {
            arg2 = "0.0";
        }
        BigDecimal big1 = new BigDecimal(arg1);
        BigDecimal big2 = new BigDecimal(arg2);
        BigDecimal big3 = big1.add(big2);
        return big3;
    }

    /**
     * 加法
     */
    public static String add2(String arg1, String arg2) {
        if (StringUtils.isEmpty(arg1)) {
            arg1 = "0.0";
        }
        if (StringUtils.isEmpty(arg2)) {
            arg2 = "0.0";
        }
        BigDecimal big1 = new BigDecimal(arg1);
        BigDecimal big2 = new BigDecimal(arg2);
        BigDecimal big3 = big1.add(big2);
        return big3.toString();
    }

    /**
     * 四舍五入保留N位小数 先四舍五入在使用double值自动去零
     *
     * @param arg
     * @param scare
     *            保留位数
     * @return
     */
    public static String setScare(BigDecimal arg, int scare) {
        BigDecimal bl = arg.setScale(scare, BigDecimal.ROUND_HALF_UP);
        return String.valueOf(bl.doubleValue());
    }

    public static double setDifScare(double arg) {
        BigDecimal bd = new BigDecimal(arg);
        BigDecimal bl = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return Double.parseDouble(bl.toString());
    }

    /**
     * 四舍五入保留两位小数 先四舍五入在使用double值自动去零
     *
     * @param arg
     * @return
     */
    public static String setDifScare(String arg) {
        BigDecimal bd = new BigDecimal(arg);
        BigDecimal bl = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bl.toString();
    }

    /**
     * 四舍五入保留N位小数 先四舍五入在使用double值自动去零（传参String类型）
     *
     * @param arg
     * @return
     */
    public static String setDifScare(String arg, int i) {
        BigDecimal bd = new BigDecimal(arg);
        BigDecimal bl = bd.setScale(i, BigDecimal.ROUND_HALF_UP);
        return bl.toString();
    }

    /**
     * 转化成百分数 先四舍五入在使用double值自动去零
     *
     * @param arg
     * @return
     */
    public static String setFenScare(BigDecimal arg) {
        BigDecimal bl = arg.setScale(3, BigDecimal.ROUND_HALF_UP);
        String scare = String.valueOf(mul(bl.toString(), "100").doubleValue());
        String fenScare = scare + "%";
        return fenScare;
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");// 去掉多余的0
            s = s.replaceAll("[.]$", "");// 如最后一位是.则去掉
        }
        return s;
    }
}
