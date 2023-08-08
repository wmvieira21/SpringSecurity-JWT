package com.vieira.productJWT.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.vieira.productJWT.domain.Product;
import com.vieira.productJWT.repository.ProductRepository;
import com.vieira.productJWT.repository.UserRepository;

@Configuration
public class Test implements CommandLineRunner {

	@Autowired
	private ProductRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
	}
}
