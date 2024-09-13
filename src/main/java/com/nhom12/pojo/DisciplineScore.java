/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "discipline_score")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DisciplineScore.findAll", query = "SELECT d FROM DisciplineScore d"),
    @NamedQuery(name = "DisciplineScore.findByScore", query = "SELECT d FROM DisciplineScore d WHERE d.score = :score"),
    @NamedQuery(name = "DisciplineScore.findByCriterion", query = "SELECT d FROM DisciplineScore d WHERE d.criterion = :criterion"),
    @NamedQuery(name = "DisciplineScore.findBySemester", query = "SELECT d FROM DisciplineScore d WHERE d.semester = :semester"),
    @NamedQuery(name = "DisciplineScore.findByFromYear", query = "SELECT d FROM DisciplineScore d WHERE d.fromYear = :fromYear"),
    @NamedQuery(name = "DisciplineScore.findByToYear", query = "SELECT d FROM DisciplineScore d WHERE d.toYear = :toYear"),
    @NamedQuery(name = "DisciplineScore.findById", query = "SELECT d FROM DisciplineScore d WHERE d.id = :id")})
public class DisciplineScore implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "score")
    private int score;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "criterion")
    private String criterion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "semester")
    private int semester;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fromYear")
    private int fromYear;
    @Basic(optional = false)
    @NotNull
    @Column(name = "toYear")
    private int toYear;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
//    @ManyToMany(mappedBy = "disciplineScoreCollection")
//    private Collection<User> userCollection;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "disciplineScoreid")
    private Collection<Activity> activityCollection;

    public DisciplineScore() {
    }

    public DisciplineScore(Integer id) {
        this.id = id;
    }

    public DisciplineScore(Integer id, int score, String criterion, int semester, int fromYear, int toYear) {
        this.id = id;
        this.score = score;
        this.criterion = criterion;
        this.semester = semester;
        this.fromYear = fromYear;
        this.toYear = toYear;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCriterion() {
        return criterion;
    }

    public void setCriterion(String criterion) {
        this.criterion = criterion;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getFromYear() {
        return fromYear;
    }

    public void setFromYear(int fromYear) {
        this.fromYear = fromYear;
    }

    public int getToYear() {
        return toYear;
    }

    public void setToYear(int toYear) {
        this.toYear = toYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    @XmlTransient
//    public Collection<User> getUserCollection() {
//        return userCollection;
//    }
//
//    public void setUserCollection(Collection<User> userCollection) {
//        this.userCollection = userCollection;
//    }

    @XmlTransient
    public Collection<Activity> getActivityCollection() {
        return activityCollection;
    }

    public void setActivityCollection(Collection<Activity> activityCollection) {
        this.activityCollection = activityCollection;
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
        if (!(object instanceof DisciplineScore)) {
            return false;
        }
        DisciplineScore other = (DisciplineScore) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nhom12.pojo.DisciplineScore[ id=" + id + " ]";
    }
    
}
