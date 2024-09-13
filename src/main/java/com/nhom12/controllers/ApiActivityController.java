/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.controllers;

import com.nhom12.pojo.Activity;
import com.nhom12.pojo.User;
import com.nhom12.services.ActivityService;
import com.nhom12.services.DisciplineScoreService;
import com.nhom12.services.FacultyService;
import com.nhom12.services.UserService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class ApiActivityController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private DisciplineScoreService scoreService;
    
    @GetMapping("/activities/")
    public ResponseEntity<List<Activity>> list(@RequestParam Map<String, String> params) {
//        if (params.isEmpty()){
//            params.put("kw", null);
//        }
        return new ResponseEntity<>(this.activityService.getActivityList(params), HttpStatus.OK);
    }
    
    @GetMapping(path = "/activities/{activityId}/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Activity> getActivityById(@PathVariable (value = "activityId") int id){
        Activity a = this.activityService.getActivityById(id);
        return new ResponseEntity<>(a, HttpStatus.OK);
    }
    
    @PostMapping(path = "/activities/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<Activity> createActivity(@RequestParam Map<String, String> params, @RequestPart MultipartFile[] file){
        Activity a = new Activity();
        a.setCreatedBy(this.userService.getUserById(Integer.parseInt(params.get("userId"))));
        a.setFacultyId(this.facultyService.getFacultyById(Integer.parseInt(params.get("facultyId"))));
        a.setDisciplineScoreid(this.scoreService.getScoreById(Integer.parseInt(params.get("disciplineScoreId"))));
        a.setDescription(params.get("description"));
        a.setTitle(params.get("title"));
        
        if (file.length > 0) {
            a.setFile(file[0]);
        }
        
        this.activityService.addOrUpdate(a);
        
        
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
    
    
}
