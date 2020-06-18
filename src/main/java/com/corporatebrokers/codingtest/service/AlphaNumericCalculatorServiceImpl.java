package com.corporatebrokers.codingtest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

@Service
public class AlphaNumericCalculatorServiceImpl implements AlphaNumericCalculatorService {
	
	private Map<String, String[]> alphaNumericsMap;
	
	

	public AlphaNumericCalculatorServiceImpl() {
		super();
		alphaNumericsMap = new HashMap<>();
		alphaNumericsMap.put("1", new String[] {"1", "1", "1", "1"});
		alphaNumericsMap.put("2", new String[] {"2", "A", "B", "C"});
		alphaNumericsMap.put("3", new String[] {"3", "D", "E", "F"});
		alphaNumericsMap.put("4", new String[] {"4", "G", "H", "I"});
		alphaNumericsMap.put("5", new String[] {"5", "J", "K", "L"});
		alphaNumericsMap.put("6", new String[] {"6", "M", "N", "O"});
		alphaNumericsMap.put("7", new String[] {"7", "P", "Q", "R", "S"});
		alphaNumericsMap.put("8", new String[] {"8", "T", "U", "V"});
		alphaNumericsMap.put("9", new String[] {"9", "W", "X", "Y", "Z"});
		alphaNumericsMap.put("0", new String[] {"0", "0", "0", "0"});
	}

	@Override
	public List<String> findAlphaNUmericCombinations(String number) throws InterruptedException, ExecutionException {
		List<String> list1= Arrays.asList(alphaNumericsMap.get(String.valueOf(number.charAt(0))));
		for(int i=1; i<number.length(); i++) {
			List<String> list2 = Arrays.asList(alphaNumericsMap.get(String.valueOf(number.charAt(i))));
			Set<String> updatedList = calculate(list1, list2);
			list1 = new ArrayList<>(updatedList);
		}
		return list1;
	}
	
	private Set<String> calculate(List<String> list1, List<String> list2) {
		Set<String> modifiedList = new HashSet<>();
		StringBuilder sb = new StringBuilder();
		for(String firstListItem : list1) {
			for(String secondListItem : list2) {
				sb = sb.delete(0, sb.length());
				sb.append(firstListItem).append(secondListItem);
				modifiedList.add(sb.toString());
			}
		}
		return modifiedList;
	}

}
