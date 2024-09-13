/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.adapters;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author Admin
 */
public class DateAdapter extends XmlAdapter<String, Date>{
    
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


    @Override
    public Date unmarshal(String v) throws Exception {
        return dateFormat.parse(v);
    }

    @Override
    public String marshal(Date d) throws Exception {
        return dateFormat.format(d);
    }
    
}
