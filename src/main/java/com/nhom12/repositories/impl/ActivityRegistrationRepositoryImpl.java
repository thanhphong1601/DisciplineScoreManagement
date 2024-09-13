/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.repositories.impl;

import com.nhom12.pojo.Activity;
import com.nhom12.pojo.ActivityRegistration;
import com.nhom12.pojo.Faculty;
import com.nhom12.pojo.StudentClass;
import com.nhom12.pojo.User;
import com.nhom12.repositories.ActivityRegistrationRepository;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class ActivityRegistrationRepositoryImpl implements ActivityRegistrationRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;
    
    @Override
    public List<Object[]> getJoinedList() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        
        Root activityRegistrationRoot = q.from(ActivityRegistration.class);
        Join<ActivityRegistration, Activity> joinedActivity = activityRegistrationRoot.join("activityId", JoinType.INNER);
        Join<ActivityRegistration, User> joinedStudent = activityRegistrationRoot.join("studentId", JoinType.INNER);
        Join<User, Faculty> faculty = joinedStudent.join("facultyId", JoinType.INNER);
        Join<User, StudentClass> studentClass = joinedStudent.join("classId", JoinType.INNER);
        
        q.select(b.array(joinedActivity, joinedStudent, faculty, studentClass));
        
        
        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void addRegistration(ActivityRegistration a) {
        Session s = this.factory.getObject().getCurrentSession();
        
        //already exists
        if(a.getId() != null)
            s.update(a);
        else //new
        {
            a.setRegisteredDate(new Date());
            s.save(a);
        }
    }

    @Override
    public List<ActivityRegistration> getJoinedActivityList(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        int userId = Integer.parseInt(params.get("userId"));
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<ActivityRegistration> q = b.createQuery(ActivityRegistration.class);
        
        Root aRoot = q.from(ActivityRegistration.class);
        q.select(aRoot);
        
        q.where(b.equal(aRoot.get("studentId"), userId))
                .orderBy(b.desc(aRoot.get("registeredDate")));
        
        Query query = s.createQuery(q);
        
        String p = params.get("page");
        if (p != null && !p.isEmpty()) {
            int pageSize = Integer.parseInt(env.getProperty("joinedActivities.pageSize"));
            int start = (Integer.parseInt(p) - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);
        }
        
        
        List<ActivityRegistration> a = query.getResultList();
        
        return a;
    }

    @Override
    public void save(ActivityRegistration a) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(a);
    }
}
