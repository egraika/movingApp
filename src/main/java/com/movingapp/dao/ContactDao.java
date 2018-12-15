package com.movingapp.dao;

import com.movingapp.model.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface ContactDao extends JpaRepository<ContactEntity, Long> {
	
}

