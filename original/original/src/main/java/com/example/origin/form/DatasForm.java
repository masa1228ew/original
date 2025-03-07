package com.example.origin.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DatasForm {

	 @NotBlank
	 @Size(max = 255, message = "アイテム名は255文字以内で入力してください")
	    private String name;

	    @NotNull
	    private Integer collectionId;

	    @NotNull
	    private Integer categoryId;
	    
	    private Integer price;
}