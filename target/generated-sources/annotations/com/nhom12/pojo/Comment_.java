package com.nhom12.pojo;

import com.nhom12.pojo.Activity;
import com.nhom12.pojo.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-06-27T18:31:48")
@StaticMetamodel(Comment.class)
public class Comment_ { 

    public static volatile SingularAttribute<Comment, User> studentId;
    public static volatile SingularAttribute<Comment, Activity> activityId;
    public static volatile SingularAttribute<Comment, Date> createdDate;
    public static volatile SingularAttribute<Comment, Integer> id;
    public static volatile SingularAttribute<Comment, String> content;

}