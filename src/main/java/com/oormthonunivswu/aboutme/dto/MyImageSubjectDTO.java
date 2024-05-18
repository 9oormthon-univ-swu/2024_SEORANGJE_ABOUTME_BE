package com.oormthonunivswu.aboutme.Dto;

import java.util.Map;

public class MyImageSubjectDTO {
    private Map<String, Boolean> subjects;

    public  Map<String, Boolean> getSubjects(){
        return subjects;
    }

    public void setSubjects(Map<String, Boolean>subjects){
        this.subjects = subjects;
    }

}
