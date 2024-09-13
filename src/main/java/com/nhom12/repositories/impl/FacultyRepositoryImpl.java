/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.repositories.impl;

import com.nhom12.pojo.Faculty;
import com.nhom12.repositories.FacultyRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
public class FacultyRepositoryImpl implements FacultyRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<Faculty> getFaculties() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Faculty.findAll");
        return q.getResultList();
    }

    @Override
    public Long countFaculty() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> query = b.createQuery(Long.class);
        Root r = query.from(Faculty.class);
        query.select(b.count(r));
        
        Long count = s.createQuery(query).getSingleResult();
        return count;
    }

    @Override
    public Faculty getFacultyById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Faculty.findById");
        q.setParameter("id", id);
        
        return (Faculty) q.getSingleResult();
    }
    
}
