package com.maass.finance.application.business.service;

import com.maass.finance.application.beans.authentication.AuthenticationDetailsResponse;
import com.maass.finance.application.beans.authentication.InitiateAuthenticationRequest;
import com.maass.finance.application.beans.authentication.InitiateAuthenticationResponse;
import com.maass.finance.application.beans.exceptions.ApplicationException;
import com.maass.finance.application.beans.exceptions.BusinessException;
import com.maass.finance.application.collections.UserProfileCollection;

public interface UserAuthenticationBusinessService {
	public InitiateAuthenticationResponse initiateAuthentication(InitiateAuthenticationRequest initiateAuthenticationRequest) throws BusinessException, ApplicationException;
	public UserProfileCollection getUserProfileCollectionInitialAuthentication(String auth0Token, String auth0Subject, String accessToken) throws BusinessException, ApplicationException;
	public void deleteInititalAuthenticationToken(String auth0Token, String auth0Subject, String accessToken) throws BusinessException, ApplicationException;
	public AuthenticationDetailsResponse getSeccondFactorAuthentication(UserProfileCollection userProfileCollection) throws BusinessException, ApplicationException;
}
