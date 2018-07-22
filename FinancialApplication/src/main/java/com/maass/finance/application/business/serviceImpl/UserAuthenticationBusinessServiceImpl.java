package com.maass.finance.application.business.serviceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.maass.finance.application.beans.authentication.InitiateAuthenticationRequest;
import com.maass.finance.application.beans.authentication.InitiateAuthenticationResponse;
import com.maass.finance.application.beans.exceptions.ApplicationException;
import com.maass.finance.application.beans.exceptions.BusinessException;
import com.maass.finance.application.business.service.UserAuthenticationBusinessService;
import com.maass.finance.application.collections.InitiateAuthenticationCollection;
import com.maass.finance.application.collections.UserProfileCollection;
import com.maass.finance.application.dao.InitiateAuthenticationDAO;
import com.maass.finance.application.dao.UserProfileDAO;
import com.maass.finance.application.helpers.ExceptionResolver;
import com.maass.finance.application.helpers.StringHandlers;

@Service
public class UserAuthenticationBusinessServiceImpl implements
		UserAuthenticationBusinessService {
	
	@Autowired
	private UserProfileDAO userProfileDAO;
	
	@Autowired
	private InitiateAuthenticationDAO initiateAuthenticationDAO;
	
	@Value("${initial.authentication.token.constant}")
	private String authenticationTokenConstant;
	
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
					InitiateAuthenticationCollection initiateAuthenticationCollection = new InitiateAuthenticationCollection();
					String access_token = authenticationTokenConstant + Math.random() + "##$$" + userProfile.getUser_id();
					Date currentDate = new Date();
					initiateAuthenticationCollection.setAccess_token(access_token);
					initiateAuthenticationCollection.setAuth0_subject(initiateAuthenticationRequest.getSubject());
					initiateAuthenticationCollection.setAuth0_token(initiateAuthenticationRequest.getToken());
					initiateAuthenticationCollection.setEmail(initiateAuthenticationRequest.getEmail());
					initiateAuthenticationCollection.setFirst_name(userProfile.getFirst_name());
					initiateAuthenticationCollection.setLast_name(userProfile.getLast_name());
					initiateAuthenticationCollection.setUser_id(userProfile.getUser_id());
					initiateAuthenticationCollection.setToken_last_update_time(currentDate.getTime());
					if(initiateAuthenticationDAO.registerInitiateAuthenticationRequest(initiateAuthenticationCollection)) {
						Map<String, Object> claims = new HashMap<String, Object>();
						claims.put("email", userProfile.getEmail());
						claims.put("access_token", access_token);
						claims.put("auth0_subject", initiateAuthenticationRequest.getSubject());
						claims.put("auth0_token", initiateAuthenticationRequest.getToken());
						String jwt = Jwts.builder()
								.setSubject("INIT_AUTH_TOK")
								.setExpiration(new Date(currentDate.getTime() * 5 * 60 * 1000))
								.addClaims(claims)
								.signWith(SignatureAlgorithm.HS256, authenticationTokenConstant)
								.compact();
						System.out.println(jwt);
						initiateAuthenticationResponse.setInitialAuthenticationToken(jwt);
					}
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
