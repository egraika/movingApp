package com.example.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Authority;

public interface AuthorityRepo extends JpaRepository<Authority, Long> {

}