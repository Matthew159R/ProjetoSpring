package com.example.demo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@PostMapping
	public ResponseEntity<CategoryDTO> insertCategory(@RequestBody CategoryDTO dtoInsert)
	{
		service.insert(dtoInsert);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(dtoInsert.getId()).toUri();
		
		return ResponseEntity.created(uri).body(dtoInsert);
	}
	
	@PutMapping(value = "/{id}")			
	public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO dtoUpdate)
	{
		service.update(id, dtoUpdate);
			
		return ResponseEntity.ok().body(dtoUpdate);
	}
	
	@DeleteMapping(value = "/{id}")			
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id)
	{
		service.delete(id);	
		return ResponseEntity.noContent().build();
	}
}

