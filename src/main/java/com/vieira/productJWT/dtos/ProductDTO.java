package com.vieira.productJWT.dtos;

import java.util.UUID;

public record ProductDTO(UUID id, String name, Double price) {
}
