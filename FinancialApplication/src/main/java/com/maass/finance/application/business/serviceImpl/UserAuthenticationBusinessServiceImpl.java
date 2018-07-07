package com.maass.finance.application.business.serviceImpl;

import com.maass.finance.application.beans.authentication.InitiateAuthenticationResponse;
import com.maass.finance.application.beans.exceptions.ApplicationException;
import com.maass.finance.application.beans.exceptions.BusinessException;
import com.maass.finance.application.business.service.UserAuthenticationBusinessService;

public class UserAuthenticationBusinessServiceImpl implements
		UserAuthenticationBusinessService {
	
	public InitiateAuthenticationResponse initiateAuthentication(String token) throws BusinessException, ApplicationException{
		
		InitiateAuthenticationResponse initiateAuthenticationResponse = null;
		try {
			
		}catch(ApplicationException applicationException) {
			applicationException.printStackTrace();
			initiateAuthenticationResponse = new InitiateAuthenticationResponse();
			initiateAuthenticationResponse.setHttpCode(502);
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
