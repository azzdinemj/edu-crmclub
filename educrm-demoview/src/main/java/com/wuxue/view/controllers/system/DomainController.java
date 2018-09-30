package com.wuxue.view.controllers.system;

import com.alibaba.fastjson.JSON;
import com.wuxue.base.KeyValue;
import com.wuxue.model.Domain;

import com.wuxue.model.SysAutocode;
import com.wuxue.utils.common.DataUtils;
import com.wuxue.utils.contract.LinkEntity;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.DomainClient;
import com.wuxue.view.client.system.SysAutcodeClient;
import com.wuxue.view.contract.TreeGridColumn;
import com.wuxue.view.contract.TreeGridModel;
import com.wuxue.view.controllers.BaseController;
import com.wuxue.view.interfaces.*;
import com.wuxue.view.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 校区管理
 */
@Controller
@RequestMapping(value = "/system/domain")
public class DomainController extends BaseController implements IQueryController<Domain,String>,ISaveController<Domain, String>,
        ICreateController<Domain, String>,IEditController<Domain, String>,IDeleteController<Domain, String> {

    @InitBinder("domain")
    public void initBinder(WebDataBinder binder){
        binder.setFieldDefaultPrefix("domin.");
    }

    @Autowired
    private DomainClient campusClient;
    @Autowired
    private SysAutcodeClient sysAutcodeClient;
    @Autowired
    private DomainUtils domainUtils;


    /**
     * 校区列表
     *
     * @param model
     * @return
     * @throws Exception
     */
    @Override

    public String query(HttpServletRequest request, Model model, Domain domain)  {

//        Response<List<Domain>> listResponse = campusClient.find(domain);
//        List<Domain> data = listResponse.getData();
//        List<JsonUtils> list = new ArrayList<>();
//
//        String s1 = SessionCache.getPkdomain();
//        domain.setPkDomain(s1);
//        Response<Domain> byPrimaryKey = campusClient.findByPrimaryKey(domain);
//        model.addAttribute("doamin",byPrimaryKey.getData());
//
//
//
//
//
//        //List<Domain> domains = getDomain(data, SessionCache.getPkdomain());
//
//
//        JsonUtils jsonUtils;
//        for (Domain datum : data) {
//            jsonUtils= new JsonUtils();
//
//            jsonUtils.setId(datum.getPkDomain());
//            jsonUtils.setpId(datum.getPkParent());
//            jsonUtils.setName(datum.getCaption());
//            list.add(jsonUtils);
//
//        }
//        String s = JSON.toJSONString(list);
//        model.addAttribute("data1",s);

        return "/system/doMainlist";
    }

    /**
     * 修改页面
     *
     * @param domain
     * @param model
     * @return
     */
    @Override
    public String edit(HttpServletRequest request, Model model,Domain domain) {

        Response<Domain> byPrimaryKey = campusClient.findByPrimaryKey(domain);
        model.addAttribute("domains", byPrimaryKey.getData());
        Domain parentDomain = new Domain();

        Response<List<Domain>> listResponse = campusClient.find(parentDomain);
        for (Domain domain1 : listResponse.getData()) {
            if(domain1.getPkDomain().equals(byPrimaryKey.getData().getPkParent())){
                parentDomain = domain1;
            }
        }
       /* Domain parentDomain1 = domainUtils.getParentDomain(byPrimaryKey.getData().getPkDomain());*/



        model.addAttribute("parentDomain",parentDomain);

        return "/system/domainModel";
    }

    /**
     * 保存
     *
     * @param domain
     * @return
     */
    @Override
    @ResponseBody
    public String save(HttpServletRequest request, Model model,Domain domain) {
        String userName = SessionCache.getUserName();
        if(domain.getPkDomain() == null || "".equals(domain.getPkDomain())){
            domain.setPkDomain(GuidUtils.getGuid());
            domain.setCreator(userName);
            domain.setModifier(userName);
        }else {
            domain.setModifier(userName);
        }
        String response = campusClient.save(domain);

        return response;
    }

    /**
     * 跳转添加页面
     *
     * @return
     */
    @Override
    public String create(HttpServletRequest request, Model model,Domain domain) {

        List<Domain> parentDomain= new ArrayList<>();

        if(domain.getPkDomain() == null || "".equals(domain.getPkDomain())){
            domain.setPkDomain(SessionCache.getPkdomain());
            List<Domain> allDomains = domainUtils.getAllDomains();
            parentDomain = getDomain(allDomains, domain.getPkDomain());
            for (Domain allDomain : allDomains) {
                if(domain.getPkDomain().equals(allDomain.getPkDomain())){
                    parentDomain.add(0,allDomain);
                }
            }

        }else {
            Domain domain1 = domainUtils.getDomain(domain.getPkDomain());
            parentDomain.add(domain1);
        }


        model.addAttribute("parentDomains",parentDomain);
        SysAutocode sysAutocode = new SysAutocode();
        sysAutocode.setPkSysAutocode(AutoCodeEnum.DOMAIN);
        String code = sysAutcodeClient.getCode(sysAutocode);
        domain.setPkParent(code);
        domain.put(LinkEntity.CREATOR_ENTITY, EmployeeUtils.getSysUser());
        domain.put(LinkEntity.MODIFIER_ENTITY,EmployeeUtils.getSysUser());
        model.addAttribute("domains",domain);


        return "/system/domainModel";
    }

    @Override
    @ResponseBody
    public String delete(HttpServletRequest request, Model model,Domain domain) {
        String response = campusClient.delete(domain.getPkDomain());
        return response;
    }

    @RequestMapping(value = "/getList",method = RequestMethod.POST)
    @ResponseBody
    public String getlist(HttpServletRequest request, Model model,Domain domain){
        Response<List<Domain>> listResponse = campusClient.find(domain);
        List<Domain> data = listResponse.getData();
        List<JsonUtils> list = new ArrayList<>();
        JsonUtils jsonUtils;
        for (Domain datum : data) {
            jsonUtils= new JsonUtils();

            jsonUtils.setId(datum.getPkDomain());
            jsonUtils.setpId(datum.getPkParent());
            jsonUtils.setName(datum.getCaption());
            list.add(jsonUtils);

        }
        String s = JSON.toJSONString(list);


        return s;
    }



    public List<Domain> getDomain(List<Domain> list , String pkDomain){
        List<Domain> domains = new ArrayList<>();
        if(list != null && list.size()>0){
            for (Domain domain : list) {
                if(pkDomain.equals(domain.getPkParent())){
                    domains.addAll(getDomain(list,domain.getPkDomain()));
                    domains.add(domain);
                }
            }
        }

        return domains;
    }


    @RequestMapping(value = "/getData",method = RequestMethod.GET)
    @ResponseBody
    public TreeGridModel<Domain> getData(){
        TreeGridModel<Domain> treeGridModel=new TreeGridModel<Domain>();
        Response<List<Domain>> response =  campusClient.find(new Domain());
        List<Domain> list = new ArrayList<>();
        for(Domain domain : response.getData()){
            if(domain.getPkParent()==null || domain.getPkParent().equals("")){
                domain.setCaption("<a class='yellow'  data-toggle='modal' data-target='#modal' data-url='/system/domain/edit?pkDomain="+domain.getPkDomain()+"'>"+domain.getCaption()+"</a>");
                domain.setCreationDateTime(DateTimeUtils.DateToString(domain.getCreationDate()));
                domain.setLasteditDateTime(DateTimeUtils.DateToString(domain.getLasteditDate()));
                KeyValue creatorEntity = DataUtils.objectToObject(domain.getMap().get(LinkEntity.CREATOR_ENTITY), KeyValue.class);
                domain.setCreator(creatorEntity.getCaption());
                KeyValue modeifierrEntity = DataUtils.objectToObject(domain.getMap().get(LinkEntity.MODIFIER_ENTITY), KeyValue.class);
                domain.setModifier(modeifierrEntity.getCaption());

//                domain.setCreator(domain.getMap().get("caption"));
                getData(response.getData(),domain);
                list.add(domain);
            }
        }
        treeGridModel.setData(list);

        treeGridModel.setRenderTo("div1");
        List<TreeGridColumn> columnList = new ArrayList<>();
        columnList.add(createTreeGridColumn("校区编码","pkDomain"));
        columnList.add(createTreeGridColumn("校区名称","caption"));
        columnList.add(createTreeGridColumn("校区负责人","person"));
        columnList.add(createTreeGridColumn("电话","phone"));
        columnList.add(createTreeGridColumn("传真","fax"));
        columnList.add(createTreeGridColumn("eMail","email"));
        columnList.add(createTreeGridColumn("创建人","creator"));
        columnList.add(createTreeGridColumn("创建时间","creationDateTime"));
        columnList.add(createTreeGridColumn("修改人","modifier"));
        columnList.add(createTreeGridColumn("修改时间","lasteditDateTime"));
//        columnList.add(createTreeGridColumn("修改时间","lasteditDate"));

        treeGridModel.setColumns(columnList);



        treeGridModel.setFolderCloseIcon("/images/folderOpen.png");
        treeGridModel.setFolderOpenIcon("/images/folderClose.png");
        treeGridModel.setDefaultLeafIcon("/images/defaultLeaf.gif");

        return treeGridModel;
    }
    private TreeGridColumn createTreeGridColumn(String headText,String dataField){
        TreeGridColumn treeGridColumn = new TreeGridColumn();
        treeGridColumn.setHeaderText(headText);
        treeGridColumn.setDataField(dataField);
        return treeGridColumn;
    }
    private void getData(List<Domain> list,Domain domain){
        List<Domain> listTmp = new ArrayList<>();
        for (Domain datum : list){
//            if(datum.getPkParent().equals(domain.getPkDomain())){
            if(domain.getPkDomain().equals(datum.getPkParent())){
                datum.setCaption("<a class='yellow'  data-toggle='modal' data-target='#modal' data-url='/system/domain/edit?pkDomain="+datum.getPkDomain()+"'>"+datum.getCaption()+"</a>");
                datum.setCreationDateTime(DateTimeUtils.DateToString(domain.getCreationDate()));
                datum.setLasteditDateTime(DateTimeUtils.DateToString(domain.getLasteditDate()));
                KeyValue creatorEntity = DataUtils.objectToObject(domain.getMap().get(LinkEntity.CREATOR_ENTITY), KeyValue.class);
                datum.setCreator(creatorEntity.getCaption());
                KeyValue modeifierrEntity = DataUtils.objectToObject(domain.getMap().get(LinkEntity.MODIFIER_ENTITY), KeyValue.class);
                datum.setModifier(modeifierrEntity.getCaption());
                listTmp.add(datum);
                getData(list,datum);
            }
        }
        if(listTmp.size()>0) {
            domain.setChildren(listTmp);
        }
        // department.put("children",listTmp);
    }



}
