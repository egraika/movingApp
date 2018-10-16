package com.movingapp.view;

import com.movingapp.entity.Authority;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class UserView {

	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	private String phone;
	private String ccLastFour;
	private String ccExpirationDate;
	private Date expirationDate;
	private String cardType;
	private List<ChargeView> charges;
	private Set<Authority> authorities;


	public UserView(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCcLastFour() {
		return ccLastFour;
	}

	public void setCcLastFour(String ccLastFour) {
		this.ccLastFour = ccLastFour;
	}

	public String getCcExpirationDate() {
		return ccExpirationDate;
	}

	public void setCcExpirationDate(String ccExpirationDate) {
		this.ccExpirationDate = ccExpirationDate;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public List<ChargeView> getCharges() {
		return charges;
	}

	public void setCharges(List<ChargeView> charges) {
		this.charges = charges;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
}