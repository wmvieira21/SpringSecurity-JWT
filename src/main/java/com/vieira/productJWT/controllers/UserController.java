package com.vieira.productJWT.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vieira.productJWT.domain.User;
import com.vieira.productJWT.dtos.UserDTO;
import com.vieira.productJWT.service.UserService;

import jakarta.validation.Valid;

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

	@PostMapping
	public ResponseEntity<User> save(@RequestBody UserDTO userDTO) {
		User user = new User(null, userDTO.login(), userDTO.password(), userDTO.role());
		user = userService.saveUser(user);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
}
