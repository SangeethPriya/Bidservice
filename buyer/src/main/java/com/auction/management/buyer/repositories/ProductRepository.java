package com.auction.management.buyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auction.management.buyer.entity.ProductInfo;

@Repository
public interface ProductRepository extends JpaRepository<ProductInfo, Long>  {

}
