/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 *
 * @author Admin
 */
@Entity
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByAvatar", query = "SELECT u FROM User u WHERE u.avatar = :avatar"),
    @NamedQuery(name = "User.findByEmailId", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findById", query = "SELECT u FROM User u WHERE u.id = :id")})
public class User implements Serializable {

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull(message = "{user.name.nullErr}")
    @NotEmpty(message = "{user.name.nullErr}")
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "role_id")
    @ManyToOne
    private Role roleId;
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "avatar")
    private String avatar;
    @Size(max = 50, message = "{user.email.lenghtErr}")
    @NotEmpty(message = "{user.email.nullErr}")
    @Column(name = "email_id")
    private String email;
    @Basic(optional = false)
    @NotNull(message = "{user.pass.nullErr}")
    @Size(min = 1, max = 100, message = "{user.pass.lenghtErr}")
    @Column(name = "password")
    private String password;
    @NotNull
    @Column(name = "username")
    private String username;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "active")
    private boolean active;
//    @JoinTable(name = "student_score", joinColumns = {
//        @JoinColumn(name = "studentID", referencedColumnName = "id")}, inverseJoinColumns = {
//        @JoinColumn(name = "disciplineScoreID", referencedColumnName = "id")})
//    @ManyToMany
//    private Collection<DisciplineScore> disciplineScoreCollection;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private Collection<ActivityRegistration> activityRegistrationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    @JsonIgnore
    private Collection<ExtracurricularAchievement> extracurricularAchievementCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    @JsonIgnore
    private Collection<Activity> activityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    @JsonIgnore
    private Collection<Comment> commentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    @JsonIgnore
    private Collection<MissingActivityReport> missingActivityReportCollection;
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private Faculty facultyId;
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private StudentClass classId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    @JsonIgnore
    private Set<StudentLike> studentLikeSet;

    @Transient
    private MultipartFile file;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name, String avatar, String password, String email, Role role) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.password = password;
        this.email = email;
        this.roleId = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getRoleName() {
        System.out.println(this.roleId.getRole());
        return this.roleId.getRole().equals("ROLE_ChuyenVienCTSV")?
                "Chuyên Viên CTSV" : this.roleId.getRole().equals("ROLE_TroLySinhVien")?
                "Trợ Lý Sinh Viên" : "Sinh Viên";
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    @XmlTransient
//    public Collection<DisciplineScore> getDisciplineScoreCollection() {
//        return disciplineScoreCollection;
//    }
//
//    public void setDisciplineScoreCollection(Collection<DisciplineScore> disciplineScoreCollection) {
//        this.disciplineScoreCollection = disciplineScoreCollection;
//    }

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

    @XmlTransient
    public Collection<Activity> getActivityCollection() {
        return activityCollection;
    }

    public void setActivityCollection(Collection<Activity> activityCollection) {
        this.activityCollection = activityCollection;
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

    public Faculty getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Faculty facultyId) {
        this.facultyId = facultyId;
    }

    public StudentClass getClassId() {
        return classId;
    }

    public void setClassId(StudentClass classId) {
        this.classId = classId;
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
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nhom12.pojo.User[ id=" + id + " ]";
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

    /**
     * @return the active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(boolean active) {
        this.active = active;
    }

}
