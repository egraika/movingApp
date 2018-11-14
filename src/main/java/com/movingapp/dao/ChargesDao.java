package com.movingapp.dao;

import com.movingapp.model.ChargeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ChargesDao extends JpaRepository<ChargeEntity, Integer> {
	List<ChargeEntity> findByMoveid(long moveid);
}
