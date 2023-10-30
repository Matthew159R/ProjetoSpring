package com.example.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		if (id > service.getNumberOfCategories())
		{
			String errorMessage = "Categoria não encontrada ";
			String errorDetails = "[O número de categorias só vai até " + service.getNumberOfCategories() + "]";
			ErrorResponse errorResponse = new ErrorResponse(errorMessage, errorDetails);
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(errorResponse.getErrorMessage() + errorResponse.getErrorDetails());			
		}else 
		{
			CategoryDTO category = service.findById(id);
			
			return ResponseEntity.ok().body(category);
		}
	}
}

class ErrorResponse {
	private String errorMessage;
	private String errorDetails;
	
	public ErrorResponse(String errorMessage, String errorDetails)
	{
		this.errorMessage = errorMessage;
		this.errorDetails = errorDetails;
	}
	
	public String getErrorMessage ()
	{
		return errorMessage;
	}
	public String getErrorDetails ()
	{
		return errorDetails;
	}
}
