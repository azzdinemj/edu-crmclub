package com.wuxue.view.utils;

import com.wuxue.model.StudentSignupDetails;
import com.wuxue.model.StudentSignupDetails;

import java.util.List;

public class SignupDetailsList {

    private List<StudentSignupDetails> details;

    public SignupDetailsList(List<StudentSignupDetails> details) {
        this.details = details;
    }

    public SignupDetailsList() {
    }

    public List<StudentSignupDetails> getDetails() {
        return details;
    }

    public void setDetails(List<StudentSignupDetails> details) {
        this.details = details;
    }
}
