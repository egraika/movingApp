package com.example.dao;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.model.EmployeeEntity;
import com.example.model.MoveEntity;

@Transactional
public interface EmployeeDao extends JpaRepository<EmployeeEntity, Long> {
	
	public EmployeeEntity findById(long id);
	public EmployeeEntity findByUsername(String username);
	public EmployeeEntity findByUsernameAndPassword(String username, String password);
}

