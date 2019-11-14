package com.integral.boot.dao;


import com.integral.boot.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface JpaCustomerFind extends JpaRepository<Customer,Long> {


}
