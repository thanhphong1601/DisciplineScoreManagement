/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.services.impl;

import com.nhom12.pojo.Activity;
import com.nhom12.pojo.Comment;
import com.nhom12.pojo.User;
import com.nhom12.repositories.CommentRepository;
import com.nhom12.services.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepo;

    @Override
    public void createComment(int creatorId, int activityId, String content) {
        this.commentRepo.createComment(creatorId, activityId, content);
    }

    @Override
    public List<Comment> getComments(int activityId) {
        return this.commentRepo.getComments(activityId);
    }
    
}
