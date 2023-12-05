package com.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.be.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, String> {
	List<Cart> findByUsersUserId(String userID);

	@Transactional
	void deleteByUsersUserId(String userID);

	@Query("SELECT COUNT(c) FROM Cart c WHERE c.users.userId = :userID")
	Long countByUserId(@Param("userID") String userID);
}
