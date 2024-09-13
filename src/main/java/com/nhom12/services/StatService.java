/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.services;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public interface StatService {
    List<Object[]> scoreRevenueByFaculty();
    List<Object[]> scoreRevenueByClass();
    void pdfOutput(HttpServletResponse response, int typeId);
}
