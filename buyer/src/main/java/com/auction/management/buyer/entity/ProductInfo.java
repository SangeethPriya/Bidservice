package com.auction.management.buyer.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@ApiModel
@Builder
@Data
@Entity
@Table(name = "product_info")
public class ProductInfo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Tolerate
	public ProductInfo() {	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private long productId;
	
	@Column
	@ApiModelProperty(notes="Bid End Date for a Product")
	private String bidEndDate;
	
	@Column
    @Size(min = 4, message = "Product Name must be between 4 to 15 characters")
	@ApiModelProperty(notes="Product Name to be added")
	private String product_name;
	
	@Column
    @Size(min = 4, max = 15, message="Short Description must be between 4 to 15 characters")
	@ApiModelProperty(notes="Short Description of a Product")
	private String short_desc;
	
	@Column
	@ApiModelProperty(notes="Detailed Description of a Product")
	private String detailed_desc;
	
	@Column
	@ApiModelProperty(notes="Category of a Product")
	private String category;
	
}
