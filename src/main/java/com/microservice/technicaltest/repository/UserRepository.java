package com.microservice.technicaltest.repository;

import com.microservice.technicaltest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    Optional<User> findById(Long idUser);

    Optional<User> findByEmail(String email);
}
