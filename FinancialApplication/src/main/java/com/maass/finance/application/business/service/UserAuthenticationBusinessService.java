package com.maass.finance.application.business.service;

import com.maass.finance.application.beans.authentication.InitiateAuthenticationResponse;
import com.maass.finance.application.beans.exceptions.ApplicationException;
import com.maass.finance.application.beans.exceptions.BusinessException;

public interface UserAuthenticationBusinessService {
	public InitiateAuthenticationResponse initiateAuthentication(String token) throws BusinessException, ApplicationException;
}
