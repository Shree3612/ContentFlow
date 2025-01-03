package com.contentflow.contentflow.service;


import com.contentflow.contentflow.Converter.CommentConverter;
import com.contentflow.contentflow.dto.request.CommentRequest;
import com.contentflow.contentflow.dto.response.CommentResponse;
import com.contentflow.contentflow.entities.Comment;
import com.contentflow.contentflow.entities.Post;
import com.contentflow.contentflow.exception.CommentNotFoundException;
import com.contentflow.contentflow.exception.PostNotFoundException;
import com.contentflow.contentflow.repository.CommentRepository;
import com.contentflow.contentflow.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    public CommentResponse createComment(CommentRequest commentRequest, Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(()-> new PostNotFoundException("Post with this ID not exists"));
        Comment comment = CommentConverter.commentDtoToCommentEntity(commentRequest);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
       // comment.setContent(commentRequest.getContent());
        CommentResponse response = CommentConverter.commentEntityToCommentResponse(savedComment);
        return response;
    }

    public String deleteComment(Integer commentId) {
            Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new CommentNotFoundException("Comment with this ID does not exist"));
            commentRepository.delete(comment);
            return "Comment deleted successfully";
    }

}
