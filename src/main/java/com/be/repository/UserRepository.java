package com.be.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.be.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
	List<User> findByfullNameContaining(String fullName);

	User findByAccount_Username(String username);

	Page<User> findByfullNameContaining(String fullName, Pageable pageable);

	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.enabled = :enabled WHERE u.userId = :userId")
	void updateEnabled(@Param("enabled") Boolean enabled, @Param("userId") String userId);
}
