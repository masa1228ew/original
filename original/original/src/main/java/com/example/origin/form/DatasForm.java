package com.example.origin.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DatasForm {

	 @NotBlank
	    private String name;

	    @NotNull
	    private Integer collectionId;

	    @NotNull
	    private Integer categoryId;

	    @NotNull
	    private Integer price;

}
