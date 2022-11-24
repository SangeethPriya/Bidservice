package com.auction.management.buyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.auction.management.buyer.entity.BuyerInfo;

@Repository
public interface BuyerRepository extends JpaRepository<BuyerInfo, Long>  {
	@Query(value="select * from auction_details.buyer_info buyer where buyer.product_id =?1",nativeQuery = true)
public BuyerInfo findBidOnProductForUser(Long productId, String buyerEmailId);


}
