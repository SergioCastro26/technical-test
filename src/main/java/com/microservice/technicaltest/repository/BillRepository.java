package com.microservice.technicaltest.repository;

import com.microservice.technicaltest.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    List<Bill> findByUser_IdUser(Long userId);
}
