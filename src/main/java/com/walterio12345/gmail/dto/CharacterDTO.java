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

@Getter
@Setter
@NoArgsConstructor
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
	

	

	
	
}
