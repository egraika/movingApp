package com.movingapp.dao;


import com.movingapp.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepo extends JpaRepository<User, Long> {
	User findByEmail(String email);
	User findByConfirmationToken(String confirmationToken);
}
