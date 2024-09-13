/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.controllers;

import com.nhom12.pojo.User;
import com.nhom12.services.FacultyService;
import com.nhom12.services.RoleService;
import com.nhom12.services.StudentClassService;
import com.nhom12.services.UserService;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private StudentClassService classService;
    @Autowired
    private FacultyService facultyService;
    

    @ModelAttribute
    public void commonAttributes(Model model) {
        model.addAttribute("roles", this.roleService.getRoles());
        model.addAttribute("classes", this.classService.getClasses());
        model.addAttribute("faculties", this.facultyService.getFaculties());

    }

    @RequestMapping("/users")
    public String index(Model model,
            @RequestParam Map<String, String> params) {
//        model.addAttribute("classes", this.classService.getClasses());
//        model.addAttribute("mails", this.mailService.getEmails());
//        model.addAttribute("users", this.userService.getUsers(params));
        model.addAttribute("users", this.userService.getUsers(params));
        return "user";
    }

    @GetMapping("/users/add")
    public String createUserView(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @GetMapping("users/{userId}")
    public String updateUser(@PathVariable(value = "userId") int id, Model model) {
        model.addAttribute("user", this.userService.getUserById(id));
        return "add-user";
    }

    @PostMapping("/users/add")
    public String addUser(Model model, @ModelAttribute(value = "user") @Valid User u,
            BindingResult rs) {
        
        if (!rs.hasErrors()) {
            try {
                this.userService.addOrUpdateUser(u);
                
                return "redirect:/users";
            } catch (Exception e) {
                model.addAttribute("errMsg", e.toString());
            }
        }
        return "add-user";

    }
}
