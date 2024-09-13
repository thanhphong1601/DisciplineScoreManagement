/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.controllers;

import com.nhom12.pojo.User;
import com.nhom12.services.SchoolEmailService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiEmailController {
    @Autowired
    private SchoolEmailService mailService;
    
    @GetMapping("/mail-check/")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> checkMail(@RequestBody String email){
        if (this.mailService.checkEmail(email))
            return new ResponseEntity<>("True", HttpStatus.OK);
        
        return new ResponseEntity<>("False", HttpStatus.OK);
    }
}
