package com.auction.management.bid.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auction.management.bid.dto.GetBidDetailAggregate;
import com.auction.management.bid.entity.ProductEntity;
import com.auction.management.bid.util.Constants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;


@Component
@RestController
@ComponentScan({"com.auction.management.getbid"})
@Consumes({"application/json", MediaType.APPLICATION_JSON})
@RequestMapping("/e-auction/api/v1/buyer")
@Slf4j
public class GetbidController {

    @Autowired
	private GetBidDetailAggregate getBidDetailAggregate;
	
	@ApiOperation(value = Constants.GET_SELLER_PRODUCT, response = ResponseEntity.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 201, message = Constants.PRODUCT_CREATED_SUCCESSFULLY),
	        @ApiResponse(code = 400, message = Constants.REQUEST_VALIDATION_ERROR)
	})
	@GetMapping(value = "/get-product")
	public ResponseEntity<List<ProductEntity>> getProductDetails() 
	{
		log.info("Creating Product and Seller Info..");		
		List<ProductEntity> productResponse = getBidDetailAggregate.getProduct();
		return ResponseEntity.status(HttpStatus.OK).body(productResponse);
    }
}
