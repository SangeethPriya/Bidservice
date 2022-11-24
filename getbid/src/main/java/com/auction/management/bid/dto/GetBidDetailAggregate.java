package com.auction.management.bid.dto;

import java.util.List;

import com.auction.management.bid.entity.BidDetailResponse;
import com.auction.management.bid.entity.ProductEntity;

public interface GetBidDetailAggregate 
	{
		public List<ProductEntity> getProduct();
	}