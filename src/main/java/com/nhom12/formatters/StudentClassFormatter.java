/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.formatters;

import com.nhom12.pojo.StudentClass;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Admin
 */
public class StudentClassFormatter implements Formatter<StudentClass> {

    @Override
    public String print(StudentClass c, Locale locale) {
        return String.valueOf(c.getId());
    }

    @Override
    public StudentClass parse(String id, Locale locale) throws ParseException {
        StudentClass c = new StudentClass();
        c.setId(Integer.parseInt(id));

        return c;
    }

}
