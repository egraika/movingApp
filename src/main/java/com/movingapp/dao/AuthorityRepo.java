package com.movingapp.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.movingapp.entity.Authority;

public interface AuthorityRepo extends JpaRepository<Authority, Long> {

    Authority findByName(String name);
}