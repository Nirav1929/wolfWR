package com.ncsu.wolfwr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ncsu.wolfwr.entity.Discount;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer>{

	List<Discount> findAll();
	
	Optional<Discount> findById(Integer discountId);
}
