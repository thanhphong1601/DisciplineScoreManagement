package com.nhom12.repositories;


import com.nhom12.pojo.SchoolEmail;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public interface SchoolEmailRepository {
    List<SchoolEmail> getEmails();
    boolean checkEmail(String email);

}
