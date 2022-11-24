package com.auction.management.buyer.dto;

import com.auction.management.buyer.entity.BuyerInfo;
import com.auction.management.buyer.entity.BuyerResponse;

public interface BuyerDetailAggregate 
	{
		public BuyerResponse addBidDetails(BuyerInfo buyerInfo);
		
		public BuyerResponse updateProductBidAmount(Long productId, String buyerEmailId, double bidAmount);
	}