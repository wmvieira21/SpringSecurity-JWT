package com.vieira.productJWT.controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vieira.productJWT.dtos.UserDTO;
import com.vieira.productJWT.service.UserService;

@RestController
@RequestMapping(value = "users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		return ResponseEntity.ok().body(userService.findAll().stream().map(UserService::fromUserToDTO).toList());
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findByID(@PathVariable UUID id) {
		var user = userService.findByID(id);
		return ResponseEntity.ok().body(UserService.fromUserToDTO(user));
	}
}
