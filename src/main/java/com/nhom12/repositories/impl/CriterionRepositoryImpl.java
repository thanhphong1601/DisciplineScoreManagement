/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.repositories.impl;

import com.nhom12.pojo.Criterion;
import com.nhom12.repositories.CriterionRepository;
import java.util.List;
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
public class CriterionRepositoryImpl implements CriterionRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<Criterion> getList() {
        Session  s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Criterion.findAll");
        
        List<Criterion> list = q.getResultList();
        
        return list;
    }
    
}
