package com.maass.finance.application.business.facadeImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.maass.finance.application.beans.authentication.AuthenticationDetailsResponse;
import com.maass.finance.application.beans.authentication.InitiateAuthenticationRequest;
import com.maass.finance.application.beans.authentication.InitiateAuthenticationResponse;
import com.maass.finance.application.beans.exceptions.ApplicationException;
import com.maass.finance.application.beans.exceptions.BusinessException;
import com.maass.finance.application.business.facade.UserAuthenticationFacade;
import com.maass.finance.application.business.service.UserAuthenticationBusinessService;
import com.maass.finance.application.collections.UserProfileCollection;
import com.maass.finance.application.helpers.ExceptionResolver;
import com.maass.finance.application.security.AuthenticatedUser;

@Service
public class UserAuthenticationFacadeImpl implements UserAuthenticationFacade {

	@Autowired
	private UserAuthenticationBusinessService userAuthenticationBusinessService;
	
	@Override
	public InitiateAuthenticationResponse initiateAuthentication(InitiateAuthenticationRequest initiateAuthenticationRequest) {
		InitiateAuthenticationResponse initiateAuthenticationResponse = null;
		try {
			if(initiateAuthenticationRequest != null) {
				initiateAuthenticationResponse = userAuthenticationBusinessService.initiateAuthentication(initiateAuthenticationRequest);
			}else {
				initiateAuthenticationResponse = new InitiateAuthenticationResponse();
				initiateAuthenticationResponse.setHttpCode(ExceptionResolver.MANDATORY_DATA_NOT_RECIEVED.getHttpCode());
				initiateAuthenticationResponse.setBussinessFaultCode(ExceptionResolver.MANDATORY_DATA_NOT_RECIEVED.getBussinessFaultCode());
				initiateAuthenticationResponse.setBusinessMessage(ExceptionResolver.MANDATORY_DATA_NOT_RECIEVED.getBusinessMessage());
				initiateAuthenticationResponse.setMessage(ExceptionResolver.MANDATORY_DATA_NOT_RECIEVED.getMessage());
			}
		}catch(BusinessException businessException) {
			businessException.printStackTrace();
			initiateAuthenticationResponse = new InitiateAuthenticationResponse();
			initiateAuthenticationResponse.setHttpCode(502);
			initiateAuthenticationResponse.setBussinessFaultCode(businessException.getBussinessFaultCode());
			initiateAuthenticationResponse.setBusinessMessage(businessException.getBussinessFaultCode());
			initiateAuthenticationResponse.setMessage(businessException.getMessage());
		}catch(ApplicationException applicationException) {
			applicationException.printStackTrace();
			initiateAuthenticationResponse = new InitiateAuthenticationResponse();
			initiateAuthenticationResponse.setHttpCode(501);
			initiateAuthenticationResponse.setBussinessFaultCode(applicationException.getBussinessFaultCode());
			initiateAuthenticationResponse.setBusinessMessage(applicationException.getBussinessFaultCode());
			initiateAuthenticationResponse.setMessage(applicationException.getMessage());
		}catch(Exception exception) {
			exception.printStackTrace();
			initiateAuthenticationResponse = new InitiateAuthenticationResponse();
			initiateAuthenticationResponse.setHttpCode(500);
			initiateAuthenticationResponse.setBussinessFaultCode("BUS-CONTROL-500");
			initiateAuthenticationResponse.setBusinessMessage("Application Faild to process the request");
			initiateAuthenticationResponse.setMessage("Internal Server Error");
		}
		return initiateAuthenticationResponse;
	}
	
	@Override
	public AuthenticationDetailsResponse getSeccondFactorAuthentication(Authentication authentication) {
		AuthenticationDetailsResponse authenticationDetailsResponse = null;
		try {
			@SuppressWarnings("unchecked")
			List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
			UserProfileCollection userProfileCollection = ((AuthenticatedUser) authentication.getPrincipal()).getUserProfileCollection();
			boolean isUserFullyAuthenticated = true;
			for(int i = 0; i<authorities.size(); i++) {
				GrantedAuthority auth = authorities.get(i);
				if("ROLE_INITIAL_AUTH".equals(auth.getAuthority())) {
					isUserFullyAuthenticated = false;
					break;
				}
			}
			if(isUserFullyAuthenticated) {
				authenticationDetailsResponse = new AuthenticationDetailsResponse(true, userProfileCollection, null);
			}else {
				authenticationDetailsResponse = userAuthenticationBusinessService.getSeccondFactorAuthentication(userProfileCollection);
			}
		}catch(BusinessException businessException) {
			businessException.printStackTrace();
			authenticationDetailsResponse = new AuthenticationDetailsResponse();
			authenticationDetailsResponse.setHttpCode(502);
			authenticationDetailsResponse.setBussinessFaultCode(businessException.getBussinessFaultCode());
			authenticationDetailsResponse.setBusinessMessage(businessException.getBussinessFaultCode());
			authenticationDetailsResponse.setMessage(businessException.getMessage());
		}catch(ApplicationException applicationException) {
			applicationException.printStackTrace();
			authenticationDetailsResponse = new AuthenticationDetailsResponse();
			authenticationDetailsResponse.setHttpCode(501);
			authenticationDetailsResponse.setBussinessFaultCode(applicationException.getBussinessFaultCode());
			authenticationDetailsResponse.setBusinessMessage(applicationException.getBussinessFaultCode());
			authenticationDetailsResponse.setMessage(applicationException.getMessage());
		}catch(Exception exception) {
			exception.printStackTrace();
			authenticationDetailsResponse = new AuthenticationDetailsResponse();
			authenticationDetailsResponse.setHttpCode(500);
			authenticationDetailsResponse.setBussinessFaultCode("BUS-CONTROL-500");
			authenticationDetailsResponse.setBusinessMessage("Application Faild to process the request");
			authenticationDetailsResponse.setMessage("Internal Server Error");
		}
		return authenticationDetailsResponse;
	}
	
}
