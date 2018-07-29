package com.maass.finance.application.business.facade;

import org.springframework.security.core.Authentication;

import com.maass.finance.application.beans.authentication.AuthenticationDetailsResponse;
import com.maass.finance.application.beans.authentication.InitiateAuthenticationRequest;
import com.maass.finance.application.beans.authentication.InitiateAuthenticationResponse;

public interface UserAuthenticationFacade {
	public InitiateAuthenticationResponse initiateAuthentication(InitiateAuthenticationRequest initiateAuthenticationRequest);
	public AuthenticationDetailsResponse getSeccondFactorAuthentication(Authentication authentication);
}
