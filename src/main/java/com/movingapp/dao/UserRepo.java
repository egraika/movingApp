package com.movingapp.dao;


import com.movingapp.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<Employee, Long> {
	 Employee findByLogin(String login);
}
