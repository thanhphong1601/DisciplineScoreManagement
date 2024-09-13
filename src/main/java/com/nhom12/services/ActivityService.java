/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.services;

import com.nhom12.pojo.Activity;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface ActivityService {
    List<Activity> getActivityList(Map<String, String> params);
    void addOrUpdate(Activity a);
    Activity getActivityById(int id);
}
