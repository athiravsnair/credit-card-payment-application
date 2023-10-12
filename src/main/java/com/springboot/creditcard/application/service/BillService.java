package com.springboot.creditcard.application.service;

import com.springboot.creditcard.application.model.Bill;
import com.springboot.creditcard.application.model.CreditCard;
import com.springboot.creditcard.application.repository.BillRepository;
import com.springboot.creditcard.application.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    public List<Bill> getBillsByCreditCardId(Long creditCardId) {
        return billRepository.findByCreditCardId(creditCardId);
    }

    public Bill getBillDetails(Long billId) {
        return billRepository.findById(billId).orElseThrow(() -> new RuntimeException("Bill No." + billId + " not found!"));
    }

    public boolean makeBillPayment(Long billId) {
        Bill bill = billRepository.findById(billId).orElseThrow(() -> new RuntimeException("Bill No." + billId + " not found!"));
        if (!bill.isPaid()) {
            bill.setPaid(true);
            billRepository.save(bill);
            return true;
        } else {
            return false;
        }
    }

    public Bill generateBill(Long creditCardId, Bill bill) {

        CreditCard creditCard = creditCardRepository.findById(creditCardId).orElseThrow(() -> new RuntimeException("Credit Card " + creditCardId + " not available!"));

        Bill newBill = new Bill();

        newBill.setAmount(bill.getAmount());
        newBill.setBillDate(bill.getBillDate());
        newBill.setCreditCard(creditCard);
        newBill.setPaid(false);

        return billRepository.save(newBill);
    }
}
