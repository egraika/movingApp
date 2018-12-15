package com.movingapp.dao;

import com.movingapp.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Transactional
public interface LocationDao extends JpaRepository<Location, Integer> {

    @Query("Select l from Location l where l.location = :location")
    Location findByLocation(@Param("location") String location);

    @Query("Select l from Location l where not(l IN :locations)")
    List<Location> findAllNotAssignedToUser(@Param("locations") Set<Location> objects);

    @Query("Select l from Location l where l IN :locations")
    List<Location> findAll(@Param("locations") Set<Location> objects);
}
