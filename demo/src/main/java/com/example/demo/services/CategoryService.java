package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.entities.Category;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	// @Transactional(readOnly = true)
	public List<CategoryDTO> findAll()
	{
		List<Category> list = repository.findAll();
		
		
		return list.stream()
				.map(x -> new CategoryDTO(x)).collect(Collectors.toList());	
	}
	
	// @Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id not found"));
		return new CategoryDTO(entity);
	}

	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		repository.save(entity);
		return new CategoryDTO(entity);
	}

	public CategoryDTO update(Long id, CategoryDTO dtoUpdate) 
	{
		Category entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Id not found " + id));
		entity.setName(dtoUpdate.getName());
		entity = repository.save(entity);
		
		return new CategoryDTO(entity);
	}
}
