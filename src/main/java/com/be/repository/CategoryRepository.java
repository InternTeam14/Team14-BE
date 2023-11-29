package com.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.be.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
 
}
