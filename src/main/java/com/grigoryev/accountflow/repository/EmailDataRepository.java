package com.grigoryev.accountflow.repository;

import com.grigoryev.accountflow.model.EmailData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmailDataRepository extends JpaRepository<EmailData, Long> {

    @Query("SELECT e FROM EmailData e LEFT JOIN FETCH e.user WHERE e.id = :id")
    Optional<EmailData> findWithUserById(Long id);

}
