/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.repositories.impl;

import com.nhom12.pojo.User;
import com.nhom12.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public List<User> getUsers(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
//        Query q = s.createNamedQuery("User.findAll");
//        return q.getResultList();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root r = q.from(User.class);
        q.select(r);

        List<Predicate> predicates = new ArrayList<>();

        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()) {
            predicates.add(b.like(r.get("name"), String.format("%%%s%%", kw)));
        }

        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.asc(r.get("id")));

        Query query = s.createQuery(q);

        List<User> users = query.getResultList();

        return users;
    }

    @Override
    public void addOrUpdateUser(User user) {
        Session s = this.factory.getObject().getCurrentSession();

        user.setPassword(this.passEncoder.encode(user.getPassword()));
        //if the object is already there -> update it
        if (user.getId() != null) {
            s.update(user);
        } //if it isnt, meaning that it's new object -> create new
        else {
            user.setActive(true);
            s.save(user);
        }

    }

    @Override
    public User getUserById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(User.class, id);
    }

    @Override
    public User getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("User.findByUsername");
        q.setParameter("username", username);

        return (User) q.getSingleResult();
    }

    @Override
    public Long countStudent() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Long> query = b.createQuery(Long.class);
        Root r = query.from(User.class);
        query.select(b.count(r)).where(b.equal(r.get("roleId"), 1));

        Long count = s.createQuery(query).getSingleResult();
        return count;
    }

    @Override
    public boolean authUser(String username, String password) {
        User u = this.getUserByUsername(username);

        return this.passEncoder.matches(password, u.getPassword());
    }

    @Override
    public User getUserByEmail(String email, String username) {
        User u = this.getUserByUsername(username);
        if (u.getEmail().equals(email))
            return u;
        return null;
    }

}
