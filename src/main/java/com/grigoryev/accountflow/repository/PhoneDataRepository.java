package com.grigoryev.accountflow.repository;

import com.grigoryev.accountflow.model.PhoneData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PhoneDataRepository extends JpaRepository<PhoneData, Long> {

    @Query("SELECT p FROM PhoneData p LEFT JOIN FETCH p.user WHERE p.id = :id")
    Optional<PhoneData> findWithUserById(Long id);

}
