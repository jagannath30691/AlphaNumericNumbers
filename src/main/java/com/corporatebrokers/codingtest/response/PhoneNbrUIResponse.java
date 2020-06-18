package com.corporatebrokers.codingtest.response;

import java.util.List;

import lombok.Data;

@Data
public class PhoneNbrUIResponse {

	private List<String> phoneNbrs;
	private int totalCount;
}
