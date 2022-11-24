package com.auction.management.buyer.dto.impl;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.auction.management.buyer.dto.BuyerDetailAggregate;
import com.auction.management.buyer.entity.BuyerInfo;
import com.auction.management.buyer.entity.BuyerResponse;
import com.auction.management.buyer.entity.ProductInfo;
import com.auction.management.buyer.repositories.BuyerRepository;
import com.auction.management.buyer.repositories.ProductRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BuyerDetailAggregateImplTest {
	private BuyerInfo buyerInfo;
	private BuyerResponse buyerResponse;
	private ProductInfo productInfo;
	
	
	@InjectMocks
	BuyerDetailAggregateImpl buyerDetailAggregateImpl;
	
	@Mock
	BuyerDetailAggregate buyerDetailAggregate;
	
	@Mock
	BuyerRepository buyerRepository;
	
	@Mock
	ProductRepository productRepository;
	
	@Before
	public void setUp() {
		 buyerInfo=new BuyerInfo();
		 buyerResponse=new BuyerResponse();
		 productInfo=new ProductInfo();
	}
	
	@Test
	public void addBidDetailsTest()
	{
		buyerInfo.setBuyerFirstName("Lenovo");
		buyerInfo.setBuyerLastName("Laptop");
		buyerInfo.setBuyerAddress("Laptop Provided by Lenovo..");
		buyerInfo.setBuyerCity("Electronics");
		buyerInfo.setBuyerState("Tamilnadu");
		buyerInfo.setBuyerPin(5000);
		buyerInfo.setBuyerPhone("7845868869");
		buyerInfo.setBuyerEmail("xyz@gmail.com");
		buyerInfo.setBidAmount(90.89);
		buyerInfo.setProductId(2);
		Mockito.when(buyerDetailAggregate.addBidDetails(Mockito.any())).thenReturn(buyerResponse);
		Mockito.when(productRepository.findById(buyerInfo.getProductId())).thenReturn(Optional.of(productInfo));
		buyerDetailAggregateImpl.addBidDetails(buyerInfo);
	}
	
	@Test
	public void updateBidDetailsTest()
	{
		buyerInfo.setProductId(2);
		buyerInfo.setBuyerEmail("xyz@gmail.com");
		buyerInfo.setBidAmount(90.89);
		buyerResponse.setStatusCode(HttpStatus.CREATED);
		productInfo.setBidEndDate("12-12-2022");

		Mockito.when(productRepository.findById(buyerInfo.getProductId())).thenReturn(Optional.of(productInfo));
		Mockito.when(buyerRepository.findBidOnProductForUser(buyerInfo.getProductId(),buyerInfo.getBuyerEmail())).thenReturn(buyerInfo);
		buyerDetailAggregateImpl.updateProductBidAmount(buyerInfo.getProductId(), buyerInfo.getBuyerEmail(), buyerInfo.getBidAmount());
    }
	
	
	@Test
	public void UpdateBidwithoutProduct()
	{
		Mockito.when(productRepository.findById(buyerInfo.getProductId())).thenReturn(Optional.ofNullable(null));
    	BuyerResponse buyerResponse=  buyerDetailAggregateImpl.updateProductBidAmount(buyerInfo.getProductId(), buyerInfo.getBuyerEmail(), buyerInfo.getBidAmount());
    	assertEquals("Product Id does not exist", buyerResponse.getStatusMessage());
	}
	
	
	@Test
	public void UpdateBidwithoutInvalidDate()
	{
		buyerInfo.setProductId(2);
		productInfo.setBidEndDate("12-12-2002");
		
		Mockito.when(productRepository.findById(buyerInfo.getProductId())).thenReturn(Optional.of(productInfo));
    	BuyerResponse buyerResponse=  buyerDetailAggregateImpl.updateProductBidAmount(buyerInfo.getProductId(), buyerInfo.getBuyerEmail(), buyerInfo.getBidAmount());
    	assertEquals("Can't update bid amount after product bid end date", buyerResponse.getStatusMessage());
		}
	
	@Test
	public void UpdateBidwithoutBuyerDetails()
	{
		buyerInfo.setProductId(2);
		productInfo.setBidEndDate("12-12-2022");
		
		Mockito.when(productRepository.findById(buyerInfo.getProductId())).thenReturn(Optional.of(productInfo));
		Mockito.when(buyerRepository.findBidOnProductForUser(buyerInfo.getProductId(),buyerInfo.getBuyerEmail())).thenReturn(null);
    	BuyerResponse buyerResponse=  buyerDetailAggregateImpl.updateProductBidAmount(buyerInfo.getProductId(), buyerInfo.getBuyerEmail(), buyerInfo.getBidAmount());
    	assertEquals("Bid Details does not exist", buyerResponse.getStatusMessage());
	}
	
}
