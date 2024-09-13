/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.repositories.impl;

import com.nhom12.pojo.Activity;
import com.nhom12.pojo.Comment;
import com.nhom12.pojo.User;
import com.nhom12.repositories.ActivityRepository;
import com.nhom12.repositories.CommentRepository;
import com.nhom12.repositories.UserRepository;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import static org.eclipse.persistence.expressions.ExpressionOperator.currentDate;
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
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private ActivityRepository aRepo;
    @Autowired
    private UserRepository uRepo;

    @Override
    public void createComment(int creatorId, int activityId, String content) {
        Session s = this.factory.getObject().getCurrentSession();
        
        
        Comment c = new Comment();
        c.setContent(content);
        c.setActivityId(this.aRepo.getActivityById(activityId));
        c.setStudentId(this.uRepo.getUserById(creatorId));
        c.setCreatedDate(new Date());
        
        s.save(c);
    }

    @Override
    public List<Comment> getComments(int activityId) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Comment> q = b.createQuery(Comment.class);
        Root r = q.from(Comment.class);
        
        q.select(r).where(b.equal(r.get("activityId"), activityId)).orderBy(b.desc(r.get("createdDate")));
        Query query = s.createQuery(q);
        
        return query.getResultList();
        
        
    }

}
