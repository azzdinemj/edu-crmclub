package com.wuxue.vo;

import com.wuxue.model.AskForLeave;
import com.wuxue.model.Homework;
import com.wuxue.model.Notice;
import com.wuxue.model.shuttle.DeliveryApplication;
import com.wuxue.model.shuttle.NoticeRecord;
import lombok.Data;

import java.util.List;

@Data
public class NoticeVO {
    private List<Notice> noticeList;

    private List<NoticeRecord> noticeRecordList;

    private List<Notice> noticeParentList;

    private List<Homework> homeworkList;

    private List<DeliveryApplication> entrustList;

    private List<DeliveryApplication> commissionedList;

    private List<AskForLeave> askForLeavesList;

    private List<DeliveryApplication> deliveryApplicationList;

    public List<Notice> getNoticeList() {
        return noticeList;
    }

    public void setNoticeList(List<Notice> noticeList) {
        this.noticeList = noticeList;
    }

    public List<NoticeRecord> getNoticeRecordList() {
        return noticeRecordList;
    }

    public void setNoticeRecordList(List<NoticeRecord> noticeRecordList) {
        this.noticeRecordList = noticeRecordList;
    }

    public List<Notice> getNoticeParentList() {
        return noticeParentList;
    }

    public void setNoticeParentList(List<Notice> noticeParentList) {
        this.noticeParentList = noticeParentList;
    }

    public List<Homework> getHomeworkList() {
        return homeworkList;
    }

    public void setHomeworkList(List<Homework> homeworkList) {
        this.homeworkList = homeworkList;
    }

    public List<AskForLeave> getAskForLeavesList() {
        return askForLeavesList;
    }

    public void setAskForLeavesList(List<AskForLeave> askForLeavesList) {
        this.askForLeavesList = askForLeavesList;
    }

    public List<DeliveryApplication> getEntrustList() {
        return entrustList;
    }

    public void setEntrustList(List<DeliveryApplication> entrustList) {
        this.entrustList = entrustList;
    }

    public List<DeliveryApplication> getCommissionedList() {
        return commissionedList;
    }

    public void setCommissionedList(List<DeliveryApplication> commissionedList) {
        this.commissionedList = commissionedList;
    }

    public List<DeliveryApplication> getDeliveryApplicationList() {
        return deliveryApplicationList;
    }

    public void setDeliveryApplicationList(List<DeliveryApplication> deliveryApplicationList) {
        this.deliveryApplicationList = deliveryApplicationList;
    }
}
