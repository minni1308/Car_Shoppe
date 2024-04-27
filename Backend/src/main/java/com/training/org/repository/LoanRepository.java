package com.training.org.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.training.org.model.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan,String> {

	//@Query("SELECT l FROM loans l WHERE l.ref_id = (:refId)")
	Loan findByRefId(String refId);
	List<Loan> getByCustomerId(String customerId);
}
