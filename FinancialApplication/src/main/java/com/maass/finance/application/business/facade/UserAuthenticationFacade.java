package com.maass.finance.application.business.facade;

import com.maass.finance.application.beans.authentication.InitiateAuthenticationResponse;

public interface UserAuthenticationFacade {
	public InitiateAuthenticationResponse initiateAuthentication(String token);
}
