package com.vieira.productJWT.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vieira.productJWT.domain.Product;
import com.vieira.productJWT.dtos.ProductDTO;
import com.vieira.productJWT.service.ProductService;

@RestController
@RequestMapping(value = "products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
		return ResponseEntity.ok()
				.body(productService.findAll().stream().map(ProductService::fromProductToDTO).toList());
	}

	@PostMapping
	public ResponseEntity<ProductDTO> save(@RequestBody ProductDTO product) {
		Product pro = productService.save(product);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pro.getId()).toUri();

		return ResponseEntity.created(uri).body(ProductService.fromProductToDTO(pro));

	}
}
