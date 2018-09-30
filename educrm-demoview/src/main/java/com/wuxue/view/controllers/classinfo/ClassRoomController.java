package com.wuxue.view.controllers.classinfo;

import com.wuxue.model.ClassRoom;
import com.wuxue.utils.common.PicZoomUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.classInfo.ClassRoomClient;
import com.wuxue.view.client.system.SysUserClient;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.EmployeeUtils;
import com.wuxue.view.utils.GuidUtils;
import com.wuxue.view.utils.SessionCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 教室管理
 */
@Controller
@RequestMapping(value = "/classinfo/classRoom")
public class ClassRoomController extends BaseController
        implements IQueryController<ClassRoom, String>, ISaveController<ClassRoom, String>,
        ICreateController<ClassRoom, String>, IEditController<ClassRoom, String>, IDeleteController<ClassRoom, String> {

    @InitBinder("classRoom")
    public void initBinder(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("room.");
    }

    @Autowired
    private ClassRoomClient classRoomClient;
    @Autowired
    private SysUserClient sysUserClient;

    /**
     * 教室列表页面
     *
     * @param model
     * @return
     * @throws java.text.ParseException
     */
    @Override
    public String query(HttpServletRequest request, Model model, ClassRoom classRoom){
        Response<List<ClassRoom>> listResponse = classRoomClient.find(classRoom);

        model.addAttribute("list", listResponse.getData());
        return "/classinfo/classRoomList";
    }

    /**
     * 修改页面
     *
     * @param classRoom
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model, ClassRoom classRoom) {
        Response<ClassRoom> byPrimaryKey = classRoomClient.findByPrimaryKey(classRoom);
        model.addAttribute("classRoom", byPrimaryKey.getData());
        return "/classinfo/classRoom";
    }
    /**
     * 保存
     *
     * @param classRoom
     * @param request
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, ClassRoom classRoom) {
        if (classRoom.getPkClassRoom() == null || classRoom.getPkClassRoom().equals("")) {
            classRoom.setPkClassRoom(GuidUtils.getGuid());
            classRoom.setPkDomain(SessionCache.getPkdomain());
            classRoom.setCreator(SessionCache.getUserCode());
            classRoom.setCreationDate(new Date());
        }
        classRoom.setModifier(SessionCache.getUserCode());
        classRoom.setLasteditDate(new Date());
        return classRoomClient.save(classRoom);

    }

    /**
     * 添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model, ClassRoom classRoom) {
        classRoom = new ClassRoom();
        classRoom.setIsvalid(1);
        classRoom.setPkDomain(SessionCache.getPkdomain());
        classRoom.put(LinkEntity.CREATOR_ENTITY, EmployeeUtils.getSysUser());
        classRoom.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
        classRoom.setCreationDate(new Date());
        classRoom.setLasteditDate(new Date());
        model.addAttribute("classRoom", classRoom);

        return "/classinfo/classRoom";
    }

    /**
     * 删除
     *
     * @param classRoom
     * @return
     */
    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model, ClassRoom classRoom) {
        return classRoomClient.delete(classRoom.getPkClassRoom());
    }


//    @RequestMapping(value = "/uploads",method = RequestMethod.POST)
//    public String uploads(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
//        UploadUtils uploadUtils = new UploadUtils();
//        uploadUtils.doPost(request,response);
//        return null;
//    }

}
