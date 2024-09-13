/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.requests;

import com.nhom12.pojo.User;

/**
 *
 * @author Admin
 */
public class ActivityRequest {
    private User createdBy;
    private int facultyId;
    private int disciplineScoreid;
    private String title;
    private String description;

    /**
     * @return the createdBy
     */
    public User getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the facultyId
     */
    public int getFacultyId() {
        return facultyId;
    }

    /**
     * @param facultyId the facultyId to set
     */
    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    /**
     * @return the disciplineScoreid
     */
    public int getDisciplineScoreid() {
        return disciplineScoreid;
    }

    /**
     * @param disciplineScoreid the disciplineScoreid to set
     */
    public void setDisciplineScoreid(int disciplineScoreid) {
        this.disciplineScoreid = disciplineScoreid;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
}
