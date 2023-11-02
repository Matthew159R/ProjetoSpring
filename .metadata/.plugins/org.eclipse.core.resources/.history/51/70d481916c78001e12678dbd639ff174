package com.example.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CategoryDTO;
import com.example.demo.services.CategoryService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	
	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll()
	{
		List<CategoryDTO> categoryList = service.findAll();
		return ResponseEntity.ok().body(categoryList);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<?> findCategoryById(@PathVariable Long id)
	{
		CategoryDTO categoryById = service.findById(id);
		return ResponseEntity.ok().body(categoryById);

	}
}

