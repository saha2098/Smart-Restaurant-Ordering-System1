package com.sahana.restaurant_ordering_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sahana.restaurant_ordering_system.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
