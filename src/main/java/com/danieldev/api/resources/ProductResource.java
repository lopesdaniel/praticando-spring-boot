package com.danieldev.api.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.danieldev.api.models.Product;
import com.danieldev.api.services.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API Rest - Model Product")
@RestController
@RequestMapping("/products")
public class ProductResource {
	
	@Autowired
	private ProductService productService;

	
	
	public ProductResource(ProductService productService) {
		this.productService = productService;
	}

	@ApiOperation(value = "Encontrar todos os Produtos no banco de dados")
	@GetMapping(produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> findAll(){
		List<Product> list = this.productService.findAll();
		
		return new ResponseEntity<List>(list, (HttpStatus.OK));
	}
	
	@ApiOperation(value = "Recupera do banco de dados, um produto por seu id")
	@GetMapping(value = "/{id}", produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> find(@PathVariable Long id) {
		Product product = this.productService.find(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Cria um novo produto")
	@PostMapping
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Product product, Errors errors) {
		if(!errors.hasErrors()) {
			Product productCreated = this.productService.create(product);
			
			return new ResponseEntity<Product>(productCreated, HttpStatus.CREATED);
		}
		
		
		return ResponseEntity.badRequest().body(
				errors.getAllErrors()
						.stream()
						.map(msg -> msg.getDefaultMessage())
						.collect(Collectors.joining(",")));
	}
	
	@ApiOperation(value = "Atualiza um produto existente")
	@PutMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<?> update(@Valid @PathVariable(value = "id") Long id, @RequestBody Product product, Errors errors) {
		if(!errors.hasErrors()) {
			Product productUpdated = this.productService.update(id, product);
			
			return new ResponseEntity<Product>(productUpdated, HttpStatus.OK);
		}
		return ResponseEntity.badRequest().body(
				errors.getAllErrors()
						.stream()
						.map(msg -> msg.getDefaultMessage())
						.collect(Collectors.joining(","))); 
	}
	
	@ApiOperation(value = "Deleta um produto do banco de dados")
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "id") Long id) {
		this.productService.delete(id);
	}
	
	
	
}
