package com.vieira.productJWT.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vieira.productJWT.domain.Product;
import com.vieira.productJWT.domain.User;
import com.vieira.productJWT.dtos.ProductDTO;
import com.vieira.productJWT.dtos.UserDTO;
import com.vieira.productJWT.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Product findById(UUID id) {
		return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
	}

	public Product save(ProductDTO product) {
		Product p = new Product(null, product.name(), product.price());
		return productRepository.save(p);
	}

	public static ProductDTO fromProductToDTO(Product p) {
		return new ProductDTO(p.getId(), p.getName(), p.getPrice());
	}
}
