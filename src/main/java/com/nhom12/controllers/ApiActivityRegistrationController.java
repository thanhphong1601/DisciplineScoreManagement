/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.controllers;

import com.nhom12.pojo.Activity;
import com.nhom12.pojo.ActivityRegistration;
import com.nhom12.pojo.User;
import com.nhom12.services.ActivityRegistrationService;
import com.nhom12.services.ActivityService;
import com.nhom12.services.UserService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.persistence.PostRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiActivityRegistrationController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityRegistrationService regisService;
    
    @PostMapping(path = "/activities/{activityId}/registration/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<Object> createRegistration(@RequestParam Map<String, String> params, @RequestPart MultipartFile[] file){
        ActivityRegistration registration = new ActivityRegistration();
        Activity a = this.activityService.getActivityById(Integer.parseInt(params.get("activityId")));
        User u = this.userService.getUserById(Integer.parseInt(params.get("studentId")));
        
        registration.setActivityId(a);
        registration.setStudentId(u);
        
        if (file.length > 0) {
            registration.setFile(file[0]);
        }

        
        this.regisService.addRegistration(registration);
//        System.out.println(Integer.parseInt(params.get("activityId")));
//        System.out.println(Integer.parseInt(params.get("studentId")));
//        System.out.println(a.getId());
//        System.out.println(u.getName());
//        System.out.println(file.length > 0);


        return new ResponseEntity<>("Hello", HttpStatus.OK);
    } 
    
    @GetMapping(path = "/activities/joined/")
    public ResponseEntity<List<ActivityRegistration>> getJoinedList(@RequestParam Map<String, String> params){
        List<ActivityRegistration> a = this.regisService.getJoinedActivityList(params);
        
        return new ResponseEntity<>(a, HttpStatus.OK);
    }
    
    @PostMapping("/activities/upload-csv/")
    public ResponseEntity<String> uploadCsv(@RequestPart MultipartFile file, @RequestParam("activityId") int activityId) {
        try {
            regisService.loadCsv(file, activityId);
            return ResponseEntity.ok("Upload Sucessfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some errors occur");
        }
    }
}
