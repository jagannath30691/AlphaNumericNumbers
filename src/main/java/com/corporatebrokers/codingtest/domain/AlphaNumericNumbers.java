package com.corporatebrokers.codingtest.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;

@Data
@Entity
public class AlphaNumericNumbers {
	
	@Id
	@GeneratedValue
	@Getter
	private Long id;
	@Column
	private String phoneNumber;
	@Column
	private String alphaNumericNumber;
	
}
