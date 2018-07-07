package com.maass.finance.application.business.facadeImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.maass.finance.application.beans.authentication.InitiateAuthenticationResponse;
import com.maass.finance.application.beans.exceptions.ApplicationException;
import com.maass.finance.application.beans.exceptions.BusinessException;
import com.maass.finance.application.business.facade.UserAuthenticationFacade;
import com.maass.finance.application.business.service.UserAuthenticationBusinessService;
import com.maass.finance.application.helpers.ExceptionResolver;

public class UserAuthenticationFacadeImpl implements UserAuthenticationFacade {

	@Autowired
	private UserAuthenticationBusinessService userAuthenticationBusinessService;
	
	public InitiateAuthenticationResponse initiateAuthentication(String token) {
		InitiateAuthenticationResponse initiateAuthenticationResponse = null;
		try {
			if(token != null && !token.equals("")) {
				initiateAuthenticationResponse = userAuthenticationBusinessService.initiateAuthentication(token);
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
			System.out.println("Exception occured in initiate authentication method");
			exception.printStackTrace();
			initiateAuthenticationResponse = new InitiateAuthenticationResponse();
			initiateAuthenticationResponse.setHttpCode(500);
			initiateAuthenticationResponse.setBussinessFaultCode("BUS-CONTROL-500");
			initiateAuthenticationResponse.setBusinessMessage("Application Faild to process the request");
			initiateAuthenticationResponse.setMessage("Internal Server Error");
		}
		return initiateAuthenticationResponse;
	}
	
}
