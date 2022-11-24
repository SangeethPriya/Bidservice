package com.auction.management.bid.entity;

public class BidDetailResponse {
	
   private long product_id;
	
	private String product_name;
	
	private String short_desc;
	
	/**
	 * @return the product_id
	 */
	public long getProduct_id() {
		return product_id;
	}

	/**
	 * @param product_id the product_id to set
	 */
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}

	/**
	 * @return the product_name
	 */
	public String getProduct_name() {
		return product_name;
	}

	/**
	 * @param product_name the product_name to set
	 */
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	/**
	 * @return the short_desc
	 */
	public String getShort_desc() {
		return short_desc;
	}

	/**
	 * @param short_desc the short_desc to set
	 */
	public void setShort_desc(String short_desc) {
		this.short_desc = short_desc;
	}

	/**
	 * @return the detailed_desc
	 */
	public String getDetailed_desc() {
		return detailed_desc;
	}

	/**
	 * @param detailed_desc the detailed_desc to set
	 */
	public void setDetailed_desc(String detailed_desc) {
		this.detailed_desc = detailed_desc;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the starting_price
	 */
	public int getStarting_price() {
		return starting_price;
	}

	/**
	 * @param starting_price the starting_price to set
	 */
	public void setStarting_price(int starting_price) {
		this.starting_price = starting_price;
	}

	/**
	 * @return the bid_end_date
	 */
	public String getBid_end_date() {
		return bid_end_date;
	}

	/**
	 * @param bid_end_date the bid_end_date to set
	 */
	public void setBid_end_date(String bid_end_date) {
		this.bid_end_date = bid_end_date;
	}

	private String detailed_desc;
	
	private String category;
	
	private int starting_price;

	private String bid_end_date;


}
