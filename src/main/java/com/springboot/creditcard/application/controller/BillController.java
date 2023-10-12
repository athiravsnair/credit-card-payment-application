package com.springboot.creditcard.application.controller;

import com.springboot.creditcard.application.model.Bill;
import com.springboot.creditcard.application.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/{creditCardId}")
    public ResponseEntity<List<Bill>> getBillsByCreditCard(@PathVariable Long creditCardId) {
        List<Bill> bills = billService.getBillsByCreditCardId(creditCardId);
        return ResponseEntity.ok(bills);
    }

    @GetMapping("/view/{billId}")
    public ResponseEntity<Bill> viewBill(@PathVariable long billId) {

        Bill bill = billService.getBillDetails(billId);
        return ResponseEntity.ok(bill);

    }

    @PostMapping("/generate/{creditCardId}")
    public ResponseEntity<String> generateBill(@PathVariable Long creditCardId, @RequestBody Bill bill) {
        billService.generateBill(creditCardId, bill);
        return ResponseEntity.ok("Bill Generated Successsfully!");
    }

    @PostMapping("/pay/{billId}")
    public ResponseEntity<String> payBill(@PathVariable long billId) {
        boolean paymentStatus = billService.makeBillPayment(billId);

        if (paymentStatus) {
            return ResponseEntity.ok("Bill Payment Successful!");
        } else {
            return ResponseEntity.ok("Bill has been paid already!");
        }

    }
}
