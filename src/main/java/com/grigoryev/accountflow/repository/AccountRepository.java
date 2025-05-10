package com.grigoryev.accountflow.repository;

import com.grigoryev.accountflow.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a LEFT JOIN FETCH a.user WHERE a.id = :id")
    Optional<Account> findWithUserById(Long id);

}
