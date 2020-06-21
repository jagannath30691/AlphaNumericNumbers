package com.corporatebrokers.codingtest.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.corporatebrokers.codingtest.domain.AlphaNumericNumbers;

@Repository
public interface AlphaNumericCalculatorRepository extends PagingAndSortingRepository<AlphaNumericNumbers, Long> {

	Page<AlphaNumericNumbers> findByPhoneNumber(String number, Pageable pageable);
	List<AlphaNumericNumbers> findByPhoneNumber(String number);
	
}
