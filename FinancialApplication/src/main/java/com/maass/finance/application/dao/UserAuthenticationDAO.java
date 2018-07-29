package com.maass.finance.application.dao;

import java.util.List;

import com.maass.finance.application.beans.exceptions.ApplicationException;
import com.maass.finance.application.beans.news.NewsHeadLines;
import com.maass.finance.application.collections.InitiateAuthenticationCollection;
import com.maass.finance.application.collections.SeccondFactorAuthenticationCollection;
import com.maass.finance.application.collections.SecurityQuestionsCollections;
import com.maass.finance.application.collections.UserProfileCollection;

public interface UserAuthenticationDAO {
	public boolean registerInitiateAuthenticationRequest(InitiateAuthenticationCollection initiateAuthenticationCollection) throws ApplicationException;
	public InitiateAuthenticationCollection getAuthenticationRequest(String auth0Token, String auth0Subject, String accessToken) throws ApplicationException;
	public void deleteInitialAuthentication(InitiateAuthenticationCollection initiateAuthenticationCollection) throws ApplicationException;
	public NewsHeadLines getNewsHeadLines();
	public UserProfileCollection getUserProfileFromEmail(String email) throws ApplicationException;
	public SeccondFactorAuthenticationCollection getSeccondFactorAuthenticationForUser(String user_id) throws ApplicationException;
	public List<SecurityQuestionsCollections> getUserSelectedSecurityQuestions(String questionOneID, String questionTwoID) throws ApplicationException;
}
