package com.maass.finance.application.dao.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maass.finance.application.beans.exceptions.ApplicationException;
import com.maass.finance.application.beans.news.NewsHeadLines;
import com.maass.finance.application.collections.InitiateAuthenticationCollection;
import com.maass.finance.application.collections.SeccondFactorAuthenticationCollection;
import com.maass.finance.application.collections.SecurityQuestionsCollections;
import com.maass.finance.application.collections.UserProfileCollection;
import com.maass.finance.application.dao.UserAuthenticationDAO;
import com.maass.finance.application.helpers.ExceptionResolver;
import com.maass.finance.application.repositories.InitiateAuthenticationRepsitory;
import com.maass.finance.application.repositories.SeccondFactorAuthenticationRepository;
import com.maass.finance.application.repositories.SecurityQuestionsRepository;
import com.maass.finance.application.repositories.UserProfileRepository;

@Service
public class UserAuthenticationDAOImpl implements UserAuthenticationDAO{
	@Autowired
	private UserProfileRepository userProfileRepository;

	@Autowired
	private InitiateAuthenticationRepsitory iniateAuthenticationRepository;

	@Autowired
	private SeccondFactorAuthenticationRepository seccondFactorAuthenticationRepository;

	@Autowired
	private SecurityQuestionsRepository securityQuestionsRepository;

	private static final String apiKey="d96de1b2807b4c118aca85ecdbe7d4da";

	@Override
	public NewsHeadLines getNewsHeadLines(){
		NewsHeadLines headLines = null;
		String apiUrI = null;
		HttpURLConnection conn=null;
		BufferedReader reader=null;
		StringBuilder strBuf =  null;
		String output = null;
		Gson gson = null;
		try{
			apiUrI = "https://newsapi.org/v2/top-headlines?sources=google-news-in";
			strBuf = new StringBuilder();  
			URL url = new URL(apiUrI);  
			conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("X-Api-Key", apiKey);

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("HTTP GET Request Failed with Error code : "
						+ conn.getResponseCode());
			}

			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			while ((output = reader.readLine()) != null){
				strBuf.append(output);
			}

			gson = new GsonBuilder().create();
			headLines = gson.fromJson(strBuf.toString(), NewsHeadLines.class);

		}catch (Exception exp){
			exp.printStackTrace();
		}finally {
			if(reader!=null)
			{
				try {
					reader.close();
				} catch (Exception exp) {
					exp.printStackTrace();
				}
			}
			if(conn!=null)
			{
				conn.disconnect();
			}
		}

		return headLines;
	}

	@Override
	public boolean registerInitiateAuthenticationRequest(
			InitiateAuthenticationCollection initiateAuthenticationCollection)
					throws ApplicationException {
		try {
			if(initiateAuthenticationCollection != null) {
				InitiateAuthenticationCollection createdAuthenticationCollection = iniateAuthenticationRepository.insert(initiateAuthenticationCollection);
				if(createdAuthenticationCollection != null && createdAuthenticationCollection.getUser_id().equals(initiateAuthenticationCollection.getUser_id())) {
					return true;
				}else {
					throw (new ApplicationException("Database Error", 
							ExceptionResolver.DATABASE_ERROR.getMessage(), 
							ExceptionResolver.DATABASE_ERROR.getBussinessFaultCode(), 
							ExceptionResolver.DATABASE_ERROR.getBusinessMessage()));
				}
			}else {
				throw (new ApplicationException("Exception from - initiateAuthentication", 
						ExceptionResolver.MANDATORY_DATA_NOT_RECIEVED.getMessage(), 
						ExceptionResolver.MANDATORY_DATA_NOT_RECIEVED.getBussinessFaultCode(), 
						ExceptionResolver.MANDATORY_DATA_NOT_RECIEVED.getBusinessMessage()));
			}
		}catch(Exception exception) {
			exception.printStackTrace();
			throw (new ApplicationException("Database Error", 
					ExceptionResolver.DATABASE_ERROR.getMessage(), 
					ExceptionResolver.DATABASE_ERROR.getBussinessFaultCode(), 
					ExceptionResolver.DATABASE_ERROR.getBusinessMessage()));
		}
	}

	@Override
	public InitiateAuthenticationCollection getAuthenticationRequest(
			String auth0Token, String auth0Subject, String accessToken)
					throws ApplicationException {
		InitiateAuthenticationCollection initiateAuthenticationCollection = null;
		try {
			initiateAuthenticationCollection = iniateAuthenticationRepository.getInitiatAuthenticationCollection(auth0Token, auth0Subject, accessToken);
		}catch(Exception exception) {
			exception.printStackTrace();
			throw (new ApplicationException("Database Error", 
					ExceptionResolver.DATABASE_ERROR.getMessage(), 
					ExceptionResolver.DATABASE_ERROR.getBussinessFaultCode(), 
					ExceptionResolver.DATABASE_ERROR.getBusinessMessage()));
		}
		return initiateAuthenticationCollection;
	}

	@Override
	public void deleteInitialAuthentication(
			InitiateAuthenticationCollection initiateAuthenticationCollection)
					throws ApplicationException {
		try {
			iniateAuthenticationRepository.delete(initiateAuthenticationCollection);
		}catch(Exception exception) {
			exception.printStackTrace();
			throw (new ApplicationException("Database Error", 
					ExceptionResolver.DATABASE_ERROR.getMessage(), 
					ExceptionResolver.DATABASE_ERROR.getBussinessFaultCode(), 
					ExceptionResolver.DATABASE_ERROR.getBusinessMessage()));
		}
	}

	@Override
	public UserProfileCollection getUserProfileFromEmail(String email) throws ApplicationException {
		UserProfileCollection userProfile = null;
		try{
			userProfile = userProfileRepository.getUserProfileFromEmail(email);
		}catch(Exception exp) {
			exp.printStackTrace();
			throw (new ApplicationException("Database Error", 
					ExceptionResolver.DATABASE_ERROR.getMessage(), 
					ExceptionResolver.DATABASE_ERROR.getBussinessFaultCode(), 
					ExceptionResolver.DATABASE_ERROR.getBusinessMessage()));
		}
		return userProfile;
	}

	@Override
	public SeccondFactorAuthenticationCollection getSeccondFactorAuthenticationForUser(String user_id) throws ApplicationException{
		SeccondFactorAuthenticationCollection seccondFactorAuthenticationCollection = null;
		try {
			seccondFactorAuthenticationCollection = seccondFactorAuthenticationRepository.getSeccondFactorAuthenticationByUserId(user_id);
		}catch(Exception exp) {
			exp.printStackTrace();
			throw (new ApplicationException("Database Error", 
					ExceptionResolver.DATABASE_ERROR.getMessage(), 
					ExceptionResolver.DATABASE_ERROR.getBussinessFaultCode(), 
					ExceptionResolver.DATABASE_ERROR.getBusinessMessage()));
		}
		return seccondFactorAuthenticationCollection;
	}

	@Override
	public List<SecurityQuestionsCollections> getUserSelectedSecurityQuestions(String questionOneID, String questionTwoID) throws ApplicationException{
		List<SecurityQuestionsCollections> securityQuestionsCollections = null;
		try {
			securityQuestionsCollections = securityQuestionsRepository.getUserSecurityQuestions(questionOneID, questionTwoID);
		}catch(Exception exp) {
			exp.printStackTrace();
			throw (new ApplicationException("Database Error", 
					ExceptionResolver.DATABASE_ERROR.getMessage(), 
					ExceptionResolver.DATABASE_ERROR.getBussinessFaultCode(), 
					ExceptionResolver.DATABASE_ERROR.getBusinessMessage()));
		}
		return securityQuestionsCollections;
	}

}
