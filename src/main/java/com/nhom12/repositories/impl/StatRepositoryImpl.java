/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.repositories.impl;

import com.nhom12.pojo.Activity;
import com.nhom12.pojo.ActivityRegistration;
import com.nhom12.pojo.DisciplineScore;
import com.nhom12.pojo.Faculty;
import com.nhom12.pojo.StudentClass;
import com.nhom12.pojo.User;
import com.nhom12.repositories.StatRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
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
public class StatRepositoryImpl implements StatRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object[]> scoreRevenueByFaculty() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();

//        CriteriaQuery<Object[]> qFacultyActivities = b.createQuery(Object[].class);
//        Root activityRoot = qFacultyActivities.from(Activity.class);
//        Root fR = qFacultyActivities.from(Faculty.class);
//        qFacultyActivities.multiselect(fR.get("id"), fR.get("name"), b.count(activityRoot))
//                .where(b.equal(activityRoot.get("facultyId"), fR.get("id"))).groupBy(fR.get("id"));
//        List<Object[]> facultyActivities = s.createQuery(qFacultyActivities).getResultList();
//        
//        CriteriaQuery<ActivityRegistration> qFacultyActivityRegis = b.createQuery(ActivityRegistration.class);
//        List<Predicate> predicates = new ArrayList<>();
//      
//        q.where(predicates.toArray(Predicate[]::new));
//        Query<Object[]> query = s.createQuery(q);
        CriteriaQuery<Faculty> facultyQuery = b.createQuery(Faculty.class);
        Root facultyRoot = facultyQuery.from(Faculty.class);
        facultyQuery.select(facultyRoot);
        List<Faculty> faculties = s.createQuery(facultyQuery).getResultList();

        List<Object[]> result = new ArrayList<>();

        for (Faculty f : faculties) {
            int facultyId = f.getId();
            String facultyName = f.getName();

            //number of activity
            CriteriaQuery<Long> activityQuery = b.createQuery(Long.class);
            Root activityRoot = activityQuery.from(Activity.class);
            activityQuery.select(b.count(activityRoot.get("id")))
                    .where(b.equal(activityRoot.get("facultyId"), facultyId));
            Long activities = s.createQuery(activityQuery).getSingleResult();

            //number of registration/student
            CriteriaQuery<Long> registrationQuery = b.createQuery(Long.class);
            Root registrationRoot = registrationQuery.from(ActivityRegistration.class);
            Join<ActivityRegistration, Activity> joinedActivity = registrationRoot.join("activityId", JoinType.INNER);
            registrationQuery.select(b.count(registrationRoot.get("studentId")))
                    .where(b.equal(joinedActivity.get("facultyId"), facultyId));
            Long joinedStudent = s.createQuery(registrationQuery).getSingleResult();

            //the most attended activity
            CriteriaQuery<Object[]> mostActivityQuery = b.createQuery(Object[].class);
            Root<ActivityRegistration> maxActivityRegRoot = mostActivityQuery.from(ActivityRegistration.class);
            Join<ActivityRegistration, Activity> mostJoinedActivity = maxActivityRegRoot.join("activityId", JoinType.INNER);
            mostActivityQuery.multiselect(mostJoinedActivity.get("id"), mostJoinedActivity.get("title"), b.count(maxActivityRegRoot.get("id")).alias("studentCount"))
                    .groupBy(mostJoinedActivity.get("id"))
                    .orderBy(b.desc(b.count(maxActivityRegRoot.get("id")))) //1st place for most joined
                    .where(b.equal(mostJoinedActivity.get("facultyId"), facultyId));
            List<Object[]> mostActivityResult = s.createQuery(mostActivityQuery).setMaxResults(1).getResultList(); //just take 1

            result.add(new Object[]{
                facultyId,
                facultyName,
                activities,
                joinedStudent,
                mostActivityResult.isEmpty() ? null : mostActivityResult.get(0)
            });
        }

        return result;

    }

    @Override
    public List<Object[]> scoreRevenueByClass() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();

        CriteriaQuery<StudentClass> studentClassQuery = b.createQuery(StudentClass.class);
        Root classRoot = studentClassQuery.from(StudentClass.class);
        studentClassQuery.select(classRoot);
        List<StudentClass> classes = s.createQuery(studentClassQuery).getResultList();

        List<Object[]> result = new ArrayList<>();

        for (StudentClass c : classes) {
            int classId = c.getId();
            String className = c.getName();

            //count student
            CriteriaQuery<Long> studentQuery = b.createQuery(Long.class);
            Root studentRoot = studentQuery.from(User.class);
            studentQuery.multiselect(b.count(studentRoot.get("id")))
                    .where(b.equal(studentRoot.get("classId"), classId));
            Long studentCount = s.createQuery(studentQuery).getSingleResult();

            //number of student joining activities
            CriteriaQuery<Long> activityRegisQuery = b.createQuery(Long.class);
            Root activityRegisRoot = activityRegisQuery.from(ActivityRegistration.class);
            Join<ActivityRegistration, User> joinedStudent = activityRegisRoot.join("studentId", JoinType.INNER);
            activityRegisQuery.select(b.countDistinct(joinedStudent.get("id")))
                    .where(b.equal(joinedStudent.get("classId"), classId));
            Long joinedStudentCount = s.createQuery(activityRegisQuery).getSingleResult();

            //most joining student
            CriteriaQuery<Object[]> mostStudentQuery = b.createQuery(Object[].class);
            Root activityRegisR = mostStudentQuery.from(ActivityRegistration.class);
            Join<ActivityRegistration, User> users = activityRegisR.join("studentId", JoinType.INNER);

            mostStudentQuery.multiselect(users.get("id"), users.get("name"), b.count(activityRegisR.get("id")))
                    .where(b.equal(users.get("classId"), classId), b.equal(users.get("id"), activityRegisR.get("studentId")))
                    .groupBy(users.get("id"))
                    .orderBy(b.desc(b.count(activityRegisR.get("id"))));
            List<Object[]> mostStudentResult = s.createQuery(mostStudentQuery).setMaxResults(1).getResultList(); //just take 1

            //least joining student
            mostStudentQuery.multiselect(users.get("id"), users.get("name"), b.count(activityRegisR.get("id")))
                    .where(b.equal(users.get("classId"), classId), b.equal(users.get("id"), activityRegisR.get("studentId")))
                    .groupBy(users.get("id"))
                    .orderBy(b.asc(b.count(activityRegisR.get("id"))));
            List<Object[]> leastStudentResult = s.createQuery(mostStudentQuery).setMaxResults(1).getResultList(); //just take 1

            //no joining
            Long noJoinStudentCount = studentCount - joinedStudentCount;

            //most attended activity
            CriteriaQuery<Object[]> mostActivityQuery = b.createQuery(Object[].class);
            Root<ActivityRegistration> mostActivityRoot = mostActivityQuery.from(ActivityRegistration.class);
            Join<ActivityRegistration, Activity> mostJoinedActivity = mostActivityRoot.join("activityId", JoinType.INNER);
            mostActivityQuery.multiselect(mostJoinedActivity.get("id"), mostJoinedActivity.get("title"), b.count(mostActivityRoot.get("id")).alias("studentCount"))
                    .groupBy(mostJoinedActivity.get("id"))
                    .orderBy(b.desc(b.count(mostActivityRoot.get("id")))) //1st place for most joined
                    .where(b.equal(mostActivityRoot.get("studentId").get("classId"), classId));
            List<Object[]> mostActivityResult = s.createQuery(mostActivityQuery).setMaxResults(1).getResultList(); //just take 1

            //student with most score
            //lấy ds các đăng ký
            CriteriaQuery<Object[]> scoreQuery = b.createQuery(Object[].class);
            Root arRoot = scoreQuery.from(ActivityRegistration.class);

            Join<ActivityRegistration, User> joinedUser = arRoot.join("studentId", JoinType.INNER);
            Join<ActivityRegistration, Activity> joinedActivity = arRoot.join("activityId", JoinType.INNER);
            Join<Activity, DisciplineScore> scoreJoin = joinedActivity.join("disciplineScoreid", JoinType.INNER);

            scoreQuery.multiselect(joinedUser.get("id"), joinedUser.get("name"), b.sum(scoreJoin.get("score")))
                    .where(b.equal(joinedUser.get("classId"), classId), b.equal(arRoot.get("studentId"), joinedUser.get("id")), b.equal(scoreJoin.get("id"), joinedActivity.get("disciplineScoreid")))
                    .groupBy(joinedUser.get("id"))
                    .orderBy(b.desc(b.sum(scoreJoin.get("score"))));

            List<Object[]> userWithHighestScore = s.createQuery(scoreQuery).setMaxResults(1).getResultList();

            result.add(new Object[]{
                classId,
                className,
                studentCount,
                joinedStudentCount,
                mostStudentResult.isEmpty() ? null : mostStudentResult.get(0),
                leastStudentResult.isEmpty() ? null : leastStudentResult.get(0),
                mostActivityResult.isEmpty() ? null : mostActivityResult.get(0),
                noJoinStudentCount,
                userWithHighestScore.isEmpty() ? null : userWithHighestScore.get(0),
            }
            );
        }

        return result;
    }

}
