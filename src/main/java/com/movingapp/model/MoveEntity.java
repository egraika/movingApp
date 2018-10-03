package com.movingapp.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import javax.persistence.*;

import com.movingapp.entity.User;
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

	@Column(name = "moveStart")
	private OffsetDateTime moveStart;

	@Column(name = "moveEnd")
	private OffsetDateTime  moveEnd;

	@Column(name = "dateOfBooking")
	private LocalDate dateOfBooking;

	@Column(name = "status")
	private String status;

	@Column(name = "moveTitle")
	private String moveTitle;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
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

	public void setDateOfBooking(LocalDate dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}

	public LocalDate getDateOfBooking() {
		return dateOfBooking;
	}

	public String getMoveTitle() {
		return moveTitle;
	}

	public void setMoveTitle(String moveTitle) {
		this.moveTitle = moveTitle;
	}

	public OffsetDateTime getMoveStart() {
		return moveStart;
	}

	public void setMoveStart(OffsetDateTime moveStart) {
		this.moveStart = moveStart;
	}

	public OffsetDateTime getMoveEnd() {
		return moveEnd;
	}

	public void setMoveEnd(OffsetDateTime moveEnd) {
		this.moveEnd = moveEnd;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}