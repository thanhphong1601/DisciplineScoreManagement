/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.services.impl;

import com.nhom12.pojo.StudentClass;
import com.nhom12.repositories.StudentClassRepository;
import com.nhom12.services.StudentClassService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class StudentClassServiceImpl implements StudentClassService{
    @Autowired
    private StudentClassRepository classRepo;

    @Override
    public List<StudentClass> getClasses() {
        return this.classRepo.getClasses();
    }

    @Override
    public Long countClass() {
        return this.classRepo.countClass();
    }

    @Override
    public StudentClass getClassById(int classId) {
        return this.classRepo.getClassById(classId);
    }
    
}
