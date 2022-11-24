package com.auction.management.bid.entity;

import java.io.Serializable;

import lombok.Data;


@Data
public class BidData implements Serializable{
	private static final long serialVersionUID = 1L;
	private String bidId;
	private String productId;
	private double bidAmount;
	private String email;
	private String name;
	private String phone;

	@Override
	public boolean equals(Object o) {
		BidData d = (BidData) o;
		if (d.getEmail().equals(this.email) && d.getProductId().equals(this.productId)) {
			return true;
		}
		return false;
	}
}
