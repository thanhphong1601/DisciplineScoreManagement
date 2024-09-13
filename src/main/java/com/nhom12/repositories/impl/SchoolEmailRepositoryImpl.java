/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.repositories.impl;

import com.nhom12.pojo.SchoolEmail;
import com.nhom12.repositories.SchoolEmailRepository;
import java.util.List;
import javax.persistence.Query;
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
public class SchoolEmailRepositoryImpl implements SchoolEmailRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<SchoolEmail> getEmails() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("SchoolEmail.findAll");
        return q.getResultList();
    }

    @Override
    public boolean checkEmail(String email) {
        List<SchoolEmail> emails = getEmails();
        for (SchoolEmail e : emails) {
            if (e.getEmail().equals(email) && e.getIsUsed() == false) //mail có và chưa dùng
            {
                return true;
            }
        }
        return false;
    }

}
