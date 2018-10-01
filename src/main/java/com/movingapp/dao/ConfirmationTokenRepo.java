package com.movingapp.dao;

import com.movingapp.entity.ConfirmationToken;
import com.movingapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.stream.Stream;

@Transactional
public interface ConfirmationTokenRepo extends JpaRepository<ConfirmationToken, Long> {

    ConfirmationToken findByToken(String token);

    ConfirmationToken findByUser(User user);

    Stream<ConfirmationToken> findAllByExpiryDateLessThan(Date now);

    void deleteByExpiryDateLessThan(Date now);

    @Modifying
    @Query("delete from ConfirmationToken t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);

    @Modifying
    @Query("delete from ConfirmationToken where user_id = :id")
    void deleteByUserId( @Param("id") long id);
}
