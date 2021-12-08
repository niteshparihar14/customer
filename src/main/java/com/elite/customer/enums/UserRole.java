package com.elite.customer.enums;

public enum UserRole {

	Customer("C"), BankExecutive("E");

	private String role;

	private UserRole(String role) {
		this.role = role;
	}

	public String value() {
		return this.role;
	}
}
