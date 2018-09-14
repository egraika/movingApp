package com.movingapp.view;

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
	private Date dateOfMove;
	private Date dateOfBooking;
	private String stripeCustomerID;
	private String status;
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
	public void setStripeCustomerID(String stripeCustomerID) {
		this.stripeCustomerID = stripeCustomerID;
	}
	
	public String getStripeCustomerID() {
		return stripeCustomerID;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setDateOfMove(Date date) {
		this.dateOfMove = date;
	}
	
	public Date getDateOfMove() {
		return dateOfMove;
	}
	
	public void setDateOfBooking(Date dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}
	
	public Date getDateOfBooking() {
		return dateOfBooking;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String lastName ) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setEmail(String email  ) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setfromStreet(String fromStreet) {
		this.fromStreet = fromStreet;
	}
	
	public String getfromStreet() {
		return fromStreet;
	}
	
	public void setfromCity(String fromCity) {
		this.fromCity = fromCity;
	}
	
	public String getfromCity() {
		return fromCity;
	}
	
	public void setFromZip(int fromZip) {
		this.fromZip = fromZip;
	}
	
	public int getFromZip() {
		return fromZip;
	}
	
	public void setFromState(String fromState) {
		this.fromState = fromState;
	}
	
	public String getFromState() {
		return fromState;
	}
	

	public void setToStreet(String toStreet) {
		this.toStreet = toStreet;
	}
	
	public String getToStreet() {
		return toStreet;
	}
	
	public void setToCity(String toCity) {
		this.toCity = toCity;
	}
	
	public String getToCity() {
		return toCity;
	}
	
	public void setToZip(int toZip) {
		this.toZip = toZip;
	}
	
	public int getToZip() {
		return toZip;
	}
	
	public void setToState(String toState) {
		this.toState = toState;
	}
	
	public String getToState() {
		return toState;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}
	
	public List<Note> getNotes() {
		return notes;
	}
	
	public void setCharges(List<ChargeView> charges) {
		this.charges = charges;
	}
	
	public List<ChargeView> getCharges() {
		return charges;
	}
}