package com.springboot.creditcard.application.repository;

import com.springboot.creditcard.application.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> findByCreditCardId(Long creditCardId);

}
