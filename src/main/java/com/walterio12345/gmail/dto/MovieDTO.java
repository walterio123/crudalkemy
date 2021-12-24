package com.walterio12345.gmail.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


import org.springframework.format.annotation.DateTimeFormat;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MovieDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	private String id;
	
	@NotBlank
	@Size(max = 60)
	private String title;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull
	private Date creationDate;
	
	@Positive
	@Max(value = 5)
	private Integer qualification;
	
	private String image;
	
	@Schema(accessMode = Schema.AccessMode.READ_ONLY,required = true)
	private GenderDTO gender;
	
	
	@Schema(accessMode = Schema.AccessMode.WRITE_ONLY)
	private String genderId;
	
	
	//TODO @NOTEMPTY??????
	@ArraySchema(schema= @Schema( accessMode = Schema.AccessMode.READ_ONLY))
	private List<CharacterDTO>characters;
	
	@ArraySchema(schema=@Schema( accessMode = Schema.AccessMode.READ_WRITE))
	private List<String>charactersIds;
	
	

	
	
	
	
}
