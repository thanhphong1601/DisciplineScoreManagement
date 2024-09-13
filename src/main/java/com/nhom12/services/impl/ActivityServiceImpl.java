/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nhom12.pojo.Activity;
import com.nhom12.repositories.ActivityRepository;
import com.nhom12.services.ActivityService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ActivityServiceImpl implements ActivityService{
    @Autowired
    private ActivityRepository activityRepo;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Activity> getActivityList(Map<String, String> params) {
        return this.activityRepo.getActivityList(params);
    }

    @Override
    public void addOrUpdate(Activity a) {
        if (!a.getFile().isEmpty() && a.getImage() == null){
            try {
                Map res = this.cloudinary.uploader().upload(a.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                a.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(ActivityServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      
        this.activityRepo.addOrUpdate(a);
    }

    @Override
    public Activity getActivityById(int id) {
        return this.activityRepo.getActivityById(id);
    }

}
