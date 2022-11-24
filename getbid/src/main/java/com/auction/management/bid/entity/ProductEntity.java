package com.auction.management.bid.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class ProductEntity  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Tolerate
	public ProductEntity() {	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	//@ApiModelProperty(notes="Database generated Product ID")
	private long product_id;
	
	@Column
	@NotNull
    @Size(min = 4, message = "Product Name must be between 4 to 15 characters")
	@ApiModelProperty(notes="Product Name to be added")
	private String product_name;
	
	@Column
	@NotNull
    @Size(min = 4, max = 15, message="Short Description must be between 4 to 15 characters")
	@ApiModelProperty(notes="Short Description of a Product")
	private String short_desc;
	
	@Column
	@ApiModelProperty(notes="Detailed Description of a Product")
	private String detailed_desc;
	
	@Column
	@ApiModelProperty(notes="Category of a Product")
	private String category;
	
	@Column
	@ApiModelProperty(notes="Starting Price of a Product")
	private int starting_price;
	
	@Column
	@ApiModelProperty(notes="Bid End Date for a Product")
	private String bid_end_date;
	
	@Column
	private Double bid_amount;
}

