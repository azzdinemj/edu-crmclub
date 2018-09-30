package com.wuxue.view.utils;

import com.wuxue.model.Domain;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.DomainClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class DomainUtils {

    @Autowired
    private DomainClient domainClient;

    /**
     * 获取当前校区信息
     * @param pkDomain
     * @return
     */
    public Domain getDomain(String pkDomain){
        Domain domain = new Domain();
        if(pkDomain ==null || "".equals(pkDomain)){
            return domain;
        }
        domain.setPkDomain(pkDomain);
        Response<Domain> domainResponse = domainClient.findByPrimaryKey(domain);
       return domainResponse.getData();
    }

    public void setModeAttribute(Model model, String name, String pkDomain){
        if(model == null || name ==null || name.equals("")){
            return;
        }
        model.addAttribute(name,getDomain(pkDomain));
    }
    public void setListModeAttribute(Model model, String name, String pkDomain){
        if(model == null || name ==null || name.equals("")){
            return;
        }
        model.addAttribute(name,getTreeDomainList(pkDomain));
    }



    /**
     * 获取上级校区
     * @param pkDomain
     * @return
     */
    public Domain getParentDomain(String pkDomain){

        return setParentDomain(pkDomain);
    }


    private Domain setParentDomain(String pkDomain){
        Domain domain = getDomain(pkDomain);
        List<Domain> allDomains = getAllDomains();
        if(allDomains !=null && allDomains.size()>0){
            for (Domain allDomain : allDomains) {
                if(domain.getPkParent().equals(allDomain)){
                    domain = allDomain;
                }
            }
        }

        return domain;
    }


    /**
     * 获取所有校区
     * @param
     * @return
     */
    public List<Domain> getAllDomains(){
        return setAllDomains();
    }

    private List<Domain> setAllDomains(){
        Domain domain = new Domain();
        Response<List<Domain>> listResponse = domainClient.find(domain);

        return listResponse.getData();
    }

    /**
     * 获取当前校区及子校区
     * @return
     */
    private List<Domain> getTreeDomainList(String pkDomain){

        Domain domain = getDomain(pkDomain);
        List<Domain> allDomains = getAllDomains();

        List<Domain> childrenDomains = getChildrenDomains(allDomains, pkDomain);
        childrenDomains.add(0,domain);

        return childrenDomains;
    }

    /**
     * 获取子校区
     * @param domain
     * @param patentid
     * @return
     */
    private static List<Domain> getChildrenDomains(List<Domain> domain, String patentid) {
;
        List<Domain> reList = new ArrayList<>();
        for (Domain domain1 : domain) {
            if (patentid.equals(domain1.getPkParent())) {
                reList.add(domain1);
                reList.addAll(getChildrenDomains(domain, domain1.getPkDomain()));
            }
        }
        return reList;
    }

}
