/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.controllers;

import com.nhom12.pojo.Activity;
import com.nhom12.repositories.StatRepository;
import com.nhom12.services.ActivityService;
import com.nhom12.services.DisciplineScoreService;
import com.nhom12.services.FacultyService;
import com.nhom12.services.SchoolEmailService;
import com.nhom12.services.StatService;
import com.nhom12.services.StudentClassService;
import com.nhom12.services.UserService;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private DisciplineScoreService scoreService;
    @Autowired
    private StatService statService;
    @Autowired
    private FacultyService facultyService;

    @RequestMapping("/")
    public String index(Model model,
            @RequestParam Map<String, String> params) {
//        model.addAttribute("classes", this.classService.getClasses());
//        model.addAttribute("mails", this.mailService.getEmails());
//        model.addAttribute("users", this.userService.getUsers(params));
        model.addAttribute("activities", this.activityService.getActivityList(params));
        return "index";

    }
    
    @GetMapping("/activities")
    public String createActivityView(Model model,
            @RequestParam Map<String, String> params){
//        Activity a = new Activity();
//        Date d = new Date();
//        a.setDate(d);
//        System.out.println(this.userService.getUsers(params));
        model.addAttribute("users", this.userService.getUsers(params));
        model.addAttribute("scores", this.scoreService.getScores());
        model.addAttribute("faculties", this.facultyService.getFaculties());
        model.addAttribute("activity", new Activity());
        
        return "activityCreate";
    }
    
    @PostMapping("/activities")
    public String createActivity(Model model, @ModelAttribute(value = "activity") Activity a,
            BindingResult rs){
        
        if(!rs.hasErrors()){
            try {
                this.activityService.addOrUpdate(a);
                
                return "redirect:/";
            } catch (Exception e) {
                model.addAttribute("errMsg", e.toString());
            }
        }
            
        return "index";
    }
    
//    @GetMapping("/test")
//    public String test(Model model){
//        model.addAttribute("stats", this.statService.scoreRevenueByFaculty());
//        return "test";
//    }
}
