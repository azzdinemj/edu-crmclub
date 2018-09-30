package com.wuxue.view.controllers.classinfo;

import com.wuxue.model.ClassRoom;
import com.wuxue.model.Classinfo;
import com.wuxue.model.ClassinfoRoom;
import com.wuxue.utils.contract.Light;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ClassInfoClient;
import com.wuxue.view.client.classInfo.ClassInfoEmployeeClient;
import com.wuxue.view.client.classInfo.ClassInfoRoomClient;
import com.wuxue.view.client.classInfo.ClassRoomClient;
import com.wuxue.view.client.system.EmployeeClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.GuidUtils;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 班级老师
 */

@Controller
@RequestMapping(value = "/classinfo/classinfoRoom")
public class ClassinfoRoomController extends BaseController
        implements IQueryController<ClassinfoRoom, String>, ISaveController<ClassinfoRoom, String>,IQueryByPagingController<ClassinfoRoom,Map<String,Object>>,
        ICreateController<ClassinfoRoom, String>, IEditController<ClassinfoRoom, String>, IDeleteController<ClassinfoRoom, String> {

    @Autowired
    private ClassInfoRoomClient classinfoRoomClient;
    @Autowired
    private EmployeeClient employeeClient;
    @Autowired
    private ClassInfoClient classInfoClient;
    @Autowired
    private ClassRoomClient classRoomClient;

    /**
     * 班级老师列表页面
     *
     * @param model
     * @return
     * @throws
     */
    @Override
    public String query(HttpServletRequest request, Model model, ClassinfoRoom student) {



        return "/classinfo/classinfoRoomList";
    }


    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, ClassinfoRoom courseTeacher, Integer sEcho, Integer start, Integer length) {
        courseTeacher.setPageNo((start/length)+1);
        courseTeacher.setPageSize(length);

        String dateTime = request.getParameter("dateTime");
        if(dateTime!=null && !"".equals(dateTime)){
            String substring = dateTime.substring(0, 10);
            String substring1 = dateTime.substring(12);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
//            SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date strTime=simpleDateFormat.parse(substring);
                Date endTime=simpleDateFormat.parse(substring1);
                courseTeacher.setFromDate(strTime);
                courseTeacher.setToDate(endTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        Response<List<ClassinfoRoom>> listResponse = classinfoRoomClient.find(courseTeacher);
        List<ClassinfoRoom> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }

    /**
     * 修改页面
     *
     * @param student
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, ClassinfoRoom student) {

        return "/classinfo/classInfo";
    }


    /**
     * 保存
     *
     * @param classinfoRoom
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, ClassinfoRoom classinfoRoom) {
        if (classinfoRoom.getPkClassinfoRoom() == null || "".equals(classinfoRoom.getPkClassinfoRoom())){
            classinfoRoom.setPkClassinfoRoom(GuidUtils.getGuid());
            classinfoRoom.setCreator(SessionCache.getUserCode());
            classinfoRoom.setPkDomain(SessionCache.getPkdomain());
        }
        classinfoRoom.setModifier(SessionCache.getUserCode());
        return classinfoRoomClient.save(classinfoRoom);

    }



    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, ClassinfoRoom student) {

        ClassRoom classRoom = new ClassRoom();
        classRoom.setPageSize(10000);

        Response<List<ClassRoom>> listResponse = classRoomClient.find(classRoom);
        model.addAttribute("classRoom",listResponse.getData());

        return "/classinfo/classinfoRoom";
    }

    /**
     * 删除
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, ClassinfoRoom classinfoRoom) {
        classinfoRoom.setModifier(SessionCache.getUserCode());
        return classinfoRoomClient.delete(classinfoRoom);
    }




}
