package com.movingapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "booked_moves")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@OrderBy
	@Column(name = "id")
	private int id;
	
	@JsonBackReference
	@OneToMany(fetch = FetchType.EAGER, cascade ={CascadeType.PERSIST, CascadeType.MERGE}, mappedBy="move")
	private List<NoteEntity> notes;
	
	@JsonBackReference
	@OneToMany(fetch=FetchType.EAGER, cascade ={CascadeType.PERSIST, CascadeType.MERGE}, mappedBy="move")
	@Fetch (FetchMode.SELECT) 
	private List<ChargeEntity> charges;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "fromStreet")
	private String fromStreet;
	
	@Column(name = "fromCity")
	private String fromCity;
	
	@Column(name = "fromZip")
	private int fromZip;
	
	@Column(name = "fromState")
	private String fromState;
	
	@Column(name = "toStreet")
	private String toStreet;
	
	@Column(name = "toCity")
	private String toCity;
	
	@Column(name = "toZip")
	private int toZip;
	
	@Column(name = "toState")
	private String toState;
	
	@Lob
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "dateOfMove")
	private Date dateOfMove;
	
	@Column(name = "dateOfBooking")
	private Date dateOfBooking;
	
	@Column(name = "stripe_Customer_ID")
	private String stripeCustomerID;
	
	@Column(name = "status")
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
	
	public void setNotes(List<NoteEntity> notes) {
		this.notes = notes;
	}
	
	public List<NoteEntity> getNotes() {
		return notes;
	}
	
	public void setCharges(List<ChargeEntity> charges) {
		this.charges = charges;
	}
	
	public List<ChargeEntity> getCharges() {
		return charges;
	}
	
	public void setDateOfBooking(Date dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}
	
	public Date getDateOfBooking() {
		return dateOfBooking;
	}
}