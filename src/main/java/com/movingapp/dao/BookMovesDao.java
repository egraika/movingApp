package com.movingapp.dao;

import javax.transaction.Transactional;

import com.movingapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.movingapp.model.MoveEntity;

import java.util.List;

@Transactional
public interface BookMovesDao extends JpaRepository<MoveEntity, Integer> {
	
	public MoveEntity findById(int id);
	
	@Query("Select m from MoveEntity m where m.fromState IN :locations")
	public Page<MoveEntity> findAll(Pageable pageable, @Param("locations") Object[] objects);
	
	@Query("Select m from MoveEntity m where m.fromState IN :locations"
			+ " AND (:statusSearch = '' OR m.status = :statusSearch)"
			+ " AND m.moveTitle LIKE %:search%")
	public Page<MoveEntity> findAll(@Param("search") String search,@Param("statusSearch") String statusSearch, Pageable pageable, @Param("locations")   Object[] objects);

	@Query("Select m from MoveEntity m where m.fromState IN :locations"
			+ " AND year(m.moveStart) = :year")
	public List<MoveEntity> findByYear(@Param("year") int year, @Param("locations")   Object[] objects);

	@Query("Select m from MoveEntity m where m.user = :user")
	public Page<MoveEntity> findAllMyMoves(Pageable pageable, @Param("user") User user);

	@Query("Select m from MoveEntity m where m.user = :user"
			+ " AND (:statusSearch = '' OR m.status = :statusSearch)"
			+ " AND m.moveTitle LIKE %:search%")
	public Page<MoveEntity> findAllMyMoves(@Param("search") String search,@Param("statusSearch") String statusSearch, Pageable pageable, @Param("user") User user);
}

