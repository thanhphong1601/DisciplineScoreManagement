package com.nhom12.pojo;

import com.nhom12.pojo.Activity;
import com.nhom12.pojo.ActivityRegistration;
import com.nhom12.pojo.Comment;
import com.nhom12.pojo.ExtracurricularAchievement;
import com.nhom12.pojo.Faculty;
import com.nhom12.pojo.MissingActivityReport;
import com.nhom12.pojo.Role;
import com.nhom12.pojo.StudentClass;
import com.nhom12.pojo.StudentLike;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-06-27T18:31:48")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile CollectionAttribute<User, ExtracurricularAchievement> extracurricularAchievementCollection;
    public static volatile CollectionAttribute<User, ActivityRegistration> activityRegistrationCollection;
    public static volatile SingularAttribute<User, Role> roleId;
    public static volatile CollectionAttribute<User, Comment> commentCollection;
    public static volatile SingularAttribute<User, Boolean> active;
    public static volatile SingularAttribute<User, String> avatar;
    public static volatile CollectionAttribute<User, Activity> activityCollection;
    public static volatile SingularAttribute<User, Faculty> facultyId;
    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, StudentClass> classId;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SetAttribute<User, StudentLike> studentLikeSet;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile SingularAttribute<User, String> email;
    public static volatile SingularAttribute<User, String> username;
    public static volatile CollectionAttribute<User, MissingActivityReport> missingActivityReportCollection;

}