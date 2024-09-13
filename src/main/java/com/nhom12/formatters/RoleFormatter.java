/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.formatters;

import com.nhom12.pojo.Role;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author Admin
 */
public class RoleFormatter implements Formatter<Role> {

    @Override
    public String print(Role role, Locale locale) {
        return String.valueOf(role.getId());
    }

    @Override
    public Role parse(String id, Locale locale) throws ParseException {
        Role role = new Role();
        role.setId(Integer.parseInt(id));

        return role;
    }

}
