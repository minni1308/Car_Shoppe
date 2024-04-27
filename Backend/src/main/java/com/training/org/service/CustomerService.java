package com.training.org.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.org.model.Customer;
import com.training.org.repository.CustomerRepository;

@Service 
public class CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> getAllCustomers()  
	{    
		List<Customer> customers = new ArrayList<>();    
		customerRepository.findAll().forEach(customer->customers.add(customer));  
		return customers;    
	} 
	public Customer getCustomerById(String id) {
		Optional<Customer> customer=customerRepository.findById(id);
		if(customer.isPresent())
			return customer.get();
		else
			return null;
	}
	
	public void setCustomerCapacity(Customer customer) {
		double totalIncome = customer.getIncome() + customer.getCoapplicantIncome();
		int points = 0;
		if(customer.getMaritalStatus()==null)
			points+=2;
		else if(customer.getMaritalStatus().equalsIgnoreCase("No"))
			points+=2;
		else
			points+=1;
		String dependents;
		if(customer.getDependents()==null)
			dependents="";
		else
		dependents= customer.getDependents();
		switch(dependents){
		case "0": points+=4; break;
		case "1": points+=3; break;
		case "2": points+=2; break;
		default: points+=1;
		}
		
		if(customer.getEducation().equalsIgnoreCase("Graduate"))
			points+=2;
		else
			points+=1;
		if(customer.getSelfEmployed()==null)
			points+=2;
		else if(customer.getSelfEmployed().equalsIgnoreCase("No"))
			points+=2;
		else
			points+=1;
		if(customer.getCreditHistory()==null)
			points+=0;
		else if(customer.getCreditHistory()==1)
			points+=5;
		else
			points+=0;
		
		switch(customer.getPropertyArea()){
		case "Rural": points+=3; break;
		case "Semiurban": points+=2; break;
		default: points+=1;
		}
		
		int expensePercent = 0;
		if(points<11)
			expensePercent = 30;
		else if(points<16)
			expensePercent = 25;
		else expensePercent = 20;
		
		double oldEMI = 0;
		double grossIncome = totalIncome * (100-expensePercent) / 100;
		double tds = grossIncome*0.6;
		if(customer.getLoanAmount()!=null) {
			double rate = 0.08/12;
			double loanTerm;
			if(customer.getLoanAmountTerm()==null)
				loanTerm = 360;
			else loanTerm = customer.getLoanAmountTerm();
			double temp = Math.pow(1+rate,loanTerm );
			oldEMI =   (customer.getLoanAmount() * 1000 * rate * temp) / (temp-1);
		}
		double capacity = Math.round((tds-oldEMI)*100.0)/100.0;
		customer.seteMICapacity(capacity);
	}
}
