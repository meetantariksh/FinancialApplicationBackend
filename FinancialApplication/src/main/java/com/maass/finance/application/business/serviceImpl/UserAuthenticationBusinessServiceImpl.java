package com.maass.finance.application.business.serviceImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.maass.finance.application.beans.authentication.AuthenticationDetailsResponse;
import com.maass.finance.application.beans.authentication.InitiateAuthenticationRequest;
import com.maass.finance.application.beans.authentication.InitiateAuthenticationResponse;
import com.maass.finance.application.beans.authentication.SeccondFactorAuthenticationDetails;
import com.maass.finance.application.beans.exceptions.ApplicationException;
import com.maass.finance.application.beans.exceptions.BusinessException;
import com.maass.finance.application.business.service.UserAuthenticationBusinessService;
import com.maass.finance.application.collections.InitiateAuthenticationCollection;
import com.maass.finance.application.collections.SeccondFactorAuthenticationCollection;
import com.maass.finance.application.collections.SecurityQuestionsCollections;
import com.maass.finance.application.collections.UserProfileCollection;
import com.maass.finance.application.dao.UserAuthenticationDAO;
import com.maass.finance.application.helpers.Constants;
import com.maass.finance.application.helpers.ExceptionResolver;
import com.maass.finance.application.helpers.StringHandlers;

@Service
public class UserAuthenticationBusinessServiceImpl implements
		UserAuthenticationBusinessService {
	
	@Autowired
	private UserAuthenticationDAO userAuthenticationDAO;
	
	@Value("${application.authentication.token.constant}")
	private String authenticationTokenConstant;
	
	@Value("${initial.authentication.token.subject}")
	private String initialAuthenticationTokenSubject;
	
	public InitiateAuthenticationResponse initiateAuthentication(InitiateAuthenticationRequest initiateAuthenticationRequest) throws BusinessException, ApplicationException{
		
		InitiateAuthenticationResponse initiateAuthenticationResponse = null;
		UserProfileCollection userProfile = null;
		try {
			if(initiateAuthenticationRequest != null &&
					!StringHandlers.isStringNullOrEmpty(initiateAuthenticationRequest.getEmail()) &&
					!StringHandlers.isStringNullOrEmpty(initiateAuthenticationRequest.getSubject()) &&
					!StringHandlers.isStringNullOrEmpty(initiateAuthenticationRequest.getToken())) {
				userProfile = userAuthenticationDAO.getUserProfileFromEmail(initiateAuthenticationRequest.getEmail());
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
					if(userAuthenticationDAO.registerInitiateAuthenticationRequest(initiateAuthenticationCollection)) {
						Map<String, Object> claims = new HashMap<String, Object>();
						claims.put("email", userProfile.getEmail());
						claims.put("access_token", access_token);
						claims.put("auth0_subject", initiateAuthenticationRequest.getSubject());
						claims.put("auth0_token", initiateAuthenticationRequest.getToken());
						claims.put("authentication_type", Constants.AUTHENTICATION_INPROGRESS);
						String jwt = Jwts.builder()
								.setSubject(initialAuthenticationTokenSubject)
								.addClaims(claims)
								.signWith(SignatureAlgorithm.HS256, authenticationTokenConstant)
								.compact();
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
	
	public UserProfileCollection getUserProfileCollectionInitialAuthentication(String auth0Token, String auth0Subject, String accessToken) throws BusinessException, ApplicationException{
		UserProfileCollection userProfileCollection = null;
		InitiateAuthenticationCollection initiateAuthenticationCollection = null;
		try {
			initiateAuthenticationCollection = userAuthenticationDAO.getAuthenticationRequest(auth0Token, auth0Subject, accessToken);
			if(null != initiateAuthenticationCollection) {
				Date currentDate = new Date();
				long difference = currentDate.getTime() - initiateAuthenticationCollection.getToken_last_update_time();
				if(difference/(60 * 1000) > 15) {
					System.out.println("Token expired..");
					userAuthenticationDAO.deleteInitialAuthentication(initiateAuthenticationCollection);
					throw (new BusinessException("Token Expired", "Token Expired", "BUS-TKN-EXP-509", "Access Token found in JWT has Expired"));
				}else {
					String email = initiateAuthenticationCollection.getEmail();
					userProfileCollection = userAuthenticationDAO.getUserProfileFromEmail(email);
				}
			}
		}catch(ApplicationException applicationException) {
			applicationException.printStackTrace();
			throw applicationException;
		}catch(Exception exception) {
			System.out.println("Exception occured in initiate authentication method");
			exception.printStackTrace();
			throw exception;
		}
		
		return userProfileCollection;
	}
	
	public void deleteInititalAuthenticationToken(String auth0Token, String auth0Subject, String accessToken) throws BusinessException, ApplicationException {
		InitiateAuthenticationCollection initiateAuthenticationCollection = null;
		try {
			initiateAuthenticationCollection = userAuthenticationDAO.getAuthenticationRequest(auth0Token, auth0Subject, accessToken);
			userAuthenticationDAO.deleteInitialAuthentication(initiateAuthenticationCollection);
		}catch (Exception exception) {
			System.out.println("Exception occured in initiate authentication method");
			exception.printStackTrace();
			throw exception;
		}
	}
	
	@Override
	public AuthenticationDetailsResponse getSeccondFactorAuthentication(UserProfileCollection userProfileCollection) throws BusinessException, ApplicationException {
		AuthenticationDetailsResponse authenticationDetailsResponse = null;
		try {
			if(null != userProfileCollection && !StringHandlers.isStringNullOrEmpty(userProfileCollection.getUser_id())) {
				SeccondFactorAuthenticationCollection seccondFactorAuthenticationCollection = userAuthenticationDAO.getSeccondFactorAuthenticationForUser(userProfileCollection.getUser_id());
				if(null != seccondFactorAuthenticationCollection
						&& !StringHandlers.isStringNullOrEmpty(seccondFactorAuthenticationCollection.getQuestion_one_id())
						&& !StringHandlers.isStringNullOrEmpty(seccondFactorAuthenticationCollection.getQuestion_two_id())
						&& !StringHandlers.isStringNullOrEmpty(seccondFactorAuthenticationCollection.getQuestion_three_text())) {
					List<SecurityQuestionsCollections> securityQuestionsCollections = userAuthenticationDAO.getUserSelectedSecurityQuestions(seccondFactorAuthenticationCollection.getQuestion_one_id(), seccondFactorAuthenticationCollection.getQuestion_two_id());
					if(null != securityQuestionsCollections && securityQuestionsCollections.size() == 2) {
						SeccondFactorAuthenticationDetails seccondFactorAuthenticationDetails = new SeccondFactorAuthenticationDetails();
						seccondFactorAuthenticationDetails.setEmail(seccondFactorAuthenticationCollection.getEmail());
						if(seccondFactorAuthenticationCollection.getQuestion_failed_attempts() == 2) {
							seccondFactorAuthenticationDetails.setSecurityQuestionsBlocked(false);
							seccondFactorAuthenticationDetails.setSecurityQuestionID("--");
							seccondFactorAuthenticationDetails.setSecurityQuestionText(seccondFactorAuthenticationCollection.getQuestion_three_text());
						}else if(seccondFactorAuthenticationCollection.getQuestion_failed_attempts() > 2) {
							seccondFactorAuthenticationDetails.setSecurityQuestionsBlocked(true);
						}else {
							seccondFactorAuthenticationDetails.setSecurityQuestionsBlocked(false);
							seccondFactorAuthenticationDetails.setSecurityQuestionID(securityQuestionsCollections.get(seccondFactorAuthenticationCollection.getQuestion_failed_attempts()).getQuestion_id());
							seccondFactorAuthenticationDetails.setSecurityQuestionText(securityQuestionsCollections.get(seccondFactorAuthenticationCollection.getQuestion_failed_attempts()).getQuestion_text());
						}
						if(seccondFactorAuthenticationCollection.getEmail_failed_attempts() > 2) {
							seccondFactorAuthenticationDetails.setEmailBlocked(true);
						}else {
							seccondFactorAuthenticationDetails.setEmailBlocked(false);
						}
						authenticationDetailsResponse = new AuthenticationDetailsResponse(false, userProfileCollection, seccondFactorAuthenticationDetails);
					}else {
						throw (new ApplicationException("Exception from - initiateAuthentication", 
								ExceptionResolver.MANDATORY_DATA_NOT_RECIEVED.getMessage(), 
								ExceptionResolver.MANDATORY_DATA_NOT_RECIEVED.getBussinessFaultCode(), 
								ExceptionResolver.MANDATORY_DATA_NOT_RECIEVED.getBusinessMessage()));
					}
				}else {
					throw (new ApplicationException("Exception from - initiateAuthentication", 
							ExceptionResolver.MANDATORY_DATA_NOT_RECIEVED.getMessage(), 
							ExceptionResolver.MANDATORY_DATA_NOT_RECIEVED.getBussinessFaultCode(), 
							ExceptionResolver.MANDATORY_DATA_NOT_RECIEVED.getBusinessMessage()));
				}
			}else {
				throw (new ApplicationException("Exception from - initiateAuthentication", 
						ExceptionResolver.MANDATORY_DATA_NOT_RECIEVED.getMessage(), 
						ExceptionResolver.MANDATORY_DATA_NOT_RECIEVED.getBussinessFaultCode(), 
						ExceptionResolver.MANDATORY_DATA_NOT_RECIEVED.getBusinessMessage()));
			}
		}catch(ApplicationException applicationException) {
			applicationException.printStackTrace();
			authenticationDetailsResponse = new AuthenticationDetailsResponse();
			authenticationDetailsResponse.setHttpCode(501);
			authenticationDetailsResponse.setBussinessFaultCode(applicationException.getBussinessFaultCode());
			authenticationDetailsResponse.setBusinessMessage(applicationException.getBussinessFaultCode());
			authenticationDetailsResponse.setMessage(applicationException.getMessage());
		}catch(Exception exception) {
			System.out.println("Exception occured in initiate authentication method");
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
