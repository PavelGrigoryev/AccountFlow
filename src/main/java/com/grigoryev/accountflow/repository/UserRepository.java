package com.grigoryev.accountflow.repository;

import com.grigoryev.accountflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
