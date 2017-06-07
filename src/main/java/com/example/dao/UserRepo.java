package com.example.dao;


import com.example.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long> {
	 User findByLogin(String login);

}
