package com.contentflow.contentflow.repository;


import com.contentflow.contentflow.entities.Category;
import com.contentflow.contentflow.entities.Post;
import com.contentflow.contentflow.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

        //customized function
        List<Post> findByUser(User user);
        List<Post> findByCategory(Category category);
        List<Post> findByTitleContaining(String keyWord);

}
