package com.corporatebrokers.codingtest.response;

import java.util.List;

import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class PhoneNbrUIResponse {

	private List<String> phoneNbrs;
	private long totalCount;
	private long totalPages;
	private int totalElementsPerPage;
	private Pageable next;
	private Pageable previous;
}
