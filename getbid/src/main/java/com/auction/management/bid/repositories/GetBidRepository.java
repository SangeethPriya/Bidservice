package com.auction.management.bid.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auction.management.bid.entity.ProductEntity;


@Repository
public interface GetBidRepository extends JpaRepository<ProductEntity, Long>  {
	
}
