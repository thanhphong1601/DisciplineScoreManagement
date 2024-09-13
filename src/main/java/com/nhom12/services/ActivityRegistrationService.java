/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.services;

import com.nhom12.pojo.ActivityRegistration;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
public interface ActivityRegistrationService {
    List<Object[]> getJoinedList();
    void addRegistration(ActivityRegistration a);
    List<ActivityRegistration> getJoinedActivityList(Map<String, String> params);
    void loadCsv(MultipartFile file, int activityId) throws IOException;
    void save(ActivityRegistration a);
}
