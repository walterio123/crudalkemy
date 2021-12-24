package com.walterio12345.gmail.entity;


import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.Hibernate;
import javax.persistence.*;


import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
//@Getter
//@Setter
//@ToString
//@RequiredArgsConstructor
@Table(name = "movie",indexes = {
		@Index(name="idx_movie_title",columnList = "title"),
		@Index(name = "idx_movie_gender_id",columnList = "gender_id")
})
public class Movie {

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	/*@Id
	@GeneratedValue
	@Column(name = "id")*/
	private String id;
	
	//@Column(unique = true,nullable = false,length = 60)
	private String title;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private Date creationDate;
		
	private Integer qualification;
	
	private String image;
	
	//TODO cambiar despues de las pruebas fetch = FetchType.EAGER 
	@ManyToMany
	//@ToString.Exclude
	private List<Character> characters;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Gender gender;

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
		Movie other = (Movie) obj;
		return Objects.equals(id, other.id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Integer getQualification() {
		return qualification;
	}

	public void setQualification(Integer qualification) {
		this.qualification = qualification;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Movie(String id, String title, Date creationDate, Integer qualification, String image,
			List<Character> characters, Gender gender) {
		
		this.id = id;
		this.title = title;
		this.creationDate = creationDate;
		this.qualification = qualification;
		this.image = image;
		this.characters = characters;
		this.gender = gender;
	}

	public Movie() {
		
	}
	
	

}
