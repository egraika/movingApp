package com.example.dao;

import java.util.Date;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.model.MoveEntity;
import com.example.entity.Location;

@Transactional
public interface BookMovesDao extends JpaRepository<MoveEntity, Integer> {
	
	public MoveEntity findById(int id);
	
	@Query("Select m from MoveEntity m where m.fromState IN :locations")
	public Page<MoveEntity> findAll(Pageable pageable, @Param("locations") Object[] objects);
	
	@Query("Select m from MoveEntity m where m.fromState IN :locations"
			+ " AND (:statusSearch = '' OR m.status = :statusSearch)"
			+ " AND (CONCAT(m.firstName, ' ', m.lastName) LIKE %:search%"
			+ " OR CONCAT(m.lastName, ' ', m.firstName) LIKE %:search%)")
	public Page<MoveEntity> findAll(@Param("search") String search,@Param("statusSearch") String statusSearch, Pageable pageable, @Param("locations")   Object[] objects);
}

