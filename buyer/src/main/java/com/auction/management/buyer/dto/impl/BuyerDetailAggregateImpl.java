package com.auction.management.buyer.dto.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.auction.management.buyer.dto.BuyerDetailAggregate;
import com.auction.management.buyer.entity.BuyerInfo;
import com.auction.management.buyer.entity.BuyerResponse;
import com.auction.management.buyer.entity.ProductInfo;
import com.auction.management.buyer.event.KafkaProducer;
import com.auction.management.buyer.exception.ResourceNotFoundException;
import com.auction.management.buyer.exception.ResourceOperationNotPermittedException;
import com.auction.management.buyer.repositories.BuyerRepository;
import com.auction.management.buyer.repositories.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class BuyerDetailAggregateImpl implements BuyerDetailAggregate 
{
	@Autowired
	private KafkaProducer kafkaProducer;
	
	
	@Autowired
	private BuyerRepository buyerRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	Optional<ProductInfo> productOptional;
	
	@Override
	public BuyerResponse addBidDetails(BuyerInfo buyerInfo) {
		BuyerResponse buyerResponse=new BuyerResponse();
		if(! Objects.isNull(buyerInfo.getProductId()))
		{
		productOptional = productRepository.findById(buyerInfo.getProductId());
		ProductInfo product = productOptional.get();
		buyerInfo.setDetailed_desc(product.getDetailed_desc());
		buyerInfo.setShort_desc(product.getShort_desc());
		buyerInfo.setCategory(product.getCategory());
		buyerInfo.setProduct_name(product.getProduct_name());
		buyerRepository.save(buyerInfo);
		}
		ObjectMapper objMapper = new ObjectMapper();
		try {
			//kafkaProducer.send(objMapper.writeValueAsString(buyerInfo));
			buyerResponse.setStatusCode(HttpStatus.CREATED);
			buyerResponse.setStatusMessage("Bid Placed Successfully");
		} catch (Exception ex) {
			log.error("Error while producing message to Kafka topic {}", ex.getMessage());
			buyerResponse.setStatusCode(HttpStatus.NOT_FOUND);
			buyerResponse.setStatusMessage("Error while producing message to Kafka topic");
		}
		return buyerResponse;
	}

	@Override
	public BuyerResponse updateProductBidAmount(Long productId, String buyerEmailId, double bidAmount)
	{
		BuyerResponse buyerResponse=new BuyerResponse();
		ObjectMapper objMapper = new ObjectMapper();
		BuyerInfo buyerInfo = null;
		
		try
		{
			 productOptional = productRepository.findById(productId);
			if(!productOptional.isPresent()) {
				buyerResponse.setStatusCode(HttpStatus.NOT_FOUND);
				buyerResponse.setStatusMessage("Product Id does not exist");
				throw new ResourceNotFoundException("Product Id does not exist");
			}
			
			ProductInfo product = productOptional.get();
			String bidEndDate=product.getBidEndDate();
			Date endDate=new SimpleDateFormat("dd-MM-yyyy").parse(bidEndDate);
			if(endDate.before(new Date())) {
				buyerResponse.setStatusCode(HttpStatus.BAD_REQUEST);
				buyerResponse.setStatusMessage("Can't update bid amount after product bid end date");
				throw new ResourceOperationNotPermittedException("Can't update bid amount after product bid end date");
			}
			
			 buyerInfo = buyerRepository.findBidOnProductForUser(productId,buyerEmailId);
			if(buyerInfo==null)
			{
				buyerResponse.setStatusCode(HttpStatus.NOT_FOUND);
				buyerResponse.setStatusMessage("Bid Details does not exist");
				throw new ResourceNotFoundException("Bid Details does not exist");
			}
			buyerInfo.setBidAmount(bidAmount);
			buyerRepository.save(buyerInfo);
			//kafkaProducer.send(objMapper.writeValueAsString(buyerInfo));
			buyerResponse.setStatusCode(HttpStatus.CREATED);
			buyerResponse.setStatusMessage("Bid Amount Updated Successfully");
		}
		catch (Exception e) {
		e.printStackTrace();
		}
		return buyerResponse;
	}
}
