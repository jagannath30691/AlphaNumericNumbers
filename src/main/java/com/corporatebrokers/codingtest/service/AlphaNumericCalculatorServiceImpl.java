package com.corporatebrokers.codingtest.service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.corporatebrokers.codingtest.domain.AlphaNumericNumbers;
import com.corporatebrokers.codingtest.repository.AlphaNumericCalculatorRepository;
import com.corporatebrokers.codingtest.response.PhoneNbrUIResponse;

@Service
public class AlphaNumericCalculatorServiceImpl implements AlphaNumericCalculatorService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private Map<String, String[]> alphaNumericsMap;
	private AlphaNumericCalculatorRepository repository;
	
	@Autowired
	public AlphaNumericCalculatorServiceImpl(AlphaNumericCalculatorRepository repository) {
		super();
		this.repository = repository;
		alphaNumericsMap = new HashMap<>();
		alphaNumericsMap.put("1", new String[] {"1"});
		alphaNumericsMap.put("2", new String[] {"2", "A", "B", "C"});
		alphaNumericsMap.put("3", new String[] {"3", "D", "E", "F"});
		alphaNumericsMap.put("4", new String[] {"4", "G", "H", "I"});
		alphaNumericsMap.put("5", new String[] {"5", "J", "K", "L"});
		alphaNumericsMap.put("6", new String[] {"6", "M", "N", "O"});
		alphaNumericsMap.put("7", new String[] {"7", "P", "Q", "R", "S"});
		alphaNumericsMap.put("8", new String[] {"8", "T", "U", "V"});
		alphaNumericsMap.put("9", new String[] {"9", "W", "X", "Y", "Z"});
		alphaNumericsMap.put("0", new String[] {"0"});
	}



	@Override
	public PhoneNbrUIResponse findAlphaNUmericCombinations(String number, int pageNumber, int pageSize) throws InterruptedException, ExecutionException {
		Pageable paging = PageRequest.of(pageNumber, pageSize);
		Page<AlphaNumericNumbers> page = repository.findByPhoneNumber(number, paging);
		if(page != null && CollectionUtils.isEmpty(page.getContent())) {
			List<String> list1= Arrays.asList(alphaNumericsMap.get(String.valueOf(number.charAt(0))));
			for(int i=1; i<number.length(); i++) {
				List<String> list2 = Arrays.asList(alphaNumericsMap.get(String.valueOf(number.charAt(i))));
				Set<String> updatedList = calculate(list1, list2);
				list1 = new ArrayList<>(updatedList);
			}
			saveAlphaNumericNumbers(number, list1);
			page = repository.findByPhoneNumber(number, paging);
			return paginateAlphaNumericNumbers(number, pageNumber, pageSize, page);
		}
		PhoneNbrUIResponse response = paginateAlphaNumericNumbers(number, pageNumber, pageSize, page);
		return response;
	}
	
	private void saveAlphaNumericNumbers(String phoneNumber, List<String> list1) {
		AlphaNumericNumbers number = null;
		List<AlphaNumericNumbers> alphaNumericList = null;
		if(CollectionUtils.isNotEmpty(list1)) {
			for(String aNumber: list1) {
				number = new AlphaNumericNumbers();
				number.setPhoneNumber(phoneNumber);
				number.setAlphaNumericNumber(aNumber);
				if(alphaNumericList == null) {
					alphaNumericList = new ArrayList<AlphaNumericNumbers>();
				}
				alphaNumericList.add(number);
			}
			if(CollectionUtils.isNotEmpty(alphaNumericList)) {
				repository.saveAll(alphaNumericList);	
			}	
		}
	}

	private PhoneNbrUIResponse paginateAlphaNumericNumbers(String number, int pageNumber, int pageSize, Page<AlphaNumericNumbers> page) {
		PhoneNbrUIResponse response = new PhoneNbrUIResponse();
		if(page.hasContent()) {
			List<AlphaNumericNumbers> alphaNumericNumbers = page.getContent();
			for(AlphaNumericNumbers num : alphaNumericNumbers) {
				if(num.getPhoneNumber().equals(number)) {
					if(response.getPhoneNbrs() == null) {
						response.setPhoneNbrs(new ArrayList<String>());
					}
					List<String> output = response.getPhoneNbrs();
					output.add(num.getAlphaNumericNumber());
				}
			}
			response.setTotalElementsPerPage(page.getNumberOfElements());
			LOGGER.debug("total number of contents on Page {} : {}"
					, pageNumber, page.getNumberOfElements());
			response.setTotalPages(page.getTotalPages());
			response.setTotalCount(page.getTotalElements());
			if(page.hasNext()) {
				Pageable next = page.nextPageable();
				response.setNext(next);
			}
			if(page.hasPrevious()) {
				Pageable previous = page.previousPageable();
				response.setPrevious(previous);
			}
		}
		return response;
	}

	private Set<String> calculate(List<String> list1, List<String> list2) {
		Set<String> modifiedList = new HashSet<>();
		for(String firstListItem : list1) {
			for(String secondListItem : list2) {
				modifiedList.add(firstListItem.concat(secondListItem));
			}
		}
		return modifiedList;
	}
}
