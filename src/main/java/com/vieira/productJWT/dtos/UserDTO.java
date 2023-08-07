package com.vieira.productJWT.dtos;

import java.util.UUID;

import org.springframework.hateoas.Links;

import com.vieira.productJWT.enums.UserRole;

public record UserDTO(UUID id, String login, String password, UserRole role, Links link) {
	
}
