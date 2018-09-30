package com.wuxue.view.controllers.shuttle;

import com.alibaba.fastjson.JSON;
import com.wuxue.model.ClassRoom;
import com.wuxue.model.ClassTime;
import com.wuxue.model.LinkmanList;
import com.wuxue.model.Student;
import com.wuxue.model.shuttle.SchoolbusLine;
import com.wuxue.utils.common.DateUtils;
import com.wuxue.utils.contract.Request;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ClassInfoClient;
import com.wuxue.view.client.classInfo.ClassRoomClient;
import com.wuxue.view.client.classInfo.ClassTimeClient;
import com.wuxue.view.client.shuttle.SchoolbusLineClient;
import com.wuxue.view.client.system.EmployeeClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.GuidUtils;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 校车线路
 */

@Controller
@RequestMapping(value = "/shuttle/schoolbusLine")
public class SchoolbuslineController extends BaseController
        implements IQueryController<SchoolbusLine, String>, ISaveController<SchoolbusLine, String>,IQueryByPagingController<SchoolbusLine,Response>,
        ICreateController<SchoolbusLine, String>, IEditController<SchoolbusLine, String>,IDeleteController<SchoolbusLine,String>{

    @Autowired
    private SchoolbusLineClient schoolbusLineClient;
    @Autowired
    private EmployeeClient employeeClient;
    @Autowired
    private ClassInfoClient classInfoClient;
    @Autowired
    private ClassRoomClient classRoomClient;
    @Autowired
    private ClassTimeClient classTimeClient;
    /**
     * 班级老师列表页面
     *
     * @param model
     * @return
     * @throws
     */
    @Override
    public String query(HttpServletRequest request, Model model, SchoolbusLine student) {
        return "/system/schoolBus";
    }


    @ResponseBody
    public Response queryByPaging(HttpServletRequest request, HttpServletResponse response, SchoolbusLine schoolbusLine, Integer sEcho, Integer start, Integer length) {

        schoolbusLine.setDirection(new Byte("2"));
        Response<List<SchoolbusLine>> listResponse = schoolbusLineClient.findByBusId(schoolbusLine);
        List<SchoolbusLine> lines = listResponse.getData();
        if(lines==null) {
            lines = new ArrayList<>();
        }
        schoolbusLine.setDirection(new Byte("1"));
        Response<List<SchoolbusLine>> listResponse1 = schoolbusLineClient.findByBusId(schoolbusLine);
        List<SchoolbusLine> reverseLines = listResponse1.getData();
        if (reverseLines == null){
            reverseLines = new ArrayList<>();
        }
        Response response1 = Response.newResponse();
        List list = new ArrayList();
        list.add(lines);
        list.add(reverseLines);
//        map.put("lines",lines);
//        map.put("reverseLines",reverseLines);
//        Response response1 = Response.newResponse();
        response1.setData(list);
        return response1;
    }

    public Response que(){
        return null;
    }

    /**
     * 修改页面
     *
     * @param schoolbusLine
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, SchoolbusLine schoolbusLine) {

        Response<SchoolbusLine> byPrimaryKey = schoolbusLineClient.findByPrimaryKey(schoolbusLine);
        model.addAttribute("schoolbusLine",byPrimaryKey.getData());

        return "/classinfo/classInfo";
    }


    /**
     * 保存
     *
     * @param schoolbusLine
     * @param request
     * @return
     */
    @ResponseBody
    public String save(HttpServletRequest request, Model model, SchoolbusLine schoolbusLine) {

        if (schoolbusLine.getDirection() == null ){
            schoolbusLine.setDirection(new Byte("2"));
        }

        String type = request.getParameter("type");
        if (type != null && type.equals("1")){  //起始站
            schoolbusLine.setStationid(new Byte("0"));
            schoolbusLine.setStationtype(new Byte("0"));

        }
        else if(type != null && type.equals("3")){  //终点站
            schoolbusLine.setStationid(new Byte("-1"));
            schoolbusLine.setStationtype(new Byte("0"));
        }else {
            schoolbusLine.setStationtype(new Byte("1"));  //中间站
        }
        return schoolbusLineClient.save(schoolbusLine);

    }

    @ResponseBody
    @RequestMapping(value = "/saveAll",method = RequestMethod.POST)
    public String saveAll(HttpServletRequest request, Model model, SchoolbusLine schoolbusLine,LinkmanList lines) {


        List<SchoolbusLine> schoolbusLineList = lines.getLines();

        if (schoolbusLineList != null &&schoolbusLineList.size()>0){

            for (int i = 0; i< schoolbusLineList.size(); i++){
                schoolbusLineList.get(i).setSchoolbusId(schoolbusLine.getSchoolbusId());
                schoolbusLineList.get(i).setDirection(new Byte("2"));
                if (i == 0 || i==(schoolbusLineList.size()-1)){
                    schoolbusLineList.get(i).setStationtype(new Byte("0"));
                }else {
                    schoolbusLineList.get(i).setStationtype(new Byte("1"));
                }
                if (i == (schoolbusLineList.size()-1)){
                    schoolbusLineList.get(i).setStationid(new Byte("-1"));
                }else {
                    schoolbusLineList.get(i).setStationid(new Byte(""+i));

                }

            }
        }

        Student student = new Student();

        return schoolbusLineClient.saveAll(schoolbusLineList);

    }



    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, SchoolbusLine student) {

        ClassRoom classRoom = new ClassRoom();

        Response<List<ClassRoom>> listResponse = classRoomClient.find(classRoom);
        model.addAttribute("classRoom",listResponse.getData());

        return "/classinfo/schoolbusLine";
    }


    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, SchoolbusLine schoolbusLine) {

        return schoolbusLineClient.delete(schoolbusLine);
    }
    @ResponseBody
    @RequestMapping(value = "/reverseLines",method = RequestMethod.POST)
    public Response reverseLines(HttpServletRequest request, Model model, SchoolbusLine schoolbusLine) {

        return schoolbusLineClient.reverseLines(schoolbusLine);
    }
}
