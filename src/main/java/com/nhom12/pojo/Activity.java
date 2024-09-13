/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nhom12.adapters.DateAdapter;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "activity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Activity.findAll", query = "SELECT a FROM Activity a"),
    @NamedQuery(name = "Activity.findByTitle", query = "SELECT a FROM Activity a WHERE a.title = :title"),
    @NamedQuery(name = "Activity.findByDescription", query = "SELECT a FROM Activity a WHERE a.description = :description"),
    @NamedQuery(name = "Activity.findByDate", query = "SELECT a FROM Activity a WHERE a.date = :date"),
    @NamedQuery(name = "Activity.findById", query = "SELECT a FROM Activity a WHERE a.id = :id")})
public class Activity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "title")
    private String title;
    @Size(max = 500)
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "image")
    private String image;
    
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Faculty facultyId;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityId")
    private Collection<ActivityRegistration> activityRegistrationCollection;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityId")
    private Collection<ExtracurricularAchievement> extracurricularAchievementCollection;
    @JoinColumn(name = "disciplineScore_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DisciplineScore disciplineScoreid;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User createdBy;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityId")
    private Collection<Comment> commentCollection;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityId")
    private Collection<MissingActivityReport> missingActivityReportCollection;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "activityId")
    private Set<StudentLike> studentLikeSet;
    
    @Transient
    private MultipartFile file;

    public Activity() {
    }

    public Activity(Integer id) {
        this.id = id;
    }

    public Activity(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
    
    @XmlElement
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    @XmlElement
    public Faculty getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Faculty facultyId) {
        this.facultyId = facultyId;
    }
    
    @XmlElement
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @XmlElement
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    @XmlElement
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @XmlElement
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @XmlTransient
    public Collection<ActivityRegistration> getActivityRegistrationCollection() {
        return activityRegistrationCollection;
    }

    public void setActivityRegistrationCollection(Collection<ActivityRegistration> activityRegistrationCollection) {
        this.activityRegistrationCollection = activityRegistrationCollection;
    }

    @XmlTransient
    public Collection<ExtracurricularAchievement> getExtracurricularAchievementCollection() {
        return extracurricularAchievementCollection;
    }

    public void setExtracurricularAchievementCollection(Collection<ExtracurricularAchievement> extracurricularAchievementCollection) {
        this.extracurricularAchievementCollection = extracurricularAchievementCollection;
    }

    public DisciplineScore getDisciplineScoreid() {
        return disciplineScoreid;
    }

    public void setDisciplineScoreid(DisciplineScore disciplineScoreid) {
        this.disciplineScoreid = disciplineScoreid;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User u) {
        this.createdBy = u;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    @XmlTransient
    public Collection<MissingActivityReport> getMissingActivityReportCollection() {
        return missingActivityReportCollection;
    }

    public void setMissingActivityReportCollection(Collection<MissingActivityReport> missingActivityReportCollection) {
        this.missingActivityReportCollection = missingActivityReportCollection;
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
        if (!(object instanceof Activity)) {
            return false;
        }
        Activity other = (Activity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nhom12.pojo.Activity[ id=" + id + " ]";
    }

    @XmlTransient
    public Set<StudentLike> getStudentLikeSet() {
        return studentLikeSet;
    }

    public void setStudentLikeSet(Set<StudentLike> studentLikeSet) {
        this.studentLikeSet = studentLikeSet;
    }

    /**
     * @return the file
     */
    @XmlTransient
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
}
