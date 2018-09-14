package com.movingapp.dao;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.movingapp.model.MoveEntity;

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

