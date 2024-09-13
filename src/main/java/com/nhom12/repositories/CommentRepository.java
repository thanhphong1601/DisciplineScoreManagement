/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.repositories;

import com.nhom12.pojo.Activity;
import com.nhom12.pojo.Comment;
import com.nhom12.pojo.User;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface CommentRepository {
    void createComment(int creatorId, int activityId, String content);
    List<Comment> getComments(int activityId);
}
