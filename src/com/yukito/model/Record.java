package com.yukito.model;

import java.math.BigDecimal;

/**
 * 订单实体
 * @author wjw20
 *
 */
public class Record {
	private String orderNum; // 订单号
	private String payer; // 交易人
	private BigDecimal paymentAmount; // 交易额
	private String paymentTime; // 交易时间
	
	public Record() {
		super();
	}
	
	public Record(String orderNum, String payer) {
		super();
		this.orderNum = orderNum;
		this.payer = payer;
	}

	public Record(String orderNum, String payer, BigDecimal paymentAmount, String paymentTime) {
		super();
		this.orderNum = orderNum;
		this.payer = payer;
		this.paymentAmount = paymentAmount;
		this.paymentTime = paymentTime;
	}

	public String getOrderNum() {
		return orderNum;
	}
	
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	
	public String getPayer() {
		return payer;
	}
	
	public void setPayer(String payer) {
		this.payer = payer;
	}
	
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}
	
	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	
	public String getPaymentTime() {
		return paymentTime;
	}
	
	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}
}
