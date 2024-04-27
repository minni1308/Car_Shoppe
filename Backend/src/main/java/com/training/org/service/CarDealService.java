package com.training.org.service;
import com.training.org.model.CarDeal;
import com.training.org.model.Customer;
import com.training.org.model.Loan;
import com.training.org.repository.CarDealRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service 
public class CarDealService {
	@Autowired
	private CarDealRepository carDealRepository;
	
	public List<CarDeal> getAllCarDeals()  
	{    
		List<CarDeal> carDeals = new ArrayList<>();    
		carDealRepository.findAll().forEach(deal->carDeals.add(deal));  
		return carDeals;    
	}    
	public CarDeal addCarDeal(CarDeal carDeal)  
	{    
		return carDealRepository.save(carDeal);	
	}
	
	public CarDeal getCarDealById(int id) {
		Optional<CarDeal> carDeal=carDealRepository.findById(id);
		if(carDeal.isPresent())
			return carDeal.get();
		else
			return null;
	}
	
	public List<CarDeal> getCarDealByDealerName(String dealerName) {
		List<CarDeal> carDeals=carDealRepository.findByDealerName(dealerName);
		return carDeals;
	}
}
