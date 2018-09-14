package com.movingapp.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movingapp.model.ContactEntity;

@Transactional
public interface ContactDao extends JpaRepository<ContactEntity, Long> {
	
}

