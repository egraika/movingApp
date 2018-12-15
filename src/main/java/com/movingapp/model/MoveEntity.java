package com.movingapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.movingapp.entity.User;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "booked_moves")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition="BIGSERIAL")
	@OrderBy
	private int id;

	@JsonManagedReference(value="notes")
	@OneToMany(fetch = FetchType.EAGER, cascade ={CascadeType.PERSIST, CascadeType.MERGE}, mappedBy="move")
	private List<NoteEntity> notes;

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

	@Column(name = "isArtwork", columnDefinition="BOOLEAN DEFAULT false")
	private Boolean isArtwork;

	@Column(name = "isAntiques", columnDefinition="BOOLEAN DEFAULT false")
	private Boolean isAntiques;

	@Column(name = "numberOfBoxes", columnDefinition="bigint DEFAULT 0")
	private long numberOfBoxes;

	@Column(name = "numberOfLargeItems",columnDefinition="bigint DEFAULT 0")
	private long numberOfLargeItems;

	@Column(name = "isGroundFloor", columnDefinition="BOOLEAN DEFAULT false")
	private Boolean isGroundFloor;

	@Column(name = "isElevator", columnDefinition="BOOLEAN DEFAULT false")
	private Boolean isElevator;

	@Column(name = "type")
	private String type;

	@JsonBackReference(value="move_user_id")
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user;

	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany
	@JoinTable(name = "assigned_users_to_moves", joinColumns= @JoinColumn(name = "user_id"), inverseJoinColumns= @JoinColumn(name = "move_id"))
	private List<User> assignedUsers;

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

	public Boolean getArtwork() {
		return isArtwork;
	}

	public void setArtwork(Boolean artwork) {
		isArtwork = artwork;
	}

	public Boolean getAntiques() {
		return isAntiques;
	}

	public void setAntiques(Boolean antiques) {
		isAntiques = antiques;
	}

	public long getNumberOfBoxes() {
		return numberOfBoxes;
	}

	public void setNumberOfBoxes(long numberOfBoxes) {
		this.numberOfBoxes = numberOfBoxes;
	}

	public long getNumberOfLargeItems() {
		return numberOfLargeItems;
	}

	public void setNumberOfLargeItems(long numberOfLargeItems) {
		this.numberOfLargeItems = numberOfLargeItems;
	}

	public Boolean getGroundFloor() {
		return isGroundFloor;
	}

	public void setGroundFloor(Boolean groundFloor) {
		isGroundFloor = groundFloor;
	}

	public Boolean getElevator() {
		return isElevator;
	}

	public void setElevator(Boolean elevator) {
		isElevator = elevator;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<User> getAssignedUsers() {
		return assignedUsers;
	}

	public void setAssignedUsers(List<User> assignedUsers) {
		this.assignedUsers = assignedUsers;
	}
}