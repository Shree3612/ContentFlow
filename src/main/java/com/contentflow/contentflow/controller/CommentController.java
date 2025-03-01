package com.contentflow.contentflow.controller;


import com.contentflow.contentflow.dto.request.CommentRequest;
import com.contentflow.contentflow.dto.response.CommentResponse;
import com.contentflow.contentflow.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping("/create/{postId}")
    public ResponseEntity<CommentResponse> createComment(@Valid @RequestBody CommentRequest commentRequest, @PathVariable("postId") Integer postId){
            CommentResponse commentResponse = commentService.createComment(commentRequest, postId);
            return new ResponseEntity<CommentResponse>(commentResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable("commentId") Integer commentId) {
            String response = commentService.deleteComment(commentId);
        return "deleted successfully";
    }
}
