package com.vieira.productJWT.enums;

public enum UserRole {

	ADMIN("ADMIN"), DEFAULT("DEFAULT");

	private String role;

	private UserRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return this.role;
	}
}
