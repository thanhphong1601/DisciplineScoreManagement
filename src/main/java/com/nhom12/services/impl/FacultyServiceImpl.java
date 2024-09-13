/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.services.impl;

import com.nhom12.pojo.Faculty;
import com.nhom12.repositories.FacultyRepository;
import com.nhom12.services.FacultyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class FacultyServiceImpl implements FacultyService{
    @Autowired
    private FacultyRepository facultyRepo;
    
    @Override
    public List<Faculty> getFaculties() {
        return this.facultyRepo.getFaculties();
    }

    @Override
    public Long countFaculty() {
        return this.facultyRepo.countFaculty();
    }

    @Override
    public Faculty getFacultyById(int id) {
        return this.facultyRepo.getFacultyById(id);
    }
    
}
