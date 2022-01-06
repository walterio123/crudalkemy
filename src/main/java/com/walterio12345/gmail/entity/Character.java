package com.walterio12345.gmail.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Validated
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "movie_character",indexes = {
		@Index(name="idx_character_name",columnList = "name")
})

public class Character {
	
	
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	@Column(nullable = false, unique = true,length = 60)
	private String name;
	
	private String image;

	private Integer age;

	private Integer weight;

	private String history;
	
	@ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL)
	private List<Movie> movies=new ArrayList<>();

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Character other = (Character) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
