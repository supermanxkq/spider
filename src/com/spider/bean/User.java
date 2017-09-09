package com.spider.bean;

import java.math.BigDecimal;

public class User {
	private int id;
	private String realName;
	private String email;
	private int repaymentMoney;
	private String idCard;
	private String telPhone;
	private int otherPayment;
	private String address;
	private String mobile;
	private int ovDays;
	private String coNa;
	private BigDecimal ovAmount;
	private String coAddress;

	/**
	 * 主键
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 真实姓名
	 * 
	 * @return
	 */
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * 邮箱
	 * 
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 几笔逾期未还款
	 * 
	 * @return
	 */
	public int getRepaymentMoney() {
		return repaymentMoney;
	}

	public void setRepaymentMoney(int repaymentMoney) {
		this.repaymentMoney = repaymentMoney;
	}

	/**
	 * 身份证
	 * 
	 * @return
	 */
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	/**
	 * 电话
	 * 
	 * @return
	 */
	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	/**
	 * 几笔网站垫付款
	 * 
	 * @return
	 */
	public int getOtherPayment() {
		return otherPayment;
	}

	public void setOtherPayment(int otherPayment) {
		this.otherPayment = otherPayment;
	}

	/**
	 * 地址
	 * 
	 * @return
	 */
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 手机
	 * 
	 * @return
	 */
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 最长逾期天数
	 * 
	 * @param mobile
	 */
	public int getOvDays() {
		return ovDays;
	}

	public void setOvDays(int ovDays) {
		this.ovDays = ovDays;
	}

	/**
	 * 公司名称
	 * 
	 * @return
	 */
	public String getCoNa() {
		return coNa;
	}

	public void setCoNa(String coNa) {
		this.coNa = coNa;
	}

	/**
	 * 逾期待还总额
	 * 
	 * @return
	 */
	public BigDecimal getOvAmount() {
		return ovAmount;
	}

	public void setOvAmount(BigDecimal ovAmount) {
		this.ovAmount = ovAmount;
	}

	/**
	 * 公司地址
	 * 
	 * @return
	 */
	public String getCoAddress() {
		return coAddress;
	}

	public void setCoAddress(String coAddress) {
		this.coAddress = coAddress;
	}

}
