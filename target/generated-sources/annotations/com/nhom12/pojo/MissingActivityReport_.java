package com.nhom12.pojo;

import com.nhom12.pojo.Activity;
import com.nhom12.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-06-27T18:31:48")
@StaticMetamodel(MissingActivityReport.class)
public class MissingActivityReport_ { 

    public static volatile SingularAttribute<MissingActivityReport, User> studentId;
    public static volatile SingularAttribute<MissingActivityReport, Activity> activityId;
    public static volatile SingularAttribute<MissingActivityReport, Date> registeredDate;
    public static volatile SingularAttribute<MissingActivityReport, String> proof;
    public static volatile SingularAttribute<MissingActivityReport, Integer> id;
    public static volatile SingularAttribute<MissingActivityReport, String> status;

}