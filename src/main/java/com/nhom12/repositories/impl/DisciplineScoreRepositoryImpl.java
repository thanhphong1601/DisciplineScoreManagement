/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.repositories.impl;

import com.nhom12.pojo.Activity;
import com.nhom12.pojo.ActivityRegistration;
import com.nhom12.pojo.DisciplineScore;
import com.nhom12.repositories.DisciplineScoreRepository;
import java.util.ArrayList;
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
public class DisciplineScoreRepositoryImpl implements DisciplineScoreRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<DisciplineScore> getScores() {
        Session s = this.factory.getObject().getCurrentSession();

        Query q = s.createNamedQuery("DisciplineScore.findAll");
        return q.getResultList();

    }

    @Override
    public void addOrUpdate(DisciplineScore score) {
        Session s = this.factory.getObject().getCurrentSession();

        if (score.getId() != null) {
            s.update(score);
        } else {
            s.save(score);
        }
    }

    @Override
    public DisciplineScore getScoreById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("DisciplineScore.findById");
        q.setParameter("id", id);

        return (DisciplineScore) q.getSingleResult();
    }

    @Override
    public List<Object[]> getScoresByStudentId(int studentId, Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
//        Root aRoot = q.from(Activity.class);
//
//        Join<Activity, DisciplineScore> activityScore = aRoot.join("disciplineScoreid", JoinType.INNER);
//        
        Root<ActivityRegistration> activityRegistrationRoot = q.from(ActivityRegistration.class);
        Join<ActivityRegistration, Activity> activityJoin = activityRegistrationRoot.join("activityId");
        Join<Activity, DisciplineScore> disciplineScoreJoin = activityJoin.join("disciplineScoreid");

        q.multiselect(
                disciplineScoreJoin,
                b.sum(disciplineScoreJoin.get("score"))
        );

        List<Predicate> predicates = new ArrayList<>();

        predicates.add(b.equal(activityRegistrationRoot.get("studentId").get("id"), studentId));

        int semester = Integer.parseInt(params.get("semester"));
//        if (semester > 0 && semester != -1)
//            q.where(b.and(b.equal(disciplineScoreJoin.get("semester"), semester), b.equal(activityRegistrationRoot.get("studentId").get("id"), studentId)))
//                    .groupBy(disciplineScoreJoin.get("id"));
//        if (semester == -1)
//            q.where(b.equal(activityRegistrationRoot.get("studentId").get("id"), studentId))
//                .groupBy(disciplineScoreJoin.get("id"));
        if (semester > 0) {
            predicates.add(b.equal(disciplineScoreJoin.get("semester"), semester));
        }

        int fromYear = Integer.parseInt(params.get("fromYear"));
        int toYear = Integer.parseInt(params.get("toYear"));

//        if (fromYear > 0)
//            q.where(b.and(b.greaterThanOrEqualTo(disciplineScoreJoin.get("fromYear"), fromYear), b.equal(activityRegistrationRoot.get("studentId").get("id"), studentId)))
//                    .groupBy(disciplineScoreJoin.get("id"));
        if (fromYear > 0 && toYear > 0) {
            predicates.add(b.and(b.greaterThanOrEqualTo(disciplineScoreJoin.get("fromYear"), fromYear), b.lessThanOrEqualTo(disciplineScoreJoin.get("toYear"), toYear)));
        }

//        
//        if (toYear > 0)
//            q.where(b.and(b.lessThanOrEqualTo(disciplineScoreJoin.get("toYear"), toYear), b.equal(activityRegistrationRoot.get("studentId").get("id"), studentId)))
//                    .groupBy(disciplineScoreJoin.get("id"));
        q.where(predicates.toArray(Predicate[]::new));
        q.groupBy(disciplineScoreJoin.get("id"));

        List<Object[]> results = s.createQuery(q).getResultList();

        return results;
    }

    @Override
    public List<Object[]> getTotalScoreByStudentId(int studentId, Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> qTotal = b.createQuery(Object[].class);

        Root<ActivityRegistration> aRegis = qTotal.from(ActivityRegistration.class);
        Join<ActivityRegistration, Activity> joinedActivity = aRegis.join("activityId");
        Join<Activity, DisciplineScore> joinedScore = joinedActivity.join("disciplineScoreid");

        qTotal.multiselect(
                aRegis.get("studentId"),
                b.sum(joinedScore.get("score"))
        );
//        .where(b.equal(aRegis.get("studentId").get("id"), studentId))
//                .groupBy(aRegis.get("studentId"));
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(b.equal(aRegis.get("studentId").get("id"), studentId));

        int semester = Integer.parseInt(params.get("semester"));

        if (semester > 0) {
            predicates.add(b.equal(joinedScore.get("semester"), semester));
        }

        int fromYear = Integer.parseInt(params.get("fromYear"));
        if (fromYear > 0) {
            predicates.add(b.greaterThanOrEqualTo(joinedScore.get("fromYear"), fromYear));
        }

        int toYear = Integer.parseInt(params.get("toYear"));
        if (toYear > 0) {
            predicates.add(b.lessThanOrEqualTo(joinedScore.get("toYear"), toYear));
        }

        qTotal.where(predicates.toArray(Predicate[]::new));
        qTotal.groupBy(aRegis.get("studentId").get("id"));

        List<Object[]> totalScore = s.createQuery(qTotal).getResultList();

        return totalScore;
    }

}
