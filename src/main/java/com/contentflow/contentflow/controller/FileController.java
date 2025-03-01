package com.contentflow.contentflow.controller;


import com.contentflow.contentflow.Converter.PostConverter;
import com.contentflow.contentflow.dto.response.PostResponse;
import com.contentflow.contentflow.entities.Post;
import com.contentflow.contentflow.exception.PostNotFoundException;
import com.contentflow.contentflow.repository.PostRepository;
import com.contentflow.contentflow.service.FileService;
import com.contentflow.contentflow.service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/file")
public class FileController {

    @Autowired
    FileService fileService;

    @Autowired
    PostService postService;

    @Autowired
    PostRepository postRepository;


    //takes project.image value from application.properties file
    @Value("${project.image}")
    String path;

    @PostMapping("/upload/{postId}")
    public ResponseEntity<?> fileUpload(
            @RequestParam("image")MultipartFile image,
            @PathVariable Integer postId
            ) throws IOException {
        try {
            // Check if post exists
            Optional<Post> optionalPost = postRepository.findById(postId);
            if (optionalPost.isEmpty()) {
                throw new PostNotFoundException("Post with ID " + postId + " not found");
            }

            // Get the post entity
            Post post = optionalPost.get();

            // Upload the image and update post
            String fileName = fileService.uploadImage(path, image);
            post.setImageName(fileName);

            // Save updated post
            Post updatedPost = postRepository.save(post);

            // Convert updated entity to DTO
            PostResponse postResponse = PostConverter.postEntityToPostResponse(updatedPost);

            // Return the updated response
            return new ResponseEntity<>(postResponse, HttpStatus.OK);

        } catch (PostNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to upload the image: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/serve/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
                @PathVariable("imageName") String imageName,
                HttpServletResponse response
                ) throws IOException {
        InputStream resource = fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}
