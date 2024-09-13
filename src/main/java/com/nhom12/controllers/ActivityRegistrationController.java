/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.controllers;

import com.nhom12.services.ActivityRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Admin
 */
@Controller
public class ActivityRegistrationController {
    @Autowired
    private ActivityRegistrationService joinedActivityService;
    
    @GetMapping("/activities/attending")
    public String activityRegistrationPage(Model model){
        model.addAttribute("joinedActivities", this.joinedActivityService.getJoinedList());
        return "activityPage";
    }
}
