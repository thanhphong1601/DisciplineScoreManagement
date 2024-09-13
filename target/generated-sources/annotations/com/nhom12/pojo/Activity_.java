package com.nhom12.pojo;

import com.nhom12.pojo.ActivityRegistration;
import com.nhom12.pojo.Comment;
import com.nhom12.pojo.DisciplineScore;
import com.nhom12.pojo.ExtracurricularAchievement;
import com.nhom12.pojo.Faculty;
import com.nhom12.pojo.MissingActivityReport;
import com.nhom12.pojo.StudentLike;
import com.nhom12.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-06-27T18:31:48")
@StaticMetamodel(Activity.class)
public class Activity_ { 

    public static volatile SingularAttribute<Activity, Date> date;
    public static volatile SingularAttribute<Activity, String> image;
    public static volatile CollectionAttribute<Activity, ExtracurricularAchievement> extracurricularAchievementCollection;
    public static volatile CollectionAttribute<Activity, ActivityRegistration> activityRegistrationCollection;
    public static volatile SingularAttribute<Activity, DisciplineScore> disciplineScoreid;
    public static volatile CollectionAttribute<Activity, Comment> commentCollection;
    public static volatile SingularAttribute<Activity, String> description;
    public static volatile SingularAttribute<Activity, String> title;
    public static volatile SingularAttribute<Activity, Faculty> facultyId;
    public static volatile SingularAttribute<Activity, User> createdBy;
    public static volatile SetAttribute<Activity, StudentLike> studentLikeSet;
    public static volatile SingularAttribute<Activity, Integer> id;
    public static volatile CollectionAttribute<Activity, MissingActivityReport> missingActivityReportCollection;

}