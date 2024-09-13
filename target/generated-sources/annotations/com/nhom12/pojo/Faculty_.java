package com.nhom12.pojo;

import com.nhom12.pojo.Activity;
import com.nhom12.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-06-27T18:31:48")
@StaticMetamodel(Faculty.class)
public class Faculty_ { 

    public static volatile SetAttribute<Faculty, Activity> activitySet;
    public static volatile CollectionAttribute<Faculty, User> userCollection;
    public static volatile SingularAttribute<Faculty, String> name;
    public static volatile SingularAttribute<Faculty, Integer> id;

}