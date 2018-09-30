package com.wuxue.view.utils;


import java.util.List;

public class ClasStuListUtils {

    private List<String> pkClassinfos;

    private List<String> pkStudents ;

    public ClasStuListUtils() {
    }

    public ClasStuListUtils(List<String> pkClassinfos, List<String> pkStudents) {

        this.pkClassinfos = pkClassinfos;
        this.pkStudents = pkStudents;
    }

    public List<String> getPkClassinfos() {
        return pkClassinfos;
    }

    public void setPkClassinfos(List<String> pkClassinfos) {
        this.pkClassinfos = pkClassinfos;
    }

    public List<String> getPkStudents() {
        return pkStudents;
    }

    public void setPkStudents(List<String> pkStudents) {
        this.pkStudents = pkStudents;
    }
}
