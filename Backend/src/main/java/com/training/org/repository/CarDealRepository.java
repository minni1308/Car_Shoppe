package com.training.org.repository;
import com.training.org.model.CarDeal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDealRepository extends JpaRepository<CarDeal,Integer>{

	List<CarDeal> findByDealerName(String dealerName);
}
