package com.maass.finance.application.beans.authentication;

import com.maass.finance.application.beans.BaseResponse;

public class InitiateAuthenticationResponse extends BaseResponse {
	private String initialAuthenticationToken = "";

	public String getInitialAuthenticationToken() {
		return initialAuthenticationToken;
	}

	public void setInitialAuthenticationToken(String initialAuthenticationToken) {
		this.initialAuthenticationToken = initialAuthenticationToken;
	}
}
