package com.movingapp.view;

import com.movingapp.entity.Authority;
import com.movingapp.entity.Location;

import java.sql.Blob;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class UserView {

	private Long id;
	private String email;
	private String firstName;
	private String lastName;
	private String fullName;
	private String phone;
	private String ccLastFour;
	private String ccExpirationDate;
	private Date expirationDate;
	private String cardType;
	private List<ChargeView> charges;
	private Set<Authority> authorities;
	private Set<Location> locations;
	private String locationsArray;
	private Boolean enabled;
	public Date createdOn;
	private String picture;
	private String picture_type;

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

	public Set<Location> getLocations() {
		return locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}

	public String getLocationsArray() {
		return locationsArray;
	}

	public void setLocationsArray(String locationsArray) {
		this.locationsArray = locationsArray;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPicture_type() {
		return picture_type;
	}

	public void setPicture_type(String picture_type) {
		this.picture_type = picture_type;
	}
}