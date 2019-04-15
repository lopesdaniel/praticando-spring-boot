package com.danieldev.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.danieldev.api.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
