package com.corporatebrokers.codingtest.service;

import java.util.concurrent.ExecutionException;

import com.corporatebrokers.codingtest.response.PhoneNbrUIResponse;

public interface AlphaNumericCalculatorService {


	PhoneNbrUIResponse findAlphaNUmericCombinations(String phoneNumber, int pageNumber, int pageSize) throws InterruptedException, ExecutionException;
}
