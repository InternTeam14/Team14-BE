package com.be.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.be.entities.Account;




@Repository
public interface AccountRepository extends JpaRepository<Account, String>{
	
}
