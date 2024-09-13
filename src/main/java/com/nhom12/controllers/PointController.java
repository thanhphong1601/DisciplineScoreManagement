/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.controllers;

import com.nhom12.pojo.DisciplineScore;
import com.nhom12.services.DisciplineScoreService;
import javax.validation.Valid;
import static jdk.vm.ci.meta.JavaKind.Int;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Admin
 */
@Controller
public class PointController {
    @Autowired
    private DisciplineScoreService scoreService;
    
    @GetMapping("/points")
    public String index(Model model){        
        model.addAttribute("s", new DisciplineScore());
        
        return "pointCreate";
    }
    
    @PostMapping("/points")
    public String createPoint(Model model, @ModelAttribute(value = "s") @Valid DisciplineScore s
            , BindingResult rs){
        if (!rs.hasErrors()){
            try {
                this.scoreService.addOrUpdate(s);
                
                return "index";
            } catch (Exception e) {
                model.addAttribute("errMsg", e.toString());
            }   
        }
            
        return "pointCreate";
    }
    
    @GetMapping("/points/list")
    public String scoresView(Model model){
        model.addAttribute("scores", this.scoreService.getScores());
        return "scores";
    }
}
