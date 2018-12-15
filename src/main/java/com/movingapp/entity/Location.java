package com.movingapp.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.movingapp.model.MoveEntity;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "location")
public class Location {

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@JsonProperty("name")
	@Column(name = "location", nullable = false)
	private String location;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="location")
	public List<MoveEntity> moves;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<MoveEntity> getMoves() {
		return moves;
	}

	public void setMoves(List<MoveEntity> moves) {
		this.moves = moves;
	}
}
