/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.controllers;

import com.nhom12.services.StatService;

import java.io.IOException;


import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Admin
 */
@Controller
public class StatController {

    @Autowired
    private StatService statService;

    @GetMapping("/stats/faculty")
    public String facultyStatPage(Model model) {
//        System.out.println(this.statService.scoreRevenueByFaculty());
        model.addAttribute("stats", this.statService.scoreRevenueByFaculty());

        return "stat";
    }

    @GetMapping("/stats/class")
    public String classStatPage(Model model) {
        model.addAttribute("stats", this.statService.scoreRevenueByClass());
        return "statClass";
    }

    @GetMapping("/stats/faculty/{type-id}/pdf")
    @ResponseBody
    public void pdfOutput(HttpServletResponse response, @PathVariable (value = "type-id") int typeId) throws IOException {
        this.statService.pdfOutput(response, typeId);
    }
    
    @GetMapping("/stats/class/{type-id}/pdf")
    @ResponseBody
    public void pdfOutputClass(HttpServletResponse response, @PathVariable (value = "type-id") int typeId) throws IOException {
        this.statService.pdfOutput(response, typeId);
    }
}
