package com.microservice.technicaltest.controller;

import com.microservice.technicaltest.model.Bill;
import com.microservice.technicaltest.model.User;
import com.microservice.technicaltest.service.BillService;
import com.microservice.technicaltest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BillService billService;

    @GetMapping
    public List<User> getAll(){
        return userService.getUsers();
    }

    @GetMapping("/{userId}")
    public Optional<User> getById(@PathVariable("userId") Long userId){
        return userService.getUser(userId);
    }

    @PostMapping
    public User save(@Validated @RequestBody User user){
        return userService.saveOrUpdate(user);
    }

    @PostMapping("edit/{userId}")
    public User update(@Validated @RequestBody User user, @PathVariable Long userId){
        user.idUser = userId;
        return userService.saveOrUpdate(user);
    }

    @DeleteMapping("/{userId}")
    public void delete(@PathVariable("userId") Long userId){
        userService.delete(userId);
    }

    @GetMapping("/{userId}/bills")
    public List<Bill> getUserBills(@PathVariable("userId") Long userId) {
        return userService.getUserBills(userId);
    }

    @PostMapping("/{userId}/bills/{billId}")
    public ResponseEntity<String> saveOrUpdateBill(@Validated @PathVariable("userId") Long userId, @PathVariable("billId") Long billId, @RequestBody Bill bill) {
        String message;
        Bill updatedBill = billService.updateBillForUser(userId, billId, bill);
        if (updatedBill != null) {
            message = "La factura se actualizó correctamente para el usuario " + userId;
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message = "No se pudo actualizar la factura para el usuario " + userId + " o la factura no existe.";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{userId}/bills/{billId}")
    public ResponseEntity<String> deleteBill(@PathVariable("userId") Long userId, @PathVariable("billId") Long billId) {
        String message;
        boolean deleted = billService.deleteBillForUser(userId, billId);
        if (deleted) {
            message = "La factura se eliminó correctamente para el usuario " + userId;
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message = "No se pudo eliminar la factura para el usuario " + userId + " o la factura no existe.";
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
