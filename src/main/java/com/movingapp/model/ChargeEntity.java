package com.movingapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.movingapp.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "charges")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChargeEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition="BIGSERIAL")
	@OrderBy
	private int id;

	@Column(name = "chargeid")
	private String chargeid;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "date")
	private Date date;

	@Column(name = "moveid")
	private long moveid;

	@Column(name = "type")
	private String type;

	@JsonBackReference(value="user_charges")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinTable(name = "charges_to_user", joinColumns= @JoinColumn(name = "chargeid"), inverseJoinColumns= @JoinColumn(name = "userid"))
	private User user;
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getMoveid() {
		return moveid;
	}

	public void setMoveid(long moveid) {
		this.moveid = moveid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChargeid() {
		return chargeid;
	}

	public void setChargeid(String chargeid) {
		this.chargeid = chargeid;
	}
}