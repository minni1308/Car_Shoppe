package com.training.org.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="loans", schema="test")
public class Loan {

	@Id
	@Column(name="ref_id")
	private String refId;
	
	@Column(name="selected_file")
	private String selected_file;
	
	@Column(name="car_name")
	private String car_name;
	
	@Column(name="loan_amount")
	private double loan_amount;
	
	@Column(name="customer_id")
	private String customerId;
	
	@Column(name="emi")
	private double emi;
	
	@Column(name="car_cost")
	private double car_cost;
	
	@Column(name="tenure")
	private double tenure;
	
	@Column(name="status")
	private String status;
	
	@Lob
	@Column(name="selected_file_data")
	private byte[] selected_file_data;


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Loan() {}
	
	
	public String getRefId() {
		return refId;
	}

	public void setRefId(String ref_id) {
		this.refId = ref_id;
	}

	public String getSelectedFile() {
		return selected_file;
	}

	public void setSelectedFile(String selected_file) {
		this.selected_file = selected_file;
	}

	public String getCarName() {
		return car_name;
	}

	public void setCarName(String car_name) {
		this.car_name = car_name;
	}

	public double getLoanAmount() {
		return loan_amount;
	}

	public void setLoanAmount(double loan_amount) {
		this.loan_amount = loan_amount;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public double getEmi() {
		return emi;
	}

	public void setEmi(double emi) {
		this.emi = emi;
	}

	public double getCarCost() {
		return car_cost;
	}

	public void setCarCost(double car_cost) {
		this.car_cost = car_cost;
	}

	public double getTenure() {
		return tenure;
	}

	public void setTenure(double tenure) {
		this.tenure = tenure;
	}


	public byte[] getSelected_file_data() {
		return selected_file_data;
	}


	public void setSelected_file_data(byte[] selected_file_data) {
		this.selected_file_data = selected_file_data;
	}


	public Loan(String refId, String selected_file, String car_name, double loan_amount, String customerId, double emi,
			double car_cost, double tenure, String status, byte[] selected_file_data) {
		super();
		this.refId = refId;
		this.selected_file = selected_file;
		this.car_name = car_name;
		this.loan_amount = loan_amount;
		this.customerId = customerId;
		this.emi = emi;
		this.car_cost = car_cost;
		this.tenure = tenure;
		this.status = status;
		this.selected_file_data = selected_file_data;
	}


	@Override
	public String toString() {
		return "Loan [refId=" + refId + ", selected_file=" + selected_file + ", car_name=" + car_name + ", loan_amount="
				+ loan_amount + ", customerId=" + customerId + ", emi=" + emi + ", car_cost=" + car_cost + ", tenure="
				+ tenure + ", status=" + status + ", selected_file_data=" + Arrays.toString(selected_file_data) + "]";
	}
	
	
}
