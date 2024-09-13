/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "extracurricular_achievement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExtracurricularAchievement.findAll", query = "SELECT e FROM ExtracurricularAchievement e"),
    @NamedQuery(name = "ExtracurricularAchievement.findByAchievement", query = "SELECT e FROM ExtracurricularAchievement e WHERE e.achievement = :achievement"),
    @NamedQuery(name = "ExtracurricularAchievement.findById", query = "SELECT e FROM ExtracurricularAchievement e WHERE e.id = :id")})
public class ExtracurricularAchievement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "achievement")
    private String achievement;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "activity_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Activity activityId;
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User studentId;

    public ExtracurricularAchievement() {
    }

    public ExtracurricularAchievement(Integer id) {
        this.id = id;
    }

    public ExtracurricularAchievement(Integer id, String achievement) {
        this.id = id;
        this.achievement = achievement;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Activity getActivityId() {
        return activityId;
    }

    public void setActivityId(Activity activityId) {
        this.activityId = activityId;
    }

    public User getStudentId() {
        return studentId;
    }

    public void setStudentId(User studentId) {
        this.studentId = studentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExtracurricularAchievement)) {
            return false;
        }
        ExtracurricularAchievement other = (ExtracurricularAchievement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nhom12.pojo.ExtracurricularAchievement[ id=" + id + " ]";
    }
    
}
