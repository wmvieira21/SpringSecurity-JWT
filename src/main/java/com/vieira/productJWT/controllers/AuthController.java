package com.vieira.productJWT.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vieira.productJWT.domain.User;
import com.vieira.productJWT.dtos.AuthenticationDTO;
import com.vieira.productJWT.dtos.RegisterDTO;
import com.vieira.productJWT.repository.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@PostMapping(value = "/login")
	public ResponseEntity<Void> login(@RequestBody @Valid AuthenticationDTO dto) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);

		return ResponseEntity.ok().build();
	}

	@PostMapping(value = "/register")
	public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO dto) {

		if (userRepository.findByLogin(dto.login()) != null) {
			return ResponseEntity.badRequest().build();
		}
		
		String encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
		User user = new User(dto.login(), encryptedPassword, dto.role());

		this.userRepository.save(user);

		return ResponseEntity.ok().build();
	}
}
