/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.repositories;

import com.nhom12.pojo.Activity;
import java.util.List;
import java.util.Map;
import sun.jvm.hotspot.oops.ObjArray;

/**
 *
 * @author Admin
 */
public interface ActivityRepository {
    List<Activity> getActivityList(Map<String, String> params);
    void addOrUpdate(Activity a);
    Long countActivity();
    Activity getActivityById(int id);
    
}
