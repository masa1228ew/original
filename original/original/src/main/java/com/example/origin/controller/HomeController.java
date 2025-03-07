package com.example.origin.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.origin.entity.Collection;
import com.example.origin.entity.User;
import com.example.origin.repository.CollectionRepository;
import com.example.origin.repository.UserRepository;
import com.example.origin.security.UserDetailsImpl;
import com.example.origin.service.CollectionService;

@Controller
public class HomeController {
private final CollectionRepository collectionRepository;
private final UserRepository userRepository;

private final CollectionService collectionService;
	
	public HomeController(CollectionRepository collectionRepository
						  ,CollectionService collectionService
						  , UserRepository userRepository) {
		this.collectionRepository = collectionRepository;
		this.collectionService = collectionService;
		 this.userRepository = userRepository;
	}
	
	
	 @GetMapping
	 public String index(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC)Pageable pageable
			 			 , @RequestParam(defaultValue = "name") String sortBy,
			 		     @RequestParam(defaultValue = "asc") String direction
			 		   
			 			) {
		 System.out.println("テスト");
//		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 Object principal = authentication.getPrincipal();
		 if (principal == null || !(principal instanceof UserDetailsImpl)) {
		        System.out.println("ユーザー詳細情報が取得できません");
		        return "redirect:/login";
		    }
		 UserDetailsImpl userDetailsImpl = (UserDetailsImpl) principal;
		 User user = userRepository.findById(userDetailsImpl.getUser().getId()).orElse(null); 
		 if (user == null) {
	            System.out.println("ユーザーが見つかりません");
	            return "redirect:/login"; // ログインページへリダイレクト
	        }
		 List<Collection> collections = collectionRepository.findByUser(user);
	     Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
	     Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
	     System.out.println("テスト２");
//	     Page<Collection> collectionPage = collectionRepository.findAll(sortedPageable);
	     Page<Collection> collectionPage = collectionRepository.findByUser(user, sortedPageable);
	     model.addAttribute("collectionPage", collectionPage);
	     System.out.println("テスト3");
	     System.out.println("データベースにあるコレクション数: " + collectionRepository.count());
	     System.out.println(collectionPage);
	     System.out.println("データベースにあるコレクション数: " + collectionRepository.count());
	     model.addAttribute("sortBy", sortBy);  // 選択された sortBy の値
	     model.addAttribute("direction", direction);  // 選択された direction の値
	     System.out.println("テスト4");
	     model.addAttribute("user", user);
	     return "index";             
	        
	      
	    }  
	
	
	
	 @GetMapping("/collections")
	 public String change(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC)Pageable pageable
			 			 , @RequestParam(defaultValue = "name") String sortBy,
			 		     @RequestParam(defaultValue = "asc") String direction
			 		    
			 			) {
		 System.out.println("テスト");
	     Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
	     Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
	     System.out.println("テスト２");
	     Page<Collection> collectionPage = collectionRepository.findAll(sortedPageable);
	     model.addAttribute("collectionPage", collectionPage);
	     System.out.println("テスト3");
	     System.out.println("データベースにあるコレクション数: " + collectionRepository.count());
	     System.out.println(collectionPage);
	     System.out.println("データベースにあるコレクション数: " + collectionRepository.count());
	     model.addAttribute("sortBy", sortBy);  // 選択された sortBy の値
	     model.addAttribute("direction", direction);  // 選択された direction の値
	     System.out.println("テスト4");
	     return "index";             
	        
	      
	    }  
	 
	 
	 @GetMapping("/{id}")
	    public String show(@PathVariable(name = "id") Integer id, Model model) {
	        Collection collection = collectionRepository.getReferenceById(id);
	        
	        model.addAttribute("collection", collection);
	        
	        return "data/show";
	    }    
	 
//	 @GetMapping
//	 public String index(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC)Pageable pageable
//			 			) {
//	        Page<Collection> collectionPage = collectionRepository.findAll(pageable);       
//	      
//	        model.addAttribute("collectionPage", collectionPage);             
	        
//	        return "index";
//	    }  
	 
	 
	 
	 
	 
//	 @PostMapping("item_list")
//	 public String showCollectionList(Model model,String sortFg) {
//		 List<Collection>collectionList=collectionService.findAll(sortFg);
//		 model.addAttribute("collectionList",collectionList);
//		 Map<String,String>map=collectionService.createMap();
//		 model.addAttribute("sortList",map);
//		 model.addAttribute("selectedValue",sortFg);
//		 return "collection_list";
//	 }
	 
	 
     
}