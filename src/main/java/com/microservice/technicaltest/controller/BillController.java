package com.microservice.technicaltest.controller;

import com.microservice.technicaltest.model.Bill;
import com.microservice.technicaltest.model.User;
import com.microservice.technicaltest.repository.BillRepository;
import com.microservice.technicaltest.service.BillService;
import com.microservice.technicaltest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/bills")
public class BillController {
    @Autowired
    BillService billService;
    @Autowired
    UserService userService;
    @Autowired
    BillRepository billRepository;

    @GetMapping
    public List<Bill> getAll() {
        return billService.getBills();
    }

    @GetMapping("/{billId}")
    public Optional<Bill> getById(@PathVariable("billId") Long billId) {
        return billService.getBill(billId);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<String> createBill(@RequestBody Bill bill, @PathVariable("userId") Long userId) {
        User user = userService.getUser(userId).orElse(null);
        if (user != null) {
            bill.setUser(user);
            billService.saveOrUpdate(bill);
            return ResponseEntity.ok("Se agregó exitosamente la factura");
        } else {
            return ResponseEntity.badRequest().body("El usuario no existe");
        }
    }

    @DeleteMapping("/{billId}")
    public void delete(@PathVariable("billId") Long billId) {
        billService.delete(billId);
    }

    @PutMapping("/edit/{billId}")
    public Bill updatedBill(@PathVariable("billId") Long billId, @RequestBody Bill bill) {
      Bill updatedBill  = billRepository.findById(billId).get();
      updatedBill.totalAmount = bill.totalAmount;
      updatedBill.desc = bill.desc;
      return billRepository.save(updatedBill);
    }
}
