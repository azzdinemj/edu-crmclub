package com.wuxue.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by tly on 2018/1/21.
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResultEntity implements Serializable {

    private static final long serialVersionUID = 6115862752925769344L;
    private String id;
    private Double count;
    private String setMealName;//套餐名
    private BigDecimal oldSumCost;

    private BigDecimal newSumCost;

    //支付记录
    private String code;
    private String pkClassInfo;
    private String className;
    private String studentId;//学生id
    private String studentName;
    private Date lastTime; //最新时间
    private Date payDate;
    private Double payCost;//现金支付金额
    private String payMethod; //支付方式
    private Double setMealPrice;//套餐价格
    private Double balancePayCost;//余额支付金额s
    private String teacherId;//老师id
    private String img;//图片

    private Date startTime;//开始时间
    private Date endTime;//结束时间
    private Integer type;//教师类型
    private Integer leaveMsgCount;//留言数
    private Integer replyCount;//回复数
    private Integer status; // 0 异常状态(請假) 1 正常上下车

    private String raceId;//竞赛id
    private String raceName;//竞赛名称
    private String awardsName; //奖项名称

    private Integer pageNo;
    private Integer pageSize;

    private List<String> ids;

    private Integer onOffBus; //0上车 1下车
    private String schoolbusId;//校车id
    private Byte direction;//1=校外开往校内，2=校内开往校外

}
