package com.springboot.creditcard.application.service;

import com.springboot.creditcard.application.model.CreditCard;
import com.springboot.creditcard.application.model.Customer;
import com.springboot.creditcard.application.repository.CreditCardRepository;
import com.springboot.creditcard.application.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public CreditCard createCreditCard(Long customerId, CreditCard creditCard) {

        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("customer" + customerId + " not available!"));
        creditCard.setCustomer(customer);
        return creditCardRepository.save(creditCard);

    }

    public CreditCard updateCreditCard(Long creditCardId, CreditCard updatedCreditCard) {

        CreditCard existingCreditCard = creditCardRepository.findById(creditCardId).orElseThrow(() -> new RuntimeException("Credit Card " + creditCardId + " not found!"));

        existingCreditCard.setCardNumber(updatedCreditCard.getCardNumber());
        existingCreditCard.setExpiryDate(updatedCreditCard.getExpiryDate());
        existingCreditCard.setCvv(updatedCreditCard.getCvv());

        return creditCardRepository.save(existingCreditCard);
    }

    public void deleteCreditCard(Long creditCardId) {

        creditCardRepository.findById(creditCardId).orElseThrow(() -> new RuntimeException("Credit Card " + creditCardId + " not found!"));
        creditCardRepository.deleteById(creditCardId);
    }
}
