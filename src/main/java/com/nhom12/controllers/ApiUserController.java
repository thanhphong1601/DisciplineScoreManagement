/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.controllers;

import com.nhom12.components.JwtService;
import com.nhom12.pojo.Faculty;
import com.nhom12.pojo.StudentClass;
import com.nhom12.pojo.User;
import com.nhom12.repositories.RoleRepository;
import com.nhom12.services.FacultyService;
import com.nhom12.services.SchoolEmailService;
import com.nhom12.services.StudentClassService;
import com.nhom12.services.UserService;
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class ApiUserController {

    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private BCryptPasswordEncoder passEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private SchoolEmailService mailService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private FacultyService facultyService;
    @Autowired
    private StudentClassService classService;

    @PostMapping(path = "/users/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<Object> createUser(@RequestParam Map<String, String> params, @RequestPart MultipartFile[] file) {
        User u = new User();
        u.setName(params.get("name"));
        u.setUsername(params.get("username"));
        u.setRoleId(this.roleRepo.getAdminRole());
        u.setEmail(params.get("email"));
        String password = params.get("password");
        u.setPassword(password);
        
        int fId = Integer.parseInt(params.get("facultyId"));
        int cId = Integer.parseInt(params.get("classId"));
        Faculty f = this.facultyService.getFacultyById(fId);
        StudentClass c = this.classService.getClassById(cId);
        
        u.setFacultyId(f);
        u.setClassId(c);
                
        if (!this.mailService.checkEmail(u.getEmail())) {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }

        if (file.length > 0) {
            u.setFile(file[0]);
        }
        
        this.userService.addOrUpdateUser(u);
        return new ResponseEntity<>("Successful", HttpStatus.CREATED);
    }

    @PostMapping("/login/")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody User u) {
        if (this.userService.authUser(u.getUsername(), u.getPassword()) == true) {
            String token = this.jwtService.generateTokenLogin(u.getUsername());

            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("Đã có lỗi xảy ra", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/current-user/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<User> getCurrentUser(Principal p) {
        User u = this.userService.getUserByUsername(p.getName());

        return new ResponseEntity<>(u, HttpStatus.OK);
    }
}
