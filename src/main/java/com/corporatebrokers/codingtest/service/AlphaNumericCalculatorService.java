package com.corporatebrokers.codingtest.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface AlphaNumericCalculatorService {


	List<String> findAlphaNUmericCombinations(String phoneNumber) throws InterruptedException, ExecutionException;
}
