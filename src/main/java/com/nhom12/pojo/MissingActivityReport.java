/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.pojo;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "missing_activity_report")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MissingActivityReport.findAll", query = "SELECT m FROM MissingActivityReport m"),
    @NamedQuery(name = "MissingActivityReport.findByRegisteredDate", query = "SELECT m FROM MissingActivityReport m WHERE m.registeredDate = :registeredDate"),
    @NamedQuery(name = "MissingActivityReport.findByProof", query = "SELECT m FROM MissingActivityReport m WHERE m.proof = :proof"),
    @NamedQuery(name = "MissingActivityReport.findByStatus", query = "SELECT m FROM MissingActivityReport m WHERE m.status = :status"),
    @NamedQuery(name = "MissingActivityReport.findById", query = "SELECT m FROM MissingActivityReport m WHERE m.id = :id")})
public class MissingActivityReport implements Serializable {

   

    private static final long serialVersionUID = 1L;
    @Column(name = "registered_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date registeredDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proof")
    private String proof;
    @Size(max = 9)
    @Column(name = "status")
    private String status;
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
    
    @Transient
    private MultipartFile file;

    public MissingActivityReport() {
    }

    public MissingActivityReport(Integer id) {
        this.id = id;
    }

    public MissingActivityReport(Integer id, String proof) {
        this.id = id;
        this.proof = proof;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public String getProof() {
        return proof;
    }

    public void setProof(String proof) {
        this.proof = proof;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    
    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
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
        if (!(object instanceof MissingActivityReport)) {
            return false;
        }
        MissingActivityReport other = (MissingActivityReport) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nhom12.pojo.MissingActivityReport[ id=" + id + " ]";
    }
    
}
