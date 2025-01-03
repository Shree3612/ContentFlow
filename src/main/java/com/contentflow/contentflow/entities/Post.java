package com.contentflow.contentflow.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="post")
@NoArgsConstructor
@Schema
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name="post_title", nullable = false, length = 100)
    String title;
    @Column(name="post_content", nullable = false, length = 1000)
    String content;
    @Column(name="post_image_name")
    String imageName;
    @Column(name="post_creation_date")
    Date addedDate;


    @ManyToOne
    @JoinColumn
    @JsonBackReference
    Category category;

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    List<Comment> comments  = new ArrayList<>();

    //lombok not working therefore created getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
