package com.br.qualiti.banck.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.qualiti.banck.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
