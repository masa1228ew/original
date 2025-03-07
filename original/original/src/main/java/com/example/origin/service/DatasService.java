package com.example.origin.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.origin.entity.Category;
import com.example.origin.entity.Collection;
import com.example.origin.entity.Datas;
import com.example.origin.form.DatasEditForm;
import com.example.origin.form.DatasForm;
import com.example.origin.repository.CategoryRepository;
import com.example.origin.repository.CollectionRepository;
import com.example.origin.repository.DatasRepository;

import jakarta.transaction.Transactional;

@Service
public class DatasService {

	private final DatasRepository datasRepository;
	private final GenreService genreService;
	private final CollectionRepository collectionRepository;
	private final CategoryRepository categoryRepository;
	
	public DatasService(DatasRepository datasRepository
						,GenreService genreService
						,CollectionRepository collectionRepository
						,CategoryRepository categoryRepository) {
		this.datasRepository = datasRepository;
		this.genreService = genreService;
		this.collectionRepository = collectionRepository;
		this.categoryRepository = categoryRepository;
	}
	
	 public Datas createData(DatasForm datasForm) {
		 System.out.println("サービステスト");
	        // Collectionを取得
	        Collection collection = collectionRepository.findById(datasForm.getCollectionId())
	                .orElseThrow(() -> new IllegalArgumentException("Collection not found with ID: " ));
	        System.out.println("テスト2");
	        // Categoryを取得
	        Category category = categoryRepository.findById(datasForm.getCategoryId())
	                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: "));
	        System.out.println("テスト3");
	        // Datasオブジェクトを作成して設定
	        Datas datas = new Datas();
	        System.out.println("テスト４");
	        datas.setName(datasForm.getName());
	        datas.setCollection(collection);
	        datas.setCategory(category);
	        datas.setPrice(datasForm.getPrice());
	        System.out.println("テスト5");
	        // 保存
	        return datasRepository.save(datas);
	    }
	  @Transactional
	    public void update(DatasEditForm datasEditForm) {
	        Datas datas = datasRepository.getReferenceById(datasEditForm.getId());
	        Collection collection = collectionRepository.findById(datasEditForm.getCollectionId().getId())
	                .orElseThrow(() -> new IllegalArgumentException("Collection not found with ID: " ));
	        datas.setName(datasEditForm.getName());                
	        
	        datas.setPrice(datasEditForm.getPrice());
	        datas.setCollection(collection);
	                    
	        datasRepository.save(datas);
	    }  
	  public Datas findById(Integer id) {
	        Optional<Datas> dataOpt = datasRepository.findById(id);
	        return dataOpt.orElse(null);
	    }

	    // ✅ データを保存（新規・更新どちらも対応）
	    public Datas save(Datas datas) {
	        return datasRepository.save(datas);
	    }
}
