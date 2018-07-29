package com.maass.finance.application.security;

import org.springframework.security.core.GrantedAuthority;

public class Authorities implements GrantedAuthority {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String authority;
	
	public Authorities(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}
	
}
