package com.movingapp.dao;


import com.movingapp.entity.Token;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<Token, String> {
}
