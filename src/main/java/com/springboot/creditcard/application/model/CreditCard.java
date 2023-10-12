package com.springboot.creditcard.application.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreditCardNumber(message = "Invalid card number format!")
    private String cardNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])/[0-9]{2}$", message = "Invalid expiry date format. Use MM/YY format.")
    private String expiryDate;

    @Pattern(regexp = "^(\\d{3})$", message = "Invalid CVV format. Must be 3 digits.")
    private String cvv;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Customer customer;
}
