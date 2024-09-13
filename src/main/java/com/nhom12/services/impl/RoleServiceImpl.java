/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.services.impl;

import com.nhom12.pojo.Role;
import com.nhom12.repositories.RoleRepository;
import com.nhom12.services.RoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepo;
    
    @Override
    public List<Role> getRoles() {
        return this.roleRepo.getRoles();
    }

    @Override
    public Role getAdminRole() {
        return this.roleRepo.getAdminRole();
    }
    
}
