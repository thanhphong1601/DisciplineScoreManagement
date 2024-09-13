/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.controllers;

import com.nhom12.pojo.Faculty;
import com.nhom12.services.FacultyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiFacultyController {
    @Autowired
    private FacultyService fService;
    
    @GetMapping("/faculties/")
    public ResponseEntity<List<Faculty>> loadFaculty(){
        return new ResponseEntity<>(this.fService.getFaculties(), HttpStatus.OK);
    }
}
