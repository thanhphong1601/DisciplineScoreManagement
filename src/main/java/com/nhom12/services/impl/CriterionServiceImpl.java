/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.services.impl;

import com.nhom12.pojo.Criterion;
import com.nhom12.repositories.CriterionRepository;
import com.nhom12.services.CriterionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CriterionServiceImpl implements CriterionService{
    @Autowired
    private CriterionRepository criterionRepo;

    @Override
    public List<Criterion> getList() {
        return this.criterionRepo.getList();
    }
}
