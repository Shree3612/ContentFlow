package com.contentflow.contentflow.repository;


import com.contentflow.contentflow.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
