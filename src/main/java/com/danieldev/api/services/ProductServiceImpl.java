package com.danieldev.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.danieldev.api.models.Product;
import com.danieldev.api.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public List<Product> findAll() {
		return this.productRepository.findAll();
	}

	@Override
	public Product find(Long id) {
		return this.productRepository.findOne(id);
	}

	@Override
	public Product create(Product product) {
		this.productRepository.save(product);
		return product;
	}

	@Override
	public Product update(Long id, Product product) {
		Product productExists = this.productRepository.findOne(id);
		if(productExists != null) {
			product.setId(productExists.getId());
			this.productRepository.save(product);
			return product;
		}else {
			return null;
		}
	}

	@Override
	public void delete(Long id) {
		Product product = this.productRepository.findOne(id);
		if(product != null) this.productRepository.delete(product);
	}

}
