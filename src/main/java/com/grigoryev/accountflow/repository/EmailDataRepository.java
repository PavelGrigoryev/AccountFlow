package com.grigoryev.accountflow.repository;

import com.grigoryev.accountflow.model.EmailData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailDataRepository extends JpaRepository<EmailData, Long> {
}
