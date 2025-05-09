package com.grigoryev.accountflow.repository;

import com.grigoryev.accountflow.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAll(Specification<User> spec, Pageable pageable);

}
