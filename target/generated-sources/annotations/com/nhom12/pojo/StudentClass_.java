package com.nhom12.pojo;

import com.nhom12.pojo.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-06-27T18:31:48")
@StaticMetamodel(StudentClass.class)
public class StudentClass_ { 

    public static volatile SingularAttribute<StudentClass, Integer> quantity;
    public static volatile CollectionAttribute<StudentClass, User> userCollection;
    public static volatile SingularAttribute<StudentClass, String> name;
    public static volatile SingularAttribute<StudentClass, Integer> id;

}