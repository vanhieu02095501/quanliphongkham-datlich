package com.quanlyphongkhamvadatlich.repository;

import com.quanlyphongkhamvadatlich.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
