package com.vieira.productJWT.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vieira.productJWT.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

}
