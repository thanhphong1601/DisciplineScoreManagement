/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nhom12.controllers;

import com.nhom12.pojo.Activity;
import com.nhom12.pojo.Comment;
import com.nhom12.pojo.User;
import com.nhom12.requests.CommentRequest;
import com.nhom12.services.ActivityService;
import com.nhom12.services.CommentService;
import com.nhom12.services.UserService;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiCommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;
    
    @PostMapping(path = "/comments/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Comment> createComment(@RequestBody CommentRequest commentReq){     
        String content = commentReq.getContent();
        int activityId = commentReq.getActivityId();
        User u = commentReq.getUser();

        if (content.isBlank() || content.isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(null);
        
        this.commentService.createComment(u.getId(), activityId, content);
        return ResponseEntity.ok().body(null);
    }
    
    @GetMapping(path = "/activities/{activityId}/comments/", produces = {
        MediaType.APPLICATION_JSON_VALUE
    })
    @ResponseStatus(HttpStatus.ACCEPTED) 
    public ResponseEntity<List<Comment>> getComments(@PathVariable(value = "activityId") int activityId){
        Activity a = this.activityService.getActivityById(activityId);
        if (a == null)
            return ResponseEntity.badRequest().body(null);
              
        return new ResponseEntity<>(this.commentService.getComments(activityId), HttpStatus.OK);
    }
}
