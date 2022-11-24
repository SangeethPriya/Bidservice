package com.auction.management.buyer.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

@Data
@Builder
@Entity
@Table(name = "buyer_info")
public class BuyerInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Tolerate
	public BuyerInfo() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "buyer_id")
	private long buyerId;
	
	@NotNull
	@Column(name = "product_id")
	private long productId; 
	
	@Column
	@NotEmpty
	@Size(min = 5, max = 30, message = "Buyer First Name must be between 5 and 30 characters")	
	private String buyerFirstName;
	
	@NotEmpty
	@Size(min = 3, max = 25, message = "Buyer Last Name must be between 5 and 30 characters")	
	@Column
	private String buyerLastName;
	
	@NotEmpty(message = "buyer_address cannot be null")
	@Column
	private String buyerAddress;
	
	@NotEmpty(message = "buyer_city cannot be null")
	@Column
	private String buyerCity;
	
	@NotEmpty(message = "buyer_state cannot be null")
	@Column
	private String buyerState;
	
	@Column
	@Min(value=0,message="Buyer Pin should not be empty")
	private Integer buyerPin;
	
	@Column
	@Size(max = 10, message = "buyer_phone Number Should Contain 10 Digits")	
	private String buyerPhone;
	
	@Column
	@NotEmpty
	@Email(message = "buyer_email should be valid")
	private String buyerEmail;
	
	@Column
	@DecimalMin("1.0")
	private double bidAmount;
	
	
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
