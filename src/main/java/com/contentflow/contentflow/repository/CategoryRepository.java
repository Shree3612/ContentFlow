package com.contentflow.contentflow.repository;


import com.contentflow.contentflow.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
