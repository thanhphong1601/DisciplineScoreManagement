package com.nhom12.pojo;

import com.nhom12.pojo.Activity;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-06-27T18:31:48")
@StaticMetamodel(DisciplineScore.class)
public class DisciplineScore_ { 

    public static volatile SingularAttribute<DisciplineScore, Integer> toYear;
    public static volatile SingularAttribute<DisciplineScore, Integer> score;
    public static volatile SingularAttribute<DisciplineScore, String> criterion;
    public static volatile SingularAttribute<DisciplineScore, Integer> fromYear;
    public static volatile SingularAttribute<DisciplineScore, Integer> semester;
    public static volatile SingularAttribute<DisciplineScore, Integer> id;
    public static volatile CollectionAttribute<DisciplineScore, Activity> activityCollection;

}