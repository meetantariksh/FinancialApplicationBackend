package com.maass.finance.application.dao;

import com.maass.finance.application.beans.exceptions.ApplicationException;
import com.maass.finance.application.collections.InitiateAuthenticationCollection;

public interface InitiateAuthenticationDAO {
	public boolean registerInitiateAuthenticationRequest(InitiateAuthenticationCollection initiateAuthenticationCollection) throws ApplicationException;
}
