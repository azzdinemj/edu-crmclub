package com.wuxue.view.controllers.system;

import com.wuxue.model.SysMenu;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.SysMenuClient;
import com.wuxue.view.contract.TreeGridColumn;
import com.wuxue.view.contract.TreeGridModel;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.IEditController;
import com.wuxue.view.interfaces.IQueryByPagingController;
import com.wuxue.view.interfaces.IQueryController;
import com.wuxue.view.interfaces.ISaveController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单管理控制器
 */
@Controller
@RequestMapping("/system/sysMenuManager")
public class SysMenuManagerController extends BaseController implements IQueryController<SysMenu, String>,
        ISaveController<SysMenu, String>, IEditController<SysMenu, String>,IQueryByPagingController<SysMenu,Map<String,Object>> {

    @Autowired
    SysMenuClient sysMenuClient;



    /*
     *编辑-跳转
     *sysMenu  参数为:主键不为空
     */
    @Override
    public String edit(HttpServletRequest request, Model model, SysMenu sysMenu) {
           if(sysMenu.getPkSysMenu()!=null&&sysMenu.getPkSysMenu()!=""){
               Response<SysMenu> response=sysMenuClient.findByPrimaryKey(sysMenu);
               model.addAttribute("responseSysmenu",response.getData());
           }
           return "/system/sysMenuManager";
    }




    /*
     *新增/修改
     *
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model, SysMenu sysmenu) {
         return sysMenuClient.save(sysmenu);
    }


    /*
     *查询菜单列表
     *
     */
//    @Override
//    public String query(HttpServletRequest request, Model model, SysMenu sysMenu) throws IOException {
//        Response<List<SysMenu>> listResponse = sysMenuClient.find(sysMenu);
//        model.addAttribute("list", listResponse.getData());
//        return "/system/sysMenuManagerList";
//    }


    /**
     * 查询菜单列表
     *
     * @param model
     * @return
     * @throws Exception
     */
    @Override
    public String query(HttpServletRequest request, Model model, SysMenu sysMenu)  {

        return "/system/sysMenuManagerList";
    }


    @RequestMapping(value = "/getData",method = RequestMethod.GET)
    @ResponseBody
    public TreeGridModel<SysMenu> getData(){
        TreeGridModel<SysMenu> treeGridModel=new TreeGridModel<SysMenu>();
        Response<List<SysMenu>> response =  sysMenuClient.find(new SysMenu());
        List<SysMenu> list = new ArrayList<>();
        for(SysMenu sys : response.getData()){
            if(sys.getPkParent()==null || sys.getPkParent().equals("")){
                sys.setCaption("<a class='yellow'   href='/system/sysMenuManager/edit?pkSysMenu="+sys.getPkSysMenu()+"'>"+sys.getCaption()+"</a>");

                getData(response.getData(),sys);
                list.add(sys);
            }
        }
        treeGridModel.setData(list);

        treeGridModel.setRenderTo("div1");
        List<TreeGridColumn> columnList = new ArrayList<>();
        columnList.add(createTreeGridColumn("pk_sys_menu","pkSysMenu"));
        columnList.add(createTreeGridColumn("pkParent","pkParent"));
        columnList.add(createTreeGridColumn("caption","caption"));
        columnList.add(createTreeGridColumn("captionEng","captionEng"));
        columnList.add(createTreeGridColumn("url","url"));
        columnList.add(createTreeGridColumn("sort","sort"));
        columnList.add(createTreeGridColumn("isdisplay","isdisplay"));


        treeGridModel.setColumns(columnList);
        treeGridModel.setFolderCloseIcon("/images/folderOpen.png");
        treeGridModel.setFolderOpenIcon("/images/folderClose.png");
        treeGridModel.setDefaultLeafIcon("/images/defaultLeaf.gif");

        return treeGridModel;
    }

    //创建 树形表格
    private TreeGridColumn createTreeGridColumn(String headText,String dataField){
        TreeGridColumn treeGridColumn = new TreeGridColumn();
        treeGridColumn.setHeaderText(headText);
        treeGridColumn.setDataField(dataField);
        return treeGridColumn;
    }


    //获取数据
    private void getData(List<SysMenu> list,SysMenu sys){
        List<SysMenu> listTmp = new ArrayList<>();
        for (SysMenu sysMenu : list){
            if(sys.getPkSysMenu().equals(sysMenu.getPkParent())){
                sysMenu.setCaption("<a class='yellow'  href='/system/sysMenuManager/edit?pkSysMenu="+sysMenu.getPkSysMenu()+"'>"+sysMenu.getCaption()+"</a>");
                listTmp.add(sysMenu);
                getData(list,sysMenu);
            }
        }
        if(listTmp.size()>0) {
            sys.setChildren(listTmp);
        }

    }





    /*
     *查询列表 modal框
     *
     */
   @RequestMapping("/querylist")
    public String querylist(HttpServletRequest request, Model model, SysMenu sysMenu) throws IOException {
       return "/model/sysMenuManagerModel";
    }



    //  弹出框查看所有parent为空的数据
    @Override
    @ResponseBody
    public Map<String, Object> queryByPaging(HttpServletRequest request, HttpServletResponse response, SysMenu sysMenu, Integer sEcho, Integer start, Integer length) {
        sysMenu.setPageNo((start/length)+1);
        sysMenu.setPageSize(length);

        Response<List<SysMenu>> listResponse = sysMenuClient.findparentisnull(sysMenu);
        List<SysMenu> data = listResponse.getData();
        if(data==null) {
            data = new ArrayList<>();
        }
        return convertToPaging(listResponse.getTotal(),listResponse.getTotal(),data);
    }



}
