package com.microservice.technicaltest.service;

import com.microservice.technicaltest.entity.UserEntity;
import com.microservice.technicaltest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserEntity> getUsers(){
        return userRepository.findAll();
    }

    public Optional<UserEntity> getUser(Long id){
        return userRepository.findById(id);
    }

    public void saveOrUpdate(UserEntity user){
        userRepository.save(user);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
