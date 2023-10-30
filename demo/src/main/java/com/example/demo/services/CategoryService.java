package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entities.Category;
import com.example.demo.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	private int numberOfCategories;
	// @Transactional(readOnly = true)
	
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
        initializeNumberOfCategories();
    }

    private void initializeNumberOfCategories() {
        List<Category> list = repository.findAll();
        numberOfCategories = list.size();
    }

    public int getNumberOfCategories() {
        return numberOfCategories;
    }
	
	public List<CategoryDTO> findAll()
	{
		List<Category> list = repository.findAll();
		
		
		return list.stream()
				.map(x -> new CategoryDTO(x)).collect(Collectors.toList());	
	}
	
	// @Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.get();
		return new CategoryDTO(entity);
	}
}
