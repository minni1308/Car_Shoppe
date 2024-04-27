package com.training.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.org.model.CarDeal;
import com.training.org.model.Customer;
import com.training.org.model.Loan;
import com.training.org.service.CarDealService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController    
public class DealController   
{    
	@Autowired    
	private CarDealService carDealService;     
	@RequestMapping("/cars")    
	public List<CarDeal> getAllDeals()  
	{    
		return carDealService.getAllCarDeals();    
	} 
	
	@GetMapping("/cardeals/{id}")    
	public ResponseEntity<CarDeal> getCarDealDetails(@PathVariable("id") int carDealId)  
	{    
		CarDeal carDeal = carDealService.getCarDealById(carDealId); 
		if(carDeal!=null) {
			return new ResponseEntity<>(carDeal,HttpStatus.OK);
		}
		return new ResponseEntity<>(carDeal,HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/cardeals/dealer/{name}") 
	public List<CarDeal> getCarDealByDealerName(@PathVariable("name") String dealerName) {
		return carDealService.getCarDealByDealerName(dealerName);
	}
	
	
	@RequestMapping(value = "/cardeals/addDeal", method = RequestMethod.POST, headers="Content-Type=multipart/form-data")
	public CarDeal addDeal(@RequestParam("model") String model, @RequestParam(value = "uploadedImage", required = false) MultipartFile file) throws Exception {
		//Loan loan = new Loan();
		ObjectMapper mapper = new ObjectMapper();
		CarDeal carDeal = mapper.readValue(model, CarDeal.class);
		carDeal.setCarImage(file.getBytes());
		carDeal = carDealService.addCarDeal(carDeal);
		System.out.println("called");
		return carDeal;
	}
	
	  @PutMapping("/cardeals/editDeal/{id}")
	  public ResponseEntity<CarDeal> updateDeal(@PathVariable("id") int id, @RequestParam("carDealData") String carDealData, @RequestParam(value = "uploadedImage", required = false) MultipartFile file) throws Exception {
	    CarDeal carDeal = carDealService.getCarDealById(id);

	    if (carDeal!=null) {
			ObjectMapper mapper = new ObjectMapper();
			CarDeal carDealNew = mapper.readValue(carDealData, CarDeal.class);
			carDealNew.setId(carDeal.getId());
			if(file!=null)
				carDealNew.setCarImage(file.getBytes());
			else
				carDealNew.setCarImage(carDeal.getCarImage());
	      return new ResponseEntity<>(carDealService.addCarDeal(carDealNew), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
