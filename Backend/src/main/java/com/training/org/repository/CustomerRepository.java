package com.training.org.repository;
import com.training.org.model.Customer;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer,String>{

}
