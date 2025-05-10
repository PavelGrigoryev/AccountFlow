package com.grigoryev.accountflow.repository;

import com.grigoryev.accountflow.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAll(Specification<User> spec, Pageable pageable);

    @Query("SELECT u FROM User u JOIN u.phones p WHERE p.phone = :phone")
    Optional<User> findByPhone(String phone);

    @Query("SELECT u FROM User u JOIN u.emails p WHERE p.email = :email")
    Optional<User> findByEmail(String email);

}
