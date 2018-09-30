package com.wuxue.model;

import com.wuxue.base.BaiscPage;

import java.util.Date;

public class StudentAllergy extends BaiscPage {
    private String pkStudentAllergy;

    private String pkStudent;

    private String pkMaterial;

    public String getPkStudentAllergy() {
        return pkStudentAllergy;
    }

    public void setPkStudentAllergy(String pkStudentAllergy) {
        this.pkStudentAllergy = pkStudentAllergy;
    }

    public String getPkStudent() {
        return pkStudent;
    }

    public void setPkStudent(String pkStudent) {
        this.pkStudent = pkStudent;
    }

    public String getPkMaterial() {
        return pkMaterial;
    }

    public void setPkMaterial(String pkMaterial) {
        this.pkMaterial = pkMaterial;
    }
}