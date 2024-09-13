/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.services.impl;

import com.nhom12.pojo.SchoolEmail;
import com.nhom12.repositories.SchoolEmailRepository;
import com.nhom12.services.SchoolEmailService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class SchoolEmailServiceImpl implements SchoolEmailService{
    @Autowired
    private SchoolEmailRepository mailRepo;
    
    @Override
    public List<SchoolEmail> getEmails() {
        return this.mailRepo.getEmails();
    }

    @Override
    public boolean checkEmail(String email) {
        return this.mailRepo.checkEmail(email);
    }
    
}
