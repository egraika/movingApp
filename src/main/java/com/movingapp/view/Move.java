package com.movingapp.view;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;


public class Move{
	

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String fromStreet;
	private String fromCity;
	private int fromZip;
	private String fromState;
	private String toStreet;
	private String toCity;
	private String comment;
	private int toZip;
	private String toState;
	private List<Note> notes;
	private List<ChargeView> charges;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXX")
	private OffsetDateTime startsAt;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXX")
	private OffsetDateTime endsAt;

	private LocalDate dateOfBooking;
	private String stripeCustomerID;
	private String status;
	private String title;
	private String color;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFromStreet() {
		return fromStreet;
	}

	public void setFromStreet(String fromStreet) {
		this.fromStreet = fromStreet;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public int getFromZip() {
		return fromZip;
	}

	public void setFromZip(int fromZip) {
		this.fromZip = fromZip;
	}

	public String getFromState() {
		return fromState;
	}

	public void setFromState(String fromState) {
		this.fromState = fromState;
	}

	public String getToStreet() {
		return toStreet;
	}

	public void setToStreet(String toStreet) {
		this.toStreet = toStreet;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getToZip() {
		return toZip;
	}

	public void setToZip(int toZip) {
		this.toZip = toZip;
	}

	public String getToState() {
		return toState;
	}

	public void setToState(String toState) {
		this.toState = toState;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	public List<ChargeView> getCharges() {
		return charges;
	}

	public void setCharges(List<ChargeView> charges) {
		this.charges = charges;
	}

	public String getStripeCustomerID() {
		return stripeCustomerID;
	}

	public void setStripeCustomerID(String stripeCustomerID) {
		this.stripeCustomerID = stripeCustomerID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public LocalDate getDateOfBooking() {
		return dateOfBooking;
	}

	public void setDateOfBooking(LocalDate dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}

	public OffsetDateTime getStartsAt() {
		return startsAt;
	}

	public void setStartsAt(OffsetDateTime startsAt) {
		this.startsAt = startsAt;
	}

	public OffsetDateTime getEndsAt() {
		return endsAt;
	}

	public void setEndsAt(OffsetDateTime endsAt) {
		this.endsAt = endsAt;
	}
}