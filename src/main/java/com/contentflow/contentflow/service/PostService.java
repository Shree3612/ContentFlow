package com.contentflow.contentflow.service;


import com.contentflow.contentflow.Converter.PostConverter;
import com.contentflow.contentflow.dto.request.PostRequest;
import com.contentflow.contentflow.dto.response.PostResponse;
import com.contentflow.contentflow.entities.Category;
import com.contentflow.contentflow.entities.Post;
import com.contentflow.contentflow.entities.User;
import com.contentflow.contentflow.exception.CategoryNotFoundException;
import com.contentflow.contentflow.exception.PostNotFoundException;
import com.contentflow.contentflow.exception.UserNotFoundException;
import com.contentflow.contentflow.repository.CategoryRepository;
import com.contentflow.contentflow.repository.PostRepository;
import com.contentflow.contentflow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

        @Autowired
        PostRepository postRepository;

        @Autowired
        UserRepository userRepository;

        @Autowired
        CategoryRepository categoryRepository;

    public PostResponse createPost(PostRequest postRequest, Integer userId, Integer categoryId){
        Post post = PostConverter.postDtoToPostEntity(postRequest);
        //we are taking only title and content from requestDto,
        //we are taking userId and categoryId from pathVariable
        post.setImageName("default.png");
        post.setAddedDate(new Date());

        //find user from userid and set in post
        User user = userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User with this ID not present"));
        post.setUser(user);
        //find category from categoryid and set in post
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new CategoryNotFoundException("Category with this ID not present"));
        post.setCategory(category);

        Post savedPost = postRepository.save(post);
        PostResponse postResponse = PostConverter.postEntityToPostResponse(savedPost);
        return postResponse;
    }

    //get all posts of a category
    public List<PostResponse> getPostsByCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new CategoryNotFoundException("Category with this ID not present"));;
        List<Post> posts = postRepository.findByCategory(category);
        List<PostResponse> postResponse = new ArrayList<>();
        for(Post post : posts) {
            postResponse.add(PostConverter.postEntityToPostResponse(post));
        }
        return postResponse;
    }

    //get all posts of a user
    public List<PostResponse> getPostsByUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException("User with this ID not present"));
        List<Post> posts = postRepository.findByUser(user);
        List<PostResponse> postResponse = new ArrayList<>();
        for(Post post : posts) {
            postResponse.add(PostConverter.postEntityToPostResponse(post));
        }
        return postResponse;
    }

    //get all posts
    public List<PostResponse> getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection){
        Sort sort = null;
        if(sortDirection.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        }
        else{
            sort = Sort.by(sortBy).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = postRepository.findAll(pageable);
        List<Post> posts = pagePost.getContent();
        List<PostResponse> response = new ArrayList<>();
        for(Post post : posts) {
            response.add(PostConverter.postEntityToPostResponse(post));
        }
        return response;
    }

    public PostResponse getPostById(Integer postId){
           Post post =   postRepository.findById(postId)
                   .orElseThrow(()-> new PostNotFoundException("Post with this ID does not exist"));
           PostResponse response = PostConverter.postEntityToPostResponse(post);
           return response;
    }

    public String deletePost(Integer postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new PostNotFoundException("Post with this ID does not exist"));
        postRepository.delete(post);
        return "post deleted successfully";
    }

    public PostResponse updatePost(PostRequest postRequest, Integer postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(()-> new UserNotFoundException("Post with this ID not present"));

        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());

        Post updatedPost = postRepository.save(post);
        PostResponse response = PostConverter.postEntityToPostResponse(updatedPost);
        return response;
    }

    public List<PostResponse> findByTitleContaining(String keyWord) {
            List<Post> posts = postRepository.findByTitleContaining(keyWord);
            List<PostResponse> responses = new ArrayList<>();
            for(Post post : posts) {
                responses.add(PostConverter.postEntityToPostResponse(post));
            }
            return responses;
    }

}
