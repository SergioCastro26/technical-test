package com.microservice.technicaltest.service;

import com.microservice.technicaltest.model.Bill;
import com.microservice.technicaltest.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {
    @Autowired
    private BillRepository billRepository;

    public List<Bill> getBills() {
        return billRepository.findAll();
    }

    public Optional<Bill> getBill(Long id) {
        return billRepository.findById(id);
    }

   public void saveOrUpdate(Bill bill) {
       billRepository.save(bill);
   }

    public Bill updateBillForUser(Long userId, Long billId, Bill updatedBill) {
        Optional<Bill> bill = billRepository.findById(billId);
        if (bill.isPresent() && bill.get().getUser().idUser.equals(userId)) {
            Bill billToUpdated  = billRepository.findById(billId).get();
            billToUpdated.totalAmount = updatedBill.totalAmount;
            billToUpdated.desc = updatedBill.desc;
            return billRepository.save(billToUpdated);
        } else {
            return null;
        }
    }

    public boolean deleteBillForUser(Long userId, Long billId) {
        Optional<Bill> bill = billRepository.findById(billId);
        if (bill.isPresent() && bill.get().getUser().idUser.equals(userId)) {
            billRepository.deleteById(billId);
            return true;
        } else {
            return false;
        }
    }

    public void delete(Long id) {
        billRepository.deleteById(id);
    }
}
