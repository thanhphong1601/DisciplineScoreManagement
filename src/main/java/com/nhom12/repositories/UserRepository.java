/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.repositories;

import com.nhom12.pojo.User;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public interface UserRepository {
    List<User> getUsers(Map<String, String> params);
    void addOrUpdateUser(User user);
    User getUserById(int id);
    User getUserByUsername(String username);
    User getUserByEmail(String email, String username);
    boolean authUser(String username, String password);
    Long countStudent();
}
