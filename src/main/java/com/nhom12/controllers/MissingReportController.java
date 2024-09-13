/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.controllers;

import com.nhom12.services.FacultyService;
import com.nhom12.services.MissingReportService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
public class MissingReportController {
    @Autowired
    private MissingReportService missingReportService;
    @Autowired
    private FacultyService facultyService;
    
    @RequestMapping("/reports")
    public String reportPage(Model model, @RequestParam Map<String, String> params){
        int facultyId = 0;
        if(!params.isEmpty()){
            facultyId = Integer.parseInt(params.get("facultyId"));
        }
        model.addAttribute("reports", this.missingReportService.getMissingReport(facultyId));
        model.addAttribute("faculties", this.facultyService.getFaculties());
        return "missingReport";
    }
}
