package com.training.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.org.model.Customer;
import com.training.org.model.Loan;
import com.training.org.service.LoanService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController   
public class LoanController {

	@Autowired
	LoanService loanService;
	
	@GetMapping("/loans")
	public List<Loan> getLoans(){
		return loanService.getAllLoans();
	}
	
	@RequestMapping(value = "/loans/add", method = RequestMethod.POST, headers="Content-Type=multipart/form-data")
	public Loan createLoans(@RequestParam("model")String model, @RequestParam(value = "uploadedFile", required = false) MultipartFile file) throws Exception {
		//Loan loan = new Loan();
		ObjectMapper mapper = new ObjectMapper();
		Loan loan = mapper.readValue(model, Loan.class);
		loan.setSelected_file_data(file.getBytes());
		loan = loanService.createLoan(loan);
		System.out.println("called");
		return loan;
	}
	
//	@PostMapping("/loans/add")
//	public Loan createLoans(@RequestBody Loan loan) throws Exception {
//		loan = loanService.createLoan(loan);
//		System.out.println("called");
//		return loan;
//	}
	
	@GetMapping("/loans/get/{id}")    
	public List<Loan> getCustomerLoansById(@PathVariable("id") String customerId)  
	{    
		List<Loan> loans = loanService.getCustomerLoans(customerId); 
		
		return loans;
	}
	
	  @GetMapping("/loans/files/{refId}")
	  public ResponseEntity<byte[]> getFile(@PathVariable("refId") String refId)  throws Exception {
	    Loan loan = loanService.getLoanByRefId(refId);

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + loan.getSelectedFile() + "\"")
	        .body(loan.getSelected_file_data());
	  }
	
	@DeleteMapping("/loans/delete/{refId}")    
	public Loan deleteLoanByRef(@PathVariable("refId") String refId) throws Exception  
	{    
		//List<Loan> loans = 
		Loan loan = loanService.deleteLoan(refId); 
		
		return loan;
	}
	
	@DeleteMapping("/loans/approve/{refId}")
	public Loan updateStatusApprove(@PathVariable("refId") String refId) throws  Exception{
		return loanService.updateStatusApprove(refId);
	}
	
	@DeleteMapping("/loans/reject/{refId}")
	public Loan updateStatusReject(@PathVariable("refId") String refId) throws  Exception{
		return loanService.updateStatusReject(refId);
	}
	
}
