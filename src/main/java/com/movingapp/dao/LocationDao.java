package com.movingapp.dao;

import com.movingapp.entity.Location;
import com.movingapp.model.ChargeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface LocationDao extends JpaRepository<Location, Integer> {
	Location findByLocation(String location);
}
