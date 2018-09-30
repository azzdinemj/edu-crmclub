package com.wuxue.api.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wuxue.api.mapper.*;
import com.wuxue.api.service.DeliveryApplicationService;
import com.wuxue.api.service.UtilsService;
import com.wuxue.api.utils.GuidUtils;
import com.wuxue.base.KeyValue;
import com.wuxue.base.Page;
import com.wuxue.model.Linkman;
import com.wuxue.model.Student;
import com.wuxue.model.shuttle.DeliveryApplication;
import com.wuxue.model.shuttle.DeliveryStudent;
import com.wuxue.model.shuttle.RelayControl;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("deliveryApplicationService")
public class DeliveryApplicationServiceImpl implements DeliveryApplicationService {


    @Autowired
    private DeliveryApplicationMapper deliveryApplicationMapper;
    @Autowired
    private LinkmanMapper linkmanMapper;
    @Autowired
    private DeliveryStudentMapper deliveryStudentMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private UtilsService utilsService;

    @Autowired
    private RelayControlMapper relayControlMapper;


    @Override
    public List<DeliveryApplication> findApply(DeliveryApplication deliveryApplication) {

        PageHelper.startPage(deliveryApplication.getPageNo(), deliveryApplication.getPageSize());
        List<DeliveryApplication> select = deliveryApplicationMapper.select(deliveryApplication);



        return select;
    }

    /**
     * 提交接送申请
     *
     * @param
     * @param studentList
     * @param date
     * @param del
     * @return
     */
    @Override
    public Response applyIssubmit(List<String> studentList, Date date, DeliveryApplication del) {

        Response response = Response.newResponse();

        if (del.getPkLinkman() == null || studentList.size() == 0 || date == null) {
            return response.PARAMS_ISNULL();
        }
        int iResult = 0;
        String message = "";

        try {

            if (del.getPhone() == null || "".equals(del.getPhone())) {
                return response.PARAMS_ISNULL();
            }
            Linkman linkman = new Linkman();
            linkman.setPhone(del.getPhone());
            if (del.getIdCard() != null && !"".equals(del.getIdCard())){
                linkman.setIdCard(del.getIdCard());
            }
            List<Linkman> select = linkmanMapper.selectByPhoneAndIdCard(linkman);
            if (select.size() > 0) {
                DeliveryApplication deliveryApplication = new DeliveryApplication();
                deliveryApplication.setPkDomain("1");
                deliveryApplication.setPkDelivery(GuidUtils.getGuid());
                deliveryApplication.setPkLinkman(del.getPkLinkman());
                deliveryApplication.setEntrustmentId(select.get(0).getPkLinkman());
                deliveryApplication.setDeliveryDate(date);
                deliveryApplication.setStatus(del.getStatus());
                deliveryApplication.setIsaudit(0);
                deliveryApplication.setCreationDate(new Date());
                deliveryApplication.setIsvalid(1);
                iResult = deliveryApplicationMapper.insertSelective(deliveryApplication);
                if (iResult > 0) {
                    for (String s : studentList) {
                        DeliveryStudent deliveryStudent = new DeliveryStudent();
                        deliveryStudent.setPkDelivery(deliveryApplication.getPkDelivery());
                        deliveryStudent.setPkStudent(s);
                        deliveryStudent.setPkDeliveryStudent(GuidUtils.getGuid());
                        iResult = deliveryStudentMapper.insertSelective(deliveryStudent);
                    }
                }
                if (iResult > 0) {
                    RelayControl relayControl = new RelayControl();
                    relayControl.setPkDomain("1");
                    relayControl.setPkRelayControl(GuidUtils.getGuid());
                    relayControl.setPersonId(select.get(0).getPkLinkman());
                    relayControl.setStatus(0);
                    relayControl.setType(1);
                    relayControlMapper.insertSelective(relayControl);
                }
            } else {

                response.setCode(1007);
                response.setMessage("未找到委托家长信息");
                return response;
            }

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if (iResult > 0) {
            return response;
        }
        return response;
    }


    /**
     * 提交接送申请(临时接送)
     *
     * @param linkman
     * @param studentList
     * @param date
     * @param del
     * @return
     */
    @Override
    public Response applyIssubmitTemp(Linkman linkman, List<Student> studentList, Date date, DeliveryApplication del) {

        Response response = Response.newResponse();

        if (linkman == null || studentList.size() == 0 || date == null) {
            return response.PARAMS_ISNULL();
        }
        int iResult = 0;
        String message = "";

        try {

//            if ((linkman.getPhone() == null || "".equals(linkman.getPhone())) && (linkman.getIdCard() == null || "".equals(linkman.getIdCard()))) {
//                return response.PARAMS_ISNULL();
//            }
            Linkman linkman1 = linkmanMapper.selectByPrimaryKey(linkman.getPkLinkman());
            if (linkman1 != null) {
                DeliveryApplication deliveryApplication = new DeliveryApplication();
                deliveryApplication.setPkDomain("1");
                deliveryApplication.setPkDelivery(GuidUtils.getGuid());
                deliveryApplication.setPkLinkman(linkman.getPkLinkman());
                deliveryApplication.setEntrustmentId(linkman.getPkLinkman());
                deliveryApplication.setDeliveryDate(date);
                deliveryApplication.setStatus(del.getStatus());
                deliveryApplication.setIsaudit(0);
                deliveryApplication.setCreationDate(new Date());
                deliveryApplication.setIsvalid(1);
                deliveryApplication.setNotes(del.getNotes());
                iResult = deliveryApplicationMapper.insertSelective(deliveryApplication);
                if (iResult > 0) {
                    for (Student s : studentList) {
                        DeliveryStudent deliveryStudent = new DeliveryStudent();
                        deliveryStudent.setPkDelivery(deliveryApplication.getPkDelivery());
                        deliveryStudent.setPkStudent(s.getPkStudent());
                        deliveryStudent.setPkDeliveryStudent(GuidUtils.getGuid());
                        iResult = deliveryStudentMapper.insertSelective(deliveryStudent);
                    }
                }
                if (iResult > 0) {
                    RelayControl relayControl = new RelayControl();
                    relayControl.setPkDomain("1");
                    relayControl.setDeliveryApplicationId(deliveryApplication.getPkDelivery());
                    relayControl.setPkRelayControl(GuidUtils.getGuid());
                    relayControl.setPersonId(linkman.getPkLinkman());
                    relayControl.setStatus(0);
                    relayControl.setType(1);
//                    relayControl
                }
            } else {

                response.setCode(1007);
                response.setMessage("未找到家长信息");
                return response;
            }

        } catch (Exception ex) {
            message = ex.getMessage();
            return response.SAVE_FAIL(message);
        }
        if (iResult > 0) {
            return response;
        }
        return response.SAVE_FAIL(message);
    }

    @Override
    public Response delete(String pkDelivery) {
        Response response = Response.newResponse();
        String message = "";
        if (pkDelivery == null || "".equals(pkDelivery)){
            return response.PARAMS_ISNULL();
        }
        try {

            DeliveryApplication deliveryApplication = deliveryApplicationMapper.selectByPrimaryKey(pkDelivery);
            if (deliveryApplication == null){
                return response.PARAMS_ISNULL();
            }
            deliveryApplication.setIsaudit(3);
            deliveryApplicationMapper.updateByPrimaryKeySelective(deliveryApplication);

        }catch (Exception ex){
            message = ex.getMessage();
            return response.DELETE_FAIL(message);
        }


        return response;
    }

    /**
     * 详情
     *
     * @param pkDelivery
     * @return
     */
    @Override
    public DeliveryApplication selectByKey(String pkDelivery) {

        DeliveryApplication deliveryApplication = deliveryApplicationMapper.selectByPrimaryKey(pkDelivery);

        if (deliveryApplication != null) {
            List<Map> studentList = new ArrayList<>();
            utilsService.setLinkManByKeyValue(deliveryApplication, deliveryApplication.getPkLinkman(), LinkEntity.LINKMAN_ENTITY);
            utilsService.setLinkManByKeyValue(deliveryApplication, deliveryApplication.getEntrustmentId(), LinkEntity.ENTRUSTMEN_ENTITY);
            List<DeliveryStudent> deliveryStudentList = deliveryStudentMapper.selectByPkDelivery(deliveryApplication.getPkDelivery());
            if (deliveryStudentList.size() > 0) {
                for (DeliveryStudent deliveryStudent : deliveryStudentList) {
                    Student student = studentMapper.selectByPrimaryKey(deliveryStudent.getPkStudent());
                    if (student != null) {
                        Map map = new HashMap();
                        map.put("caption",student.getCaption());
                        map.put("id",student.getPkStudent());
                        map.put("code",student.getCode());
                        map.put("img",student.getImg());
//                        KeyValue keyValue = new KeyValue();
//                        keyValue.setCaption(student.getCaption());
//                        keyValue.setId(student.getPkStudent());
//                        keyValue.setCode(student.getCode());
                        studentList.add(map);
                    }
                }

            }
            deliveryApplication.put(Light.STUDENT_LIST, studentList);
        }


        return deliveryApplication;
    }

    @Override
    public Response audit(DeliveryApplication deliveryApplication) {
        Response response = Response.newResponse();

        String message = "";

        if (deliveryApplication == null || deliveryApplication.getPkDelivery() == null || "".equals(deliveryApplication.getPkDelivery())) {
            return response.PARAMS_ISNULL();
        }
        String pkDelivery = deliveryApplication.getPkDelivery();
        DeliveryApplication select = deliveryApplicationMapper.selectByPrimaryKey(pkDelivery);

        if (select == null) {
            return response.AUDIT_FAIL("未找到该接送信息");
        }
        select.setIsaudit(deliveryApplication.getIsaudit());
        int iResult = deliveryApplicationMapper.updateByPrimaryKeySelective(select);
        if (iResult >0){
            RelayControl relayControl = new RelayControl();
            relayControl.setPkDomain("1");
            relayControl.setPkRelayControl(GuidUtils.getGuid());
            relayControl.setPersonId(select.getPkLinkman());
            relayControl.setStatus(0);
            relayControl.setType(1);
            relayControlMapper.insertSelective(relayControl);
        }

        if (iResult > 0) {
            return response;
        }
        return response.AUDIT_FAIL("");
    }
}
