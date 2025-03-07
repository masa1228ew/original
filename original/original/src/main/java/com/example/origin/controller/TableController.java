package com.example.origin.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.origin.entity.Collection;
import com.example.origin.entity.User;
import com.example.origin.form.CollectionEditForm;
import com.example.origin.form.CollectionRegisterForm;
import com.example.origin.repository.CollectionRepository;
import com.example.origin.repository.UserRepository;
import com.example.origin.security.UserDetailsImpl;
import com.example.origin.service.CollectionService;

@Controller
@RequestMapping("/table")
public class TableController {
	private final CollectionRepository collectionRepository;
	private final CollectionService collectionService;
	private final UserRepository userRepository;
	
	public TableController(CollectionRepository collectionRepository
							,CollectionService collectionService
							,UserRepository userRepository) {
		this.collectionRepository = collectionRepository;
		this.collectionService = collectionService;
		this.userRepository = userRepository;
	}
	
	  @GetMapping("/register")
	    public String register(Model model) {
		 
	        model.addAttribute("collectionRegisterForm", new CollectionRegisterForm());
	        return "table/register";
	    }  
	  
	@PostMapping("/create")
    public String create(@ModelAttribute @Validated CollectionRegisterForm collectionRegisterForm, BindingResult bindingResult,
    		RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
		User user = userDetailsImpl.getUser();
        if (bindingResult.hasErrors()) {
            return "table/register";
        }
        
      
        
        collectionService.create(collectionRegisterForm,user);
        redirectAttributes.addFlashAttribute("successMessage", "コレクションを登録しました。");    
        
        return "redirect:/";
    }    
	
	@GetMapping("/{id}/edit")
    public String edit(@PathVariable(name = "id") int id, Model model) {
		System.out.println("テストエディット");
//    	Collection collection = collectionRepository.getReferenceById(id);
		Collection collection = collectionRepository.findById(id).orElse(null);
    	System.out.println("テスト");
    	CollectionEditForm collectionEditForm = new CollectionEditForm(collection.getId(),collection.getName());
//    	CollectionRegisterForm collectionRegisterForm = new CollectionRegisterForm();
//    	collectionRegisterForm.setGenre(collection.getGenre().getId());
    	System.out.println("テスト2");
    	model.addAttribute("collectionEditForm", collectionEditForm);
    	model.addAttribute("genreId", collection.getGenre().getId());
    	model.addAttribute("collection",collection);
    	return "table/edit";
    }
	
	 @PostMapping("/{id}/update")
	    public String update(@ModelAttribute @Validated CollectionEditForm collectionEditForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		 System.out.println("テストアップデート");
	        if (bindingResult.hasErrors()) {
	            return "table/edit";
	        }
	        System.out.println("テスト");
	        collectionService.update(collectionEditForm);
	        redirectAttributes.addFlashAttribute("successMessage", "情報を編集しました。");
	        System.out.println("テスト２");
	        return "redirect:/data/"+ collectionEditForm.getId();
	    }    
	
	 @PostMapping("/{id}/delete")
	    public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
		 System.out.println("テストデリート");
	        collectionRepository.deleteById(id);
	        System.out.println("テスト2");
//	        Collection collection = collectionRepository.getReferenceById(id);
	        redirectAttributes.addFlashAttribute("successMessage", "データベースをを削除しました。");
	        
	        return "redirect:/";
//	        return "index";
	    }    
	  
}
