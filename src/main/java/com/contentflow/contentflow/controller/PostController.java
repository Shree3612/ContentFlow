package com.contentflow.contentflow.controller;

import com.contentflow.contentflow.config.Constants;
import com.contentflow.contentflow.dto.request.PostRequest;
import com.contentflow.contentflow.dto.response.PostResponse;
import com.contentflow.contentflow.exception.CategoryNotFoundException;
import com.contentflow.contentflow.exception.PostNotFoundException;
import com.contentflow.contentflow.exception.UserNotFoundException;
import com.contentflow.contentflow.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/create/user/{userId}/category/{categoryId}")
    public ResponseEntity createPost(@Valid @RequestBody PostRequest postRequest,
                                        @PathVariable Integer userId, @PathVariable Integer categoryId) {
            PostResponse postResponse = postService.createPost(postRequest, userId, categoryId);
            return new ResponseEntity(postResponse, HttpStatus.CREATED);
    }

    //get all posts of a user
    @GetMapping("/getAll/user/{userId}")
    public ResponseEntity getPostsByUser(@PathVariable Integer userId) {
        try {
            return new ResponseEntity(postService.getPostsByUser(userId), HttpStatus.OK);
        }
        catch(UserNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //get all posts of a category
    @GetMapping("/getAll/category/{categoryId}")
    public ResponseEntity getPostsByCategory(@PathVariable Integer categoryId) {
        try {
            return new ResponseEntity(postService.getPostsByCategory(categoryId), HttpStatus.OK);
        }
        catch(CategoryNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll(
            @RequestParam(value = "pageNumber", defaultValue = Constants.pageNumber, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = Constants.pageSize, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = Constants.sortBy, required = false) String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = Constants.sortDirection, required = false) String sortDirection
            ){
        List<PostResponse> response = postService.getAllPosts(pageNumber, pageSize, sortBy, sortDirection);
        return new ResponseEntity<List<PostResponse>>(response, HttpStatus.OK);
    }

    @GetMapping("/get/{postId}")
    public ResponseEntity getPostById(@PathVariable Integer postId){
        try{
            return new ResponseEntity (postService.getPostById(postId), HttpStatus.OK);
        }
        catch(PostNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity deletePost(@PathVariable Integer postId){
        try{
            return new ResponseEntity(postService.deletePost(postId), HttpStatus.OK);
        }
        catch(PostNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity updatePost(@Valid @RequestBody PostRequest postRequest, @PathVariable Integer postId) {
        try{
            return new ResponseEntity(postService.updatePost(postRequest, postId), HttpStatus.OK);
        }
        catch(PostNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search/title")
    public ResponseEntity searchPost(
            @RequestParam(value = "keyWord") String keyWord
            ) {
        List<PostResponse> responses = postService.findByTitleContaining(keyWord);
        if (responses == null || responses.isEmpty()) {
            return new ResponseEntity<>("Post-title containing this keyword does not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
