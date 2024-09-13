/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.formatters;

import com.nhom12.pojo.Faculty;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Admin
 */
public class FacultyFormatter implements Formatter<Faculty>{

    @Override
    public String print(Faculty f, Locale locale) {
        return String.valueOf(f.getId());
    }

    @Override
    public Faculty parse(String id, Locale locale) throws ParseException {
        Faculty f = new Faculty();
        f.setId(Integer.parseInt(id));
        
        return f;
    }
    
}
