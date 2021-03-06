package com.ncsu.wolfwr.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ncsu.wolfwr.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	List<Customer> findAll();
	
	Optional<Customer> findById(Integer customerId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE Customer m SET m.rewardPoints= m.rewardPoints + :points  WHERE  m.customerId = :customerId")
	void updateRewardPointsOnTransaction(@Param(value = "points") Float points, @Param(value = "customerId") Integer customerId);
	
	@Query("select sum(t.cashbackReward) as rewards from Transaction t where year(t.purchaseDate) = :year and t.customerId = :customerId")
	Optional<Float> getRewardPointsByYear(@Param(value = "customerId") int customerId, @Param(value = "year") int year);
	
	@Query("Select s from Customer s where s.customerId = :customerId")
	Customer getCustomerById(@Param("customerId") Integer customerId);
}
