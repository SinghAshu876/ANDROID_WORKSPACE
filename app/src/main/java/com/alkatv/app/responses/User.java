package com.alkatv.app.responses;

import java.util.Date;
import java.util.List;


public class User {
	private Integer id;
	private String customerName;
	private String street;
	private String sector;
	private Date doc;
	private String mobNumber;
	private String setTopBox;
	private String active;
	private Integer connectionCharge;
	private String casNumber;
	private Integer qrNo;
	private Integer backDues;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public Date getDoc() {
		return doc;
	}

	public void setDoc(Date doc) {
		this.doc = doc;
	}

	public String getMobNumber() {
		return mobNumber;
	}

	public void setMobNumber(String mobNumber) {
		this.mobNumber = mobNumber;
	}

	public String getSetTopBox() {
		return setTopBox;
	}

	public void setSetTopBox(String setTopBox) {
		this.setTopBox = setTopBox;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Integer getConnectionCharge() {
		return connectionCharge;
	}

	public void setConnectionCharge(Integer connectionCharge) {
		this.connectionCharge = connectionCharge;
	}

	public String getCasNumber() {
		return casNumber;
	}

	public void setCasNumber(String casNumber) {
		this.casNumber = casNumber;
	}

	public Integer getQrNo() {
		return qrNo;
	}

	public void setQrNo(Integer qrNo) {
		this.qrNo = qrNo;
	}

	public Integer getBackDues() {
		return backDues;
	}

	public void setBackDues(Integer backDues) {
		this.backDues = backDues;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
