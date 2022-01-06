package com.walterio12345.gmail.entity;


import java.util.*;

import org.hibernate.Hibernate;
import javax.persistence.*;


import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "movie",indexes = {
		@Index(name="idx_movie_title",columnList = "title"),
		@Index(name = "idx_movie_gender_id",columnList = "gender_id")
})
public class Movie {

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")

	private String id;
	
	//@Column(unique = true,nullable = false,length = 60)
	private String title;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private Date creationDate;
		
	private Integer qualification;
	
	private String image;

	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "gender_id",insertable = false,updatable = false)
	private Gender gender;

	@Column(name = "gender_id",nullable = false)
	private String genderId;

	//engloba icon por ser la entidad "mas Grande"
	@ManyToMany(
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			})
	@JoinTable(
			name = "character_movie",
			joinColumns = @JoinColumn(name = "movie_id"),
			inverseJoinColumns = @JoinColumn(name = "character_id"))
	private Set<Character> characters=new HashSet<>();

	



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


	
	

}
