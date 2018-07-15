package com.maass.finance.application.controller.authentication;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.maass.finance.application.beans.authentication.InitiateAuthenticationRequest;
import com.maass.finance.application.beans.authentication.InitiateAuthenticationResponse;
import com.maass.finance.application.business.facade.UserAuthenticationFacade;

@RestController
@CrossOrigin("*")
@RequestMapping("/authentication/*")
public class UserAuthenticationController {
	
	@Autowired
	private UserAuthenticationFacade userAuthenticationFacade;  
	
	@RequestMapping(
			value = "/initiateAuthentication",
			method = {RequestMethod.POST, RequestMethod.GET}
			)
	public InitiateAuthenticationResponse initiateAuthentication(@RequestBody Map<String, Object> payload){
		System.out.println("Entering initiate authentication method...");
		InitiateAuthenticationResponse initiateAuthenticationResponse = null;
		InitiateAuthenticationRequest initiateAuthenticationRequest = null;
		try {
			initiateAuthenticationRequest = new InitiateAuthenticationRequest();
			initiateAuthenticationRequest.setEmail(payload.get("email").toString());
			initiateAuthenticationRequest.setToken(payload.get("token").toString());
			initiateAuthenticationRequest.setSubject(payload.get("sub").toString());
			initiateAuthenticationResponse = userAuthenticationFacade.initiateAuthentication(initiateAuthenticationRequest);
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
