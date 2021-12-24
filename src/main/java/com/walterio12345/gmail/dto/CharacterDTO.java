package com.walterio12345.gmail.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;

//@Getter
//@Setter
//@NoArgsConstructor
//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CharacterDTO implements Serializable {

	private static final long serialVersionUID = 1L;
    
	private String id;
    
	@NotBlank
	@Size(max = 40)
	private String name;
	
	private String image;
	
	
	@Min(value = 1)
	private Integer age;

	@ArraySchema(schema = @Schema(accessMode = Schema.AccessMode.READ_ONLY))
	private List<MovieDTO>movies;
	
	public CharacterDTO() {}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public List<MovieDTO> getMovies() {
		return movies;
	}
	public void setMovies(List<MovieDTO> movies) {
		this.movies = movies;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	};
	
	
}
