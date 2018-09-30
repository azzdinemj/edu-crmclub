package com.wuxue.api.task;

import com.wuxue.api.mapper.*;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Auther: wh
 * @Date: 2018/7/16 16:39
 * @Description: 定时任务,定时将已过期的预约课程，修改为 系统自动确认已完成（2）,并生成 缺席学习记录
 */
@Component
public class TaskProductOrder {

    @Autowired
    TkLearnRecordsMapper tkLearnRecordsMapper;
    @Autowired
    ScheduleMapper scheduleMapper;
    @Autowired
    TkProductOrderMapper tkProductOrderMapper;
    @Autowired
	ParentOrderMapper parentOrderMapper;
    @Autowired
	NoticeMapper noticeMapper;
    @Autowired
	StudentLinkmanMapper studentLinkmanMapper;
    @Autowired
	LinkmanMapper linkmanMapper;
    @Autowired
	StudentMapper studentMapper;

    //    每分钟启动
    @Scheduled(cron = "0 0/1 * * * ?")
    //@Scheduled(cron = "0/2 * * * * *")
    public void timerToNow(){
        System.out.println("now time:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        TkProductOrder tkProductOrder = new TkProductOrder(); //查找出全部未上课的预约记录
		tkProductOrder.setStatus(0);

		List<TkProductOrder> response = tkProductOrderMapper.select(tkProductOrder);
		for (TkProductOrder t : response) {
             /*遍历查询相关联的课程开始时间
             *若结束时间小于当前时间，则 系统修改状态为已上
             */
			Schedule scheduleResponse = scheduleMapper.selectByPrimaryKey(t.getPkSchedule());
			//判断这个预约的课程是否已经过期
			if (scheduleResponse.getEndTime().getTime() < new Date().getTime()) {

				Schedule sch = new Schedule(); //排课  修改为已完成
				sch.setPkSchedule(scheduleResponse.getPkSchedule());
				sch.setStatus(1);
                scheduleMapper.updateByPrimaryKeySelective(sch);

				//修改 已预约为  系统自动确定完成(2)
				tkProductOrder.setPkProductOrder(t.getPkProductOrder());
				tkProductOrder.setStatus(2);
                int i=tkProductOrderMapper.updateByPrimaryKeySelective(tkProductOrder);
				if (i>0) {
					//创建学习记录  （缺席）
					TkLearnRecords tkLearnRecords = new TkLearnRecords();
					tkLearnRecords.setPkLearnRecords(GuidUtils.getGuid());
					tkLearnRecords.setPkStudent(t.getPkStudent());
					tkLearnRecords.setPkSchedule(t.getPkSchedule());
					tkLearnRecords.setCreationDate(new Date());
					tkLearnRecords.setLasteditDate(new Date());
					tkLearnRecords.setProductType(1); //缺席
					tkLearnRecordsMapper.insertSelective(tkLearnRecords);
				}
			}
		}

    }


	//    每分钟启动
	@Scheduled(cron = "0 0 22 * * ?")
	public void addNotice(){
		System.out.println("点餐通知start now time:---------------" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-----------------");

//		获取当天周几
		Date today = new Date();
		Calendar c=Calendar.getInstance();
		c.setTime(today);
//		0周末 1周一~6周六
		int weekday=c.get(Calendar.DAY_OF_WEEK)-1;

		Notice notice;
		StudentLinkmanKey studentLinkmanKey2;
		ParentOrder parentOrder;
//		周六查询是否已点餐
//		if(weekday == 5){
//			notice = new Notice();
//			notice.setPkNotice(GuidUtils.getGuid());
//			notice.setTitle("点餐内部通知");
//			notice.setContent("要点餐了");
//			notice.setCreator("admin");
//			notice.setModifier("admin");
//			notice.setCreationDate(new Date());
//			notice.setLasteditDate(new Date());
//			notice.setRanges("ALL");
//			notice.setIsdel(1);
//			notice.setType(2);
//			notice.setPkUser("admin");
//			noticeMapper.insertSelective(notice);
//		}
//		else if(weekday != 5 && weekday != 6){
//			List<StudentLinkmanKey> studentLinkmanKeyList = studentLinkmanMapper.select(new StudentLinkmanKey());
//
//			c.add(c.DATE,1);
//			today=c.getTime();
//			if (studentLinkmanKeyList.size() > 0) {
//				for (StudentLinkmanKey studentLinkmanKey : studentLinkmanKeyList) {
//					parentOrder = new ParentOrder();
//					notice = new Notice();
//					parentOrder.setMealDate(today);
//					parentOrder.setPkStudent(studentLinkmanKey.getPkStudent());
//					Student student = studentMapper.selectByPrimaryKey(studentLinkmanKey.getPkStudent());
//					List<ParentOrder> parentOrderList = parentOrderMapper.select(parentOrder);
//					if (parentOrderList.size() > 0) {notice.setTitle("点餐通知");
//						notice.setContent("您孩子"+ student.getCaption() +"明天的套餐只点了"+parentOrderList.size()+"份，还有"+(3-parentOrderList.size())+"份未点，如果您有事耽误的话我们就给您孩子默认套餐。");
//					}else{
//						notice.setTitle("点餐通知");
//						notice.setContent("您孩子"+ student.getCaption() +"明天的套餐还未点！");
//					}
//					notice.setCreator("admin");
//					notice.setModifier("admin");
//					notice.setPkUser("admin");
//					notice.setCreationDate(new Date());
//					notice.setLasteditDate(new Date());
//					notice.setIsdel(1);
//
////					studentLinkmanKey2 = new StudentLinkmanKey();
////					studentLinkmanKey2.setPkStudent(studentLinkmanKey.getPkStudent());
////					List<StudentLinkmanKey> linkmanKeyList = studentLinkmanMapper.select(studentLinkmanKey2);
////					if (linkmanKeyList.size() > 0) {
////						for (StudentLinkmanKey linkmanKey : linkmanKeyList) {
//							Linkman linkman = linkmanMapper.selectByPrimaryKey(studentLinkmanKey.getPkLinkman());
//							if(linkman != null && linkman.getMaster() != null && linkman.getMaster() == 1){
//								notice.setRanges(studentLinkmanKey.getPkLinkman());
//								notice.setPkNotice(GuidUtils.getGuid());
//								noticeMapper.insertSelective(notice);
//								break;
//							}
//
//							notice.setRanges(studentLinkmanKey.getPkLinkman());
//							notice.setPkNotice(GuidUtils.getGuid());
//							noticeMapper.insertSelective(notice);
////						}
////					}
//				}
//			}
//
////			List<ParentOrder> parentOrderList = parentOrderMapper.selectNotOrderList(parentOrder);
//		}

		System.out.println("点餐通知end  now time:---------------" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-----------------");
	}
}
