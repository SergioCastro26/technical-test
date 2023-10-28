package com.microservice.technicaltest.service;

import com.microservice.technicaltest.model.Bill;
import com.microservice.technicaltest.model.User;
import com.microservice.technicaltest.repository.BillRepository;
import com.microservice.technicaltest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BillRepository billRepository;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long id){
        return userRepository.findById(id);
    }

    public User saveOrUpdate(User user){
        return userRepository.save(user);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public List<Bill> getUserBills(Long userId) {
        return billRepository.findByUser_IdUser(userId);
    }
}
