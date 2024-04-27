package com.training.org.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.org.repository.LoanRepository;
import com.training.org.model.Loan;



@Service
public class LoanService {
	
	@Autowired
	private LoanRepository loanRepository;
	
	public List<Loan> getAllLoans(){
		
		return (ArrayList<Loan>) loanRepository.findAll();
	}

	public Loan createLoan( Loan loan) throws Exception {
		
		//String uuid = String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
		String uuid = UUID.randomUUID().toString();
		loan.setRefId(uuid);
		loan.setStatus("Applied");
		
		return loanRepository.save(loan);
	}
	
	public Loan getLoanByRefId(String ref_id) throws Exception {
		return loanRepository.findByRefId(ref_id);
	}
	public Loan deleteLoan(String ref_id) throws Exception {
		Loan deleted = loanRepository.findByRefId(ref_id);
		loanRepository.delete(deleted);
		return deleted;
	}
	
	public List<Loan> getCustomerLoans(String customerId){
		
		return loanRepository.getByCustomerId(customerId);
	}
	
	public Loan updateStatusApprove(String refId) {
		Loan updatedLoan = loanRepository.findByRefId(refId);
		//loan.setStatus(loanDetails.getStatus());
		//Loan updatedLoan = loanRepository.save(loanDetails);
		updatedLoan.setStatus("Approved");
		return loanRepository.save(updatedLoan);
	}
	
	public Loan updateStatusReject(String refId) {
		Loan updatedLoan = loanRepository.findByRefId(refId);
		//loan.setStatus(loanDetails.getStatus());
		//Loan updatedLoan = loanRepository.save(loanDetails);
		updatedLoan.setStatus("Rejected");
		return loanRepository.save(updatedLoan);
	}
	
}
