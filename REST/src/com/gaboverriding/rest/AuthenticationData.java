package com.gaboverriding.rest;

import java.util.ArrayList;
import java.util.List;


public class AuthenticationData {
	private String idAuthenticationData;
	private List<String> authenticationData = new ArrayList<String>();


	public List<String> getAuthenticationData() {
		return authenticationData;
	}

	public void setAuthenticationData(List<String> authenticationData) {
		this.authenticationData = authenticationData;
	}

	public String getIdAuthenticationData() {
		return idAuthenticationData;
	}

	public void setIdAuthenticationData(String idAuthenticationData) {
		this.idAuthenticationData = idAuthenticationData;
	}
	
}
