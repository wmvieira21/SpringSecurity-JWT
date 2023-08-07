package com.vieira.productJWT.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vieira.productJWT.controllers.UserController;
import com.vieira.productJWT.domain.User;
import com.vieira.productJWT.dtos.UserDTO;
import com.vieira.productJWT.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		return userRepository.findAll().stream()
				.map(user -> user.add(linkTo(methodOn(UserController.class).findByID(user.getId())).withSelfRel()))
				.toList();
	}

	public User findByID(UUID id) {
		var user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Object not found"));
		user.add(linkTo(methodOn(UserController.class).findAll()).withRel("Product list"));
		return user;
	}

	public static UserDTO fromUserToDTO(User user) {
		return new UserDTO(user.getId(), user.getLogin(), user.getPassword(), user.getRole(), user.getLinks());
	}
}
