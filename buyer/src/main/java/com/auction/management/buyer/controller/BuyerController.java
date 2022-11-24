package com.auction.management.buyer.controller;

import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auction.management.buyer.dto.BuyerDetailAggregate;
import com.auction.management.buyer.entity.BuyerInfo;
import com.auction.management.buyer.entity.BuyerResponse;
import com.auction.management.buyer.util.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Component
@RestController
@ComponentScan({Constants.BASE_PACKAGE})
@Consumes({"application/json", MediaType.APPLICATION_JSON})
@RequestMapping("/e-auction/api/v1/buyer")
@Api(value = Constants.BUYER_INFO)
public class BuyerController {
	@Autowired
	private BuyerDetailAggregate bidDetailService;

	@Autowired
	Validator validator;
	
	

	@ApiOperation(value = Constants.BUYER_INFO, response = ResponseEntity.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = Constants.BUYER_PLACED_SUCCESSFULLY),
	        @ApiResponse(code = 400, message = Constants.REQUEST_VALIDATION_ERROR)
	})
	@PostMapping(value = "/place-bid")
	public ResponseEntity<Object> addBidDetails(@Valid @RequestBody BuyerInfo buyerInfo) 
	{
		BuyerResponse buyerResponse=bidDetailService.addBidDetails(buyerInfo);
		return ResponseEntity.status(HttpStatus.CREATED).body(buyerResponse);
    }
	
	@ApiOperation(value = Constants.BID_UPDATE, response = ResponseEntity.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = Constants.BID_UPDATED_SUCCESSFULLY),
	        @ApiResponse(code = 400, message = Constants.REQUEST_VALIDATION_ERROR)
	})
	@PutMapping(value = "/place-bid/{productId}/{buyerEmailId}/{newBidAmount}")
	public ResponseEntity<Object> updateBidDetails(BuyerInfo buyerInfo,@PathVariable long productId,@PathVariable String buyerEmailId, @PathVariable double newBidAmount) 
	{
		BuyerResponse buyerResponse=bidDetailService.updateProductBidAmount(productId, buyerEmailId, newBidAmount);
		return ResponseEntity.status(buyerResponse.getStatusCode()).body(buyerResponse);
	}
}
