package com.training.org.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.training.org.model.Customer;
import com.training.org.service.CustomerService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController    
public class CustomerController   
{    
	@Autowired    
	private CustomerService customerService;     
	//@RequestMapping("/customers")    
	//public List<Customer> getAllUsers()  
	//{    
	//	return customerService.getAllCustomers();    
	//} 
	
	@RequestMapping("/customers")    
	public List<Customer> getAllUsers()  
	{    customerService.getAllCustomers().forEach(customer->customerService.setCustomerCapacity(customer));
		return customerService.getAllCustomers();    
	} 
	
	@GetMapping("/customers/{id}")    
	public ResponseEntity<Customer> getCustomerDetails(@PathVariable("id") String customerId)  
	{    
		Customer customer = customerService.getCustomerById(customerId); 
		if(customer!=null) {
			customerService.setCustomerCapacity(customer);
			return new ResponseEntity<>(customer,HttpStatus.OK);
		}
		return new ResponseEntity<>(customer,HttpStatus.NOT_FOUND);
	}
}
