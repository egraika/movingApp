package com.movingapp.dao;


import com.movingapp.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorityRepo extends JpaRepository<Authority, Long> {

    Authority findByName(String name);

    @Query("Select a from Authority a where a.name != 'user'")
    List<Authority> findAllThatNotUser();
}