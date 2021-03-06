package com.movingapp.dao;


import com.movingapp.entity.Authority;
import com.movingapp.entity.Location;
import com.movingapp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Transactional
public interface UserRepo extends JpaRepository<User, Long> {
	User findByEmail(String email);
	User findByConfirmationToken(String confirmationToken);

	@Query("Select distinct u from User u join u.locations t where t in (:locations) and (lower(u.firstName) LIKE lower(concat('%', :search,'%')) or lower(u.lastName) LIKE lower(concat('%', :search,'%')) or lower(u.email) LIKE lower(concat('%', :search,'%')))")
	public Page<User> findAll(Pageable pageable, @Param("locations") Set<Location> objects, @Param("search") String search);

	@Query("Select distinct u from User u join u.locations t where t in (:locations) and (:userType) MEMBER OF u.authorities and (lower(u.firstName) LIKE lower(concat('%', :search,'%')) or lower(u.lastName) LIKE lower(concat('%', :search,'%')) or lower(u.email) LIKE lower(concat('%', :search,'%')))")
	public Page<User> findByUserType(Pageable pageable, @Param("locations") Set<Location> objects, @Param("userType") Authority userType, @Param("search") String search);

	@Query("Select distinct u from User u join u.locations t where t in (:locations) and (:location) MEMBER OF u.locations and (lower(u.firstName) LIKE lower(concat('%', :search,'%')) or lower(u.lastName) LIKE lower(concat('%', :search,'%')) or lower(u.email) LIKE lower(concat('%', :search,'%')))")
	public Page<User> findByLocationFilter(Pageable pageable, @Param("locations") Set<Location> objects, @Param("location") Location location, @Param("search") String search);

	@Query("Select distinct u from User u join u.locations t where t in (:locations) and (:userType) MEMBER OF u.authorities and (:location) MEMBER OF u.locations and (lower(u.firstName) LIKE lower(concat('%', :search,'%')) or lower(u.lastName) LIKE lower(concat('%', :search,'%')) or lower(u.email) LIKE lower(concat('%', :search,'%')))")
	public Page<User> findByUserTypeAndLocationFilter(Pageable pageable, @Param("locations") Set<Location> objects, @Param("userType") Authority userType, @Param("location") Location location, @Param("search") String search);

	@Query("Select distinct u from User u join u.authorities a where a in (:authorities) and not(u IN :users) and :location MEMBER OF u.locations")
	List<User> findAllNotAssignedToUser(@Param("users") List<User> objects, @Param("location") Location location, @Param("authorities") List<Authority> authorities);

	@Query("Select distinct u from User u join u.authorities a where a in (:authorities) and :location MEMBER OF u.locations")
	List<User> findAllWithLocation(@Param("location") Location location, @Param("authorities") List<Authority> authorities);

	@Query("Select distinct u from User u join u.locations l where l in (:locations) and not(:userAuthority MEMBER OF u.authorities) order by u.firstName ASC")
	List<User> findAllWithLocationsAndNotUser(@Param("locations") Set<Location> locations, @Param("userAuthority") Authority authority);
}
