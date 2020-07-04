package com.corporatebrokers.codingtest.controller;

import java.lang.invoke.MethodHandles;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corporatebrokers.codingtest.request.PhoneNbrUIRequest;
import com.corporatebrokers.codingtest.response.PhoneNbrUIResponse;
import com.corporatebrokers.codingtest.service.AlphaNumericCalculatorService;


@RestController
@RequestMapping("/calculator")
public class AlphaNumericCalculatorController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private AlphaNumericCalculatorService calculatorService;
	
	@Autowired
	public AlphaNumericCalculatorController(AlphaNumericCalculatorService calculatorService) {
		super();
		this.calculatorService = calculatorService;
	}

	@PostMapping("/calculate")
	public PhoneNbrUIResponse alphaNumericNumbers(@RequestBody PhoneNbrUIRequest request,
												@RequestParam("pageNumber") int pageNumber, 
									            @RequestParam("pageSize") int pageSize){
		PhoneNbrUIResponse response = null;
		
		try {
			if(request != null && StringUtils.isNotBlank(request.getPhoneNumber())
					&& pageNumber >= 0 && pageSize > 0) {
				
				response = calculatorService.findAlphaNUmericCombinations(request.getPhoneNumber(), pageNumber, pageSize);
				if(response != null) {
					LOGGER.debug("Possible alphanumeric combination for {} is {}",request.getPhoneNumber(), response.getPhoneNbrs());
				} else {
					LOGGER.error("There are no alphanumeric combination for {}", request.getPhoneNumber());
				}	
			} else {
				LOGGER.error("Invalid phone number");
			}
			
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		return response;
	}
	
}
