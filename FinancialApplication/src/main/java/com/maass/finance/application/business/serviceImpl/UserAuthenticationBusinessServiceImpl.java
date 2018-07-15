package com.maass.finance.application.business.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maass.finance.application.beans.authentication.InitiateAuthenticationRequest;
import com.maass.finance.application.beans.authentication.InitiateAuthenticationResponse;
import com.maass.finance.application.beans.exceptions.ApplicationException;
import com.maass.finance.application.beans.exceptions.BusinessException;
import com.maass.finance.application.business.service.UserAuthenticationBusinessService;
import com.maass.finance.application.collections.UserProfileCollection;
import com.maass.finance.application.dao.UserProfileDAO;
import com.maass.finance.application.helpers.ExceptionResolver;
import com.maass.finance.application.helpers.StringHandlers;

@Service
public class UserAuthenticationBusinessServiceImpl implements
		UserAuthenticationBusinessService {
	
	@Autowired
	private UserProfileDAO userProfileDAO;
	
	public InitiateAuthenticationResponse initiateAuthentication(InitiateAuthenticationRequest initiateAuthenticationRequest) throws BusinessException, ApplicationException{
		
		InitiateAuthenticationResponse initiateAuthenticationResponse = null;
		UserProfileCollection userProfile = null;
		try {
			if(initiateAuthenticationRequest != null &&
					!StringHandlers.isStringNullOrEmpty(initiateAuthenticationRequest.getEmail()) &&
					!StringHandlers.isStringNullOrEmpty(initiateAuthenticationRequest.getSubject()) &&
					!StringHandlers.isStringNullOrEmpty(initiateAuthenticationRequest.getToken())) {
				userProfile = userProfileDAO.getUserProfileFromEmail(initiateAuthenticationRequest.getEmail());
				if(userProfile != null) {
					System.out.println("User profile populated...");
					initiateAuthenticationResponse = new InitiateAuthenticationResponse();
					initiateAuthenticationResponse.setInitialAuthenticationToken("Token-Will-Be-Generated");
				}else {
					throw (new BusinessException("User Profile not found.", 
							ExceptionResolver.USER_PROFILE_NOT_FOUND.getMessage(), 
							ExceptionResolver.USER_PROFILE_NOT_FOUND.getBussinessFaultCode(), 
							ExceptionResolver.USER_PROFILE_NOT_FOUND.getBusinessMessage()));
				}
			}else {
				throw (new ApplicationException("Exception from - initiateAuthentication", 
						ExceptionResolver.MANDATORY_DATA_NOT_RECIEVED.getMessage(), 
						ExceptionResolver.MANDATORY_DATA_NOT_RECIEVED.getBussinessFaultCode(), 
						ExceptionResolver.MANDATORY_DATA_NOT_RECIEVED.getBusinessMessage()));
			}	
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
