package com.alkatv.app.requests;


public class SearchRequest extends APIRequest {
	private String customerName;
	private Integer qrNo;
	private Integer id;
	private String mobNumber;

	public Integer getQrNo() {
		return qrNo;
	}

	public void setQrNo(Integer qrNo) {
		this.qrNo = qrNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMobNumber() {
		return mobNumber;
	}

	public void setMobNumber(String mobNumber) {
		this.mobNumber = mobNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
