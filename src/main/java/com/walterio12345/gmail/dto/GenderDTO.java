package com.walterio12345.gmail.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.walterio12345.gmail.entity.Gender;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GenderDTO implements Serializable{
	
	
	
	private static final long serialVersionUID = 1L;
	
	@Schema(example = "5241f5eb-c49b-4643-97dc-e902a6a10ebc")
	private String id;
	
	@NotBlank
	@Size(max = 40)
	private String name;

	private boolean deleted;
	


}
