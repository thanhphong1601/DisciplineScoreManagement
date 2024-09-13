/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.repositories.impl;

import com.nhom12.pojo.StudentClass;
import com.nhom12.repositories.StudentClassRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
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
public class StudentClassRepositoryImpl implements StudentClassRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    
    
    @Override
    public List<StudentClass> getClasses() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM StudentClass");
        return q.getResultList();
    }

    @Override
    public Long countClass() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> query = b.createQuery(Long.class);
        Root r = query.from(StudentClass.class);
        query.select(b.count(r));
        
        Long count = s.createQuery(query).getSingleResult();
        return count;

    }

    @Override
    public StudentClass getClassById(int classId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("StudentClass.findById");
        q.setParameter("id", classId);
        
        return (StudentClass) q.getSingleResult();
    }
    
}
