/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.services.impl;

import com.cloudinary.Cloudinary;
import com.nhom12.pojo.DisciplineScore;
import com.nhom12.repositories.DisciplineScoreRepository;
import com.nhom12.services.DisciplineScoreService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class DisciplineScoreServiceImpl implements DisciplineScoreService{
    @Autowired
    private DisciplineScoreRepository scoreRepo;

    
    @Override
    public List<DisciplineScore> getScores() {
        return this.scoreRepo.getScores();
    }

    @Override
    public void addOrUpdate(DisciplineScore score) {
        this.scoreRepo.addOrUpdate(score);
    }

    @Override
    public DisciplineScore getScoreById(int id) {
        return this.scoreRepo.getScoreById(id);
    }

    @Override
    public List<Object[]> getScoresByStudentId(int studentId, Map<String, String> params) {
        return this.scoreRepo.getScoresByStudentId(studentId, params);
    }

    @Override
    public List<Object[]> getTotalScoreByStudentId(int studentId, Map<String, String> params) {
        return this.scoreRepo.getTotalScoreByStudentId(studentId, params);
    }

    
    
}
