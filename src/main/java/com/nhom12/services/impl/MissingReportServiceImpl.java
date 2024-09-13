/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nhom12.pojo.MissingActivityReport;
import com.nhom12.repositories.MissingReportRepository;
import com.nhom12.services.MissingReportService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class MissingReportServiceImpl implements MissingReportService{
    @Autowired
    private MissingReportRepository missingReportRepo;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<MissingActivityReport> getMissingReport(int facultyId) {
        return this.missingReportRepo.getMissingReport(facultyId);
    }

    @Override
    public void confirmReport(MissingActivityReport r) {
        this.missingReportRepo.confirmReport(r);
    }

    @Override
    public void deleteReport(int reportId) {
        this.missingReportRepo.deleteReport(reportId);
    }

    @Override
    public MissingActivityReport getMissingReportById(int reportId) {
        return this.missingReportRepo.getMissingReportById(reportId);
    }

    @Override
    public void addReport(MissingActivityReport a) {
        if (!a.getFile().isEmpty() && a.getProof() == null){
            try {
                Map res = this.cloudinary.uploader().upload(a.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                a.setProof(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(MissingReportServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.missingReportRepo.addReport(a);
    }
    
}
