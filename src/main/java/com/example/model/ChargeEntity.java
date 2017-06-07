package com.example.model;

import java.io.Serializable;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "charges")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChargeEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@OrderBy
	@Column(name = "id")
	private int id;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "date")
	private Date date;

	@JsonManagedReference
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinTable(name = "charges_to_move", joinColumns= @JoinColumn(name = "chargeid"), inverseJoinColumns= @JoinColumn(name = "moveid"))
	private MoveEntity move;
	
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
	
	public void setMove(MoveEntity move) {
		this.move = move;
	}
	
	public MoveEntity getMove() {
		return move;
	}
}