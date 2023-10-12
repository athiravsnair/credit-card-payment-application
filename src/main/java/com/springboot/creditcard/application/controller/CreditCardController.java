package com.springboot.creditcard.application.controller;

import com.springboot.creditcard.application.model.CreditCard;
import com.springboot.creditcard.application.service.CreditCardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/creditcard")
public class CreditCardController {

    @Autowired
    private CreditCardService creditCardService;

    @PostMapping("/save/{customerId}")
    public ResponseEntity<CreditCard> createCreditCard(@PathVariable Long customerId, @Valid @RequestBody CreditCard creditCard) {
        CreditCard newCreditCard = creditCardService.createCreditCard(customerId, creditCard);
        return ResponseEntity.ok(newCreditCard);
    }

    @PutMapping("/update/{creditCardId}")
    public ResponseEntity<CreditCard> updateCard(@PathVariable long creditCardId, @Valid @RequestBody CreditCard creditCard) {
        CreditCard updateCreditCard = creditCardService.updateCreditCard(creditCardId, creditCard);
        return ResponseEntity.ok(updateCreditCard);
    }

    @DeleteMapping("/delete/{creditCardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable Long creditCardId) {
        creditCardService.deleteCreditCard(creditCardId);
        return ResponseEntity.noContent().build();
    }


}
