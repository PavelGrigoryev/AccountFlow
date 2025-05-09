package com.grigoryev.accountflow.repository;

import com.grigoryev.accountflow.model.PhoneData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneDataRepository extends JpaRepository<PhoneData, Long> {
}
