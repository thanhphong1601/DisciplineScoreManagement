/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.repositories;

import com.nhom12.pojo.Activity;
import com.nhom12.pojo.ActivityRegistration;
import com.nhom12.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface ActivityRegistrationRepository {
    List<Object[]> getJoinedList();
    void addRegistration(ActivityRegistration a);
    List<ActivityRegistration> getJoinedActivityList(Map<String, String> params);
    void save(ActivityRegistration a);
}
