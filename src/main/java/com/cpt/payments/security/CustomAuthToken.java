package com.cpt.payments.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class CustomAuthToken extends AbstractAuthenticationToken {
	public CustomAuthToken() {
		super(Arrays.asList());
		super.setAuthenticated(true);
	}
	
	@Override
	public Object getCredentials() {
		return null;
	}
	@Override
	public Object getPrincipal() {
		return null;
	}
}

