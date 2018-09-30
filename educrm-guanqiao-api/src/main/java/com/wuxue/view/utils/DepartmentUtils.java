package com.wuxue.view.utils;

import com.wuxue.model.Department;
import com.wuxue.utils.contract.Response;
import com.wuxue.view.client.system.DepartmentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentUtils {

    @Autowired
    private DepartmentClient departmentClient;

    /**
     * 获取上级部门
     * @param pkParent
     * @return
     */
    public Department getParentDep(String pkParent){

        Department department = new Department();
        department.setPkDepartment(pkParent);
        Response<Department> byPrimaryKey = departmentClient.findByPrimaryKey(department);
        return byPrimaryKey.getData();

    }

    public void setListModeAttribute(Model model,String name,String department){
        if(name == null || "".equals(name)||model == null){
            return;
        }
        model.addAttribute(name,getTreeDepartment(department));
    }

    /**
     * 获取本部门
     * @param pkDepartment
     * @return
     */
    public Department getDepartment(String pkDepartment){
        Department department = new Department();
        department.setPkDepartment(pkDepartment);
        Response<Department> byPrimaryKey = departmentClient.findByPrimaryKey(department);
        return byPrimaryKey.getData();
    }

    public List<Department> getAllDepartment(){
        Department department = new Department();
        Response<List<Department>> listResponse = departmentClient.find(department);
        return listResponse.getData();
    }

    /**
     * 获取本部门及所欲子部门
     * @param pkDepartment
     * @return
     */
    public List<Department> getTreeDepartment(String pkDepartment){

        Department department = getDepartment(pkDepartment);

        List<Department> allDepartment = getAllDepartment();

        List<Department> childrenDepartment = getChildrenDepartment(allDepartment, department.getPkDepartment());

        childrenDepartment.add(0,department);

        return childrenDepartment;
    }


    /**
     * 获取子部门
     * @param departments
     * @param patentid
     * @return
     */
    private static List<Department> getChildrenDepartment(List<Department> departments, String patentid) {

        List<Department> reList = new ArrayList<>();
        for (Department department : departments) {
            if (patentid.equals(department.getPkParent())) {
                reList.add(department);
                reList.addAll(getChildrenDepartment(departments, department.getPkDepartment()));
            }
        }
        return reList;
    }


}
