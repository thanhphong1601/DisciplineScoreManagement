/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.formatters;

import com.nhom12.pojo.DisciplineScore;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Admin
 */
public class DisciplineScoreFormatter implements Formatter<DisciplineScore>{

    @Override
    public String print(DisciplineScore s, Locale locale) {
        return String.valueOf(s.getId());
    }

    @Override
    public DisciplineScore parse(String id, Locale locale) throws ParseException {
        DisciplineScore s = new DisciplineScore();
        s.setId(Integer.parseInt(id));
        
        return s;
    }
    
}
