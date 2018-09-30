package com.wuxue.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wuxue.base.BaiscPage;

import java.util.Date;
import java.util.Map;

/**
 *
 * description:产品（课程）预约表
 * @auther: wh
 * @date: 2018/6/13 14:03
 */
public class TkProductOrder  extends BaiscPage implements Comparable<TkProductOrder>{
    private String pkProductOrder;

    private String pkStudent;

    private String pkSchedule;

    private Integer status;

    private String creator;

    private Date creationDate;

    private String modifier;

    private Date lasteditDate;

    private Integer type; //0一对一 1 一对多


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPkProductOrder() {
        return pkProductOrder;
    }

    public void setPkProductOrder(String pkProductOrder) {
        this.pkProductOrder = pkProductOrder;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getPkSchedule() {
        return pkSchedule;
    }

    public void setPkSchedule(String pkSchedule) {
        this.pkSchedule = pkSchedule;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getLasteditDate() {
        return lasteditDate;
    }

    public void setLasteditDate(Date lasteditDate) {
        this.lasteditDate = lasteditDate;
    }

    //按照对象中的时间排序
    @Override
    public int compareTo(TkProductOrder o) {

        Map<String,Object> map=o.getMap();
        Schedule schedule = JSON.parseObject(map.get("schedule").toString(),new TypeReference<Schedule>(){});
        Map<String,Object> map2=this.getMap();
        Schedule schedule2=JSON.parseObject(map2.get("schedule").toString(),new TypeReference<Schedule>(){});

        if(schedule.getStartTime().getTime()<schedule2.getStartTime().getTime()){
          return 0;
        }else if(schedule.getStartTime().getTime()==schedule2.getStartTime().getTime()){
          return 1;
        }else {
         return -1;
        }

    }

}