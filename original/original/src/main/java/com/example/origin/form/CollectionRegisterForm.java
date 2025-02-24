package com.example.origin.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CollectionRegisterForm {
	@NotBlank(message = "コレクション名を入力してください。")
	 @Size(max = 50, message = "テーブル名は50文字以内で入力してください")
    private String name;
	
	 @NotNull(message = "カテゴリを入力してください。")
     private Integer  genre;  
}
