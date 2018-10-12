package com.movingapp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "note")
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@OrderBy
	@Column(name = "id")
	private int id;
	
	@Lob
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "author")
	private String author;
	
	@Column(name = "date")
	private Date date;
	
	@JsonBackReference(value="notes")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinTable(name = "notes_to_move", joinColumns= @JoinColumn(name = "noteid"), inverseJoinColumns= @JoinColumn(name = "moveid"))
	private MoveEntity move;
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getComment() {
		return comment;
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