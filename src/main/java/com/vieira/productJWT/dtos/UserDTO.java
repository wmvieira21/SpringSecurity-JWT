package com.vieira.productJWT.dtos;

import java.util.UUID;

import org.springframework.hateoas.Links;

import com.vieira.productJWT.enums.UserRole;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserDTO(@NotNull UUID id, @NotEmpty String login, @NotEmpty String password, @NotNull UserRole role,
		Links link) {
}
