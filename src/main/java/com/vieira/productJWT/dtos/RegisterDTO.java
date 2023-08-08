package com.vieira.productJWT.dtos;

import com.vieira.productJWT.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {

}
