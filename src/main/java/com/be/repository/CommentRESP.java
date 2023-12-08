package com.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.be.entities.Comment;

public interface CommentRESP extends JpaRepository<Comment, Long>{
	
}
