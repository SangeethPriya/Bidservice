package com.auction.management.buyer.entity;

import javax.persistence.Column;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
public class BuyerResponse {
	
	@Tolerate
	public BuyerResponse() {
	}

	@Column
	private HttpStatus statusCode;

	@Column
	private String statusMessage;

}