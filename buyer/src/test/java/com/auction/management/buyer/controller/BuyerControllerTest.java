package com.auction.management.buyer.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.auction.management.buyer.controller.BuyerController;
import com.auction.management.buyer.dto.BuyerDetailAggregate;
import com.auction.management.buyer.entity.BuyerInfo;
import com.auction.management.buyer.entity.BuyerResponse;


@RunWith(MockitoJUnitRunner.class)
public class BuyerControllerTest {
	
	private BuyerInfo buyerInfo;
	private BuyerResponse buyerResponse;

	@InjectMocks
	private BuyerController buyerController;
	
	@Mock
	BuyerDetailAggregate buyerDetailAggregate;
	
	@Before
	public void setUp() {
		 buyerInfo=new BuyerInfo();
		 buyerResponse=new BuyerResponse();
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
		
		Mockito.when(buyerDetailAggregate.addBidDetails(buyerInfo)).thenReturn(buyerResponse);
		assertNotNull(buyerController.addBidDetails(buyerInfo));
	}

	@Test
	public void updateBidDetailsTest()
	{
		buyerInfo.setProductId(2);
		buyerInfo.setBuyerEmail("xyz@gmail.com");
		buyerInfo.setBidAmount(89.0);
		buyerResponse.setStatusCode(HttpStatus.CREATED);
		
		Mockito.when(buyerDetailAggregate.updateProductBidAmount(buyerInfo.getProductId(), buyerInfo.getBuyerEmail(), buyerInfo.getBidAmount())).thenReturn(buyerResponse);
		assertNotNull(buyerController.updateBidDetails(buyerInfo, buyerInfo.getProductId(), buyerInfo.getBuyerEmail(), buyerInfo.getBidAmount()));
	}
	

}
