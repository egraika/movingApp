package com.movingapp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.movingapp.model.ChargeEntity;
import com.movingapp.model.MoveEntity;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "user")
public class User implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition="BIGSERIAL")
	private Long id;

	@Column(name = "email", nullable = false, unique = true)
	@Email(message = "Please provide a valid e-mail")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "cc_last_four")
	private String ccLastFour;

	@Column(name = "cc_expiration_date")
	private String ccExpirationDate;

	@Column(name = "cc_card_type")
	private String ccCardType;

	@Column(name = "enabled")
	private boolean enabled;

	@OneToOne(mappedBy="user", fetch=FetchType.LAZY)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@PrimaryKeyJoinColumn
	private ConfirmationToken confirmationToken;

	@OneToOne(fetch=FetchType.LAZY)
	@Cascade({org.hibernate.annotations.CascadeType.ALL})
	@PrimaryKeyJoinColumn
	private PasswordResetToken passwordResetToken;

	@Column(name = "phone")
	private String phone;

	@Column(name = "stripe_Customer_ID")
	private String stripeCustomerID;

	@JsonManagedReference(value="move_user_id")
	@OneToMany(fetch = FetchType.EAGER,mappedBy="user")
	private Set<MoveEntity> moves;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "user_to_location", joinColumns= @JoinColumn(name = "user_id"), inverseJoinColumns= @JoinColumn(name = "location_id"))
	@OrderBy("location ASC")
	private Set<Location> locations = new HashSet<Location>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_authority", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_authority", table = "authority", referencedColumnName = "id") })
	private Set<Authority> authorities = new HashSet<Authority>();

	@JsonManagedReference(value="user_charges")
	@OneToMany(fetch=FetchType.EAGER, cascade ={CascadeType.PERSIST, CascadeType.MERGE}, mappedBy="user")
	@Fetch(FetchMode.SELECT)
	private List<ChargeEntity> charges;

	@Column(name="created_on", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date createdOn;

	@Column(name = "picture")
	private byte[] picture;

	@Column(name = "picture_type")
	private String picture_type;

	@PrePersist
	protected void onCreate() {
		createdOn = new Date();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
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
	
	public Set<Location> getLocations() {
		return locations;
	}

	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}

	public ConfirmationToken getConfirmationToken() {
		return confirmationToken;
	}

	public void setConfirmationToken(ConfirmationToken confirmationToken) {
		this.confirmationToken = confirmationToken;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PasswordResetToken getPasswordResetToken() {
		return passwordResetToken;
	}

	public void setPasswordResetToken(PasswordResetToken passwordResetToken) {
		this.passwordResetToken = passwordResetToken;
	}

	public String getStripeCustomerID() {
		return stripeCustomerID;
	}

	public void setStripeCustomerID(String stripeCustomerID) {
		this.stripeCustomerID = stripeCustomerID;
	}

	public Set<MoveEntity> getMoves() {
		return moves;
	}

	public void setMoves(Set<MoveEntity> moves) {
		this.moves = moves;
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

	public String getCcCardType() {
		return ccCardType;
	}

	public void setCcCardType(String ccCardType) {
		this.ccCardType = ccCardType;
	}

	public List<ChargeEntity> getCharges() {
		return charges;
	}

	public void setCharges(List<ChargeEntity> charges) {
		this.charges = charges;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getPicture_type() {
		return picture_type;
	}

	public void setPicture_type(String picture_type) {
		this.picture_type = picture_type;
	}
}
