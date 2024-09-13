/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.repositories.impl;

import com.nhom12.pojo.Activity;
import com.nhom12.pojo.ActivityRegistration;
import com.nhom12.pojo.User;
import com.nhom12.repositories.ActivityRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class ActivityRepositoryImpl implements ActivityRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;
    
    @Override
    public List<Activity> getActivityList(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Activity> q = b.createQuery(Activity.class);
        Root r = q.from(Activity.class);
        q.select(r);

        List<Predicate> predicates = new ArrayList<>();

        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()) {
            predicates.add(b.like(r.get("title"), String.format("%%%s%%", kw)));
        }


        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(r.get("id")));

        Query query = s.createQuery(q);
         
          //phân trang ở đây
        String p = params.get("page");
        if (p != null && !p.isEmpty()) {
            int pageSize = Integer.parseInt(env.getProperty("activities.pageSize"));
            int start = (Integer.parseInt(p) - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);
        }
        
        List<Activity> activities = query.getResultList();

        return activities;
    }

    @Override
    public void addOrUpdate(Activity a) {
        Session s = this.factory.getObject().getCurrentSession();
        
        //already exists
        if(a.getId() != null)
            s.update(a);
        else //new
        {
            a.setDate(new Date());
            s.save(a);
        }
            
    }

    @Override
    public Long countActivity() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> query = b.createQuery(Long.class);
        Root r = query.from(Activity.class);
        query.select(b.count(r));
        
        Long count = s.createQuery(query).getSingleResult();
        return count;
    }

    @Override
    public Activity getActivityById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Activity.findById");
        q.setParameter("id", id);
        Activity a = (Activity) q.getSingleResult();
        
        return a;
    }

    

    
    
}
