/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.repositories.impl;

import com.nhom12.pojo.Activity;
import com.nhom12.pojo.Faculty;
import com.nhom12.pojo.MissingActivityReport;
import com.nhom12.pojo.StudentClass;
import com.nhom12.pojo.User;
import com.nhom12.repositories.MissingReportRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class MissingReportRepositoryImpl implements MissingReportRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<MissingActivityReport> getMissingReport(int facultyId) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root missingReportRoot = q.from(MissingActivityReport.class);
        Join<MissingActivityReport, User> user = missingReportRoot.join("studentId", JoinType.INNER);
        Join<MissingActivityReport, Activity> activity = missingReportRoot.join("activityId", JoinType.INNER);
        Join<User, Faculty> faculty = user.join("facultyId", JoinType.INNER);
        Join<User, StudentClass> studentClass = user.join("classId", JoinType.INNER);
        q.select(missingReportRoot);

        q.select(b.array(missingReportRoot, user, activity, faculty, studentClass));

        if (facultyId != 0) {
            q.where(b.equal(faculty.get("id"), facultyId));
        }

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void confirmReport(MissingActivityReport r) {
        Session s = this.factory.getObject().getCurrentSession();
        r.setStatus("Confirmed");
        
        s.update(r);
    }

    @Override
    public void deleteReport(int reportId) {
        Session s = this.factory.getObject().getCurrentSession();
        MissingActivityReport r = s.get(MissingActivityReport.class, reportId);
        s.delete(r);
    }

    @Override
    public MissingActivityReport getMissingReportById(int reportId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("MissingActivityReport.findById");
        q.setParameter("id", reportId);
        MissingActivityReport r = (MissingActivityReport) q.getSingleResult();
        return r;
    }

    @Override
    public void addReport(MissingActivityReport a) {
        Session s = this.factory.getObject().getCurrentSession();
        
        //already exists
        if(a.getId() != null)
            s.update(a);
        else //new
        {
            a.setRegisteredDate(new Date());
            a.setStatus("Pending");
            s.save(a);
        }
    }
}
