/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.controllers;

import com.nhom12.pojo.Activity;
import com.nhom12.pojo.DisciplineScore;
import com.nhom12.pojo.User;
import com.nhom12.services.DisciplineScoreService;
import java.util.List;
import java.util.Map;
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
public class ApiScoreController {
    @Autowired
    private DisciplineScoreService scoreService;

    
    @GetMapping("/scores/details/")
    public ResponseEntity<Object> loadScore(@RequestParam Map<String, String> params){
        int studentId = Integer.parseInt(params.get("studentId"));
        
        if (studentId == -1){
            return new ResponseEntity<>(this.scoreService.getScores(), HttpStatus.OK); 
        }
        
        return new ResponseEntity<>(this.scoreService.getScoresByStudentId(studentId, params), HttpStatus.OK);
    }
    
    @GetMapping("/scores/total/")
    public ResponseEntity<Object> loadTotal(@RequestParam Map<String, String> params){
        int studentId = Integer.parseInt(params.get("studentId"));
        
        List<Object[]> totalScore = this.scoreService.getTotalScoreByStudentId(studentId, params);
        if (totalScore == null)
               return new ResponseEntity<>("No matched result", HttpStatus.OK);

        return new ResponseEntity<>(this.scoreService.getTotalScoreByStudentId(studentId, params), HttpStatus.OK);
        
            
    }
 
}
