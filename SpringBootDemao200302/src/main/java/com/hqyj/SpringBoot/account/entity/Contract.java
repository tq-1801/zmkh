package com.hqyj.SpringBoot.account.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Contract {

	// 合同登记表
	private int contractId;
	// 客户名称
	private String contractName;
	// 合同类别
	private String category;
	// 销售人员
	private String person;
	// 签约日期
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date dates;
	// 合同金额
	private String moneyt;
	// 回款金额
	private String paymentv;
	// 合同期限
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date period;

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public Date getDates() {
		return dates;
	}

	public void setDates(Date dates) {
		this.dates = dates;
	}

	public String getMoneyt() {
		return moneyt;
	}

	public void setMoneyt(String moneyt) {
		this.moneyt = moneyt;
	}

	public String getPaymentv() {
		return paymentv;
	}

	public void setPaymentv(String paymentv) {
		this.paymentv = paymentv;
	}

	public Date getPeriod() {
		return period;
	}

	public void setPeriod(Date period) {
		this.period = period;
	}

}
