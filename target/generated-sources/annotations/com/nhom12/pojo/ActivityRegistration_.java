package com.nhom12.pojo;

import com.nhom12.pojo.Activity;
import com.nhom12.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-06-27T18:31:48")
@StaticMetamodel(ActivityRegistration.class)
public class ActivityRegistration_ { 

    public static volatile SingularAttribute<ActivityRegistration, User> studentId;
    public static volatile SingularAttribute<ActivityRegistration, Activity> activityId;
    public static volatile SingularAttribute<ActivityRegistration, Date> registeredDate;
    public static volatile SingularAttribute<ActivityRegistration, Integer> id;
    public static volatile SingularAttribute<ActivityRegistration, String> proof;

}