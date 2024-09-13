/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.controllers;

import com.nhom12.pojo.Activity;
import com.nhom12.pojo.MissingActivityReport;
import com.nhom12.pojo.User;
import com.nhom12.services.ActivityService;
import com.nhom12.services.MissingReportService;
import com.nhom12.services.UserService;
import java.util.List;
import java.util.Map;
import javax.ws.rs.PUT;
import org.hibernate.Session;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiMissingReportController {

    @Autowired
    private MissingReportService reportService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private UserService userService;

    @PostMapping("/reports/{reportId}/")
    public ResponseEntity<Object> confirmReport(Model model, @PathVariable(value = "reportId") int id) {
//        this.reportService.confirmReport(id);
        MissingActivityReport r = this.reportService.getMissingReportById(id);   
        this.reportService.confirmReport(r);
        
        return ResponseEntity.status(HttpStatus.OK).body("Done");
    }

    @DeleteMapping("/reports/{reportId}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReport(Model model, @PathVariable(value = "reportId") int id) {
        this.reportService.deleteReport(id);
    }
    
    
    @PostMapping(path = "/reports/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<MissingActivityReport> createReport(@RequestParam Map<String, String> params,  @RequestPart MultipartFile[] file){
        MissingActivityReport r = new MissingActivityReport();
        
        int studentId = Integer.parseInt(params.get("studentId"));
        int activityId = Integer.parseInt(params.get("activityId"));
        
        User u = this.userService.getUserById(studentId);
        Activity a = this.activityService.getActivityById(activityId);
        
        r.setStudentId(u);
        r.setActivityId(a);
        
        if (file.length > 0) {
            r.setFile(file[0]);
        }
        
        this.reportService.addReport(r);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
    
    @GetMapping(path = "/reports/")
    public ResponseEntity<List<MissingActivityReport>> listReport(@RequestParam Map<String, String> params){
        String facultyId = params.get("facultyId");
        if (facultyId.equals(""))
            facultyId = "0";
        
        int id = Integer.parseInt(facultyId);
        
        return new ResponseEntity<>(this.reportService.getMissingReport(id), HttpStatus.OK);
                
    }
}
