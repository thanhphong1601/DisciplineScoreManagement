/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.services;

import com.nhom12.pojo.MissingActivityReport;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface MissingReportService {
    List<MissingActivityReport> getMissingReport(int facultyId);
    MissingActivityReport getMissingReportById(int reportId);
    void confirmReport(MissingActivityReport r);
    void deleteReport(int reportId);
    void addReport(MissingActivityReport a);
}
