package com.be.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.be.entities.Manufacturer;




@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, String>{
	List<Manufacturer> findByTitleContaining(String name);
	Page<Manufacturer> findByTitleContaining(String name, Pageable pageable);
}
