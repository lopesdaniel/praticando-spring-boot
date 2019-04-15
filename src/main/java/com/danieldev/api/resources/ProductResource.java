package com.danieldev.api.resources;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.danieldev.api.models.Product;
import com.danieldev.api.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductResource {
	
	@Autowired
	private ProductService productService;

	
	
	public ProductResource(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	@ResponseBody
	public List<Product> findAll(){
		return this.productService.findAll();
	}
	
	@GetMapping(value = "/{id}")
	@ResponseBody
	public Product find(@PathVariable Long id) {
		return this.productService.find(id);
	}
	
	@PostMapping
	@ResponseBody
	public Product create(@RequestBody Product product) {
		return this.productService.create(product);
	}
	
	@PutMapping(value = "/{id}")
	@ResponseBody
	public Product update(@PathVariable(value = "id") Long id, @RequestBody Product product) {
		return this.productService.update(id, product);
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable(value = "id") Long id, HttpServletResponse response) {
		this.productService.delete(id);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
	
	
	
}
