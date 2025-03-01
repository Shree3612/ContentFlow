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

}
