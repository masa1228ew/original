package com.example.origin.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CollectionEditForm {

	 @NotNull
	    private Integer id;    
	    
	    @NotBlank(message = "名前を入力してください。")
	    @Size(max = 50, message = "テーブル名は50文字以内で入力してください")
	    private String name;

//	    @NotNull
//	    private Genre genreId;
}
