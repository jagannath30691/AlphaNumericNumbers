package com.corporatebrokers.codingtest.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corporatebrokers.codingtest.service.AlphaNumericCalculatorService;


@RestController
@RequestMapping("/calculator")
public class AlphaNumericCalculatorController {
	
	private AlphaNumericCalculatorService calculatorService;
	
	@Autowired
	public AlphaNumericCalculatorController(AlphaNumericCalculatorService calculatorService) {
		super();
		this.calculatorService = calculatorService;
	}

	@PostMapping("/calculate")
	public List<String> alphaNumericNumbers(@RequestBody String phoneNumber){
		List<String> result = null;
		try {
			phoneNumber = "72792684";
			result = calculatorService.findAlphaNUmericCombinations(phoneNumber);
			Set<String> a = new HashSet<>(result);
			if(CollectionUtils.isNotEmpty(result)) {
				for(String s : a) {
					System.out.println(s);
				}
			}
			System.out.println("count: "+a.size());
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		return result;
	}
}
