package com.maass.finance.application.beans.authentication;

import com.maass.finance.application.beans.BaseResponse;
import com.maass.finance.application.collections.UserProfileCollection;

public class AuthenticationDetailsResponse extends BaseResponse{
	private boolean isUserFullyAuthenticated;
	private UserProfileCollection userProfileCollection;
	private SeccondFactorAuthenticationDetails seccondFactorAuthenticationDetails;
	
	public AuthenticationDetailsResponse(
			boolean isUserFullyAuthenticated,
			UserProfileCollection userProfileCollection,
			SeccondFactorAuthenticationDetails seccondFactorAuthenticationDetails) {
		super();
		this.isUserFullyAuthenticated = isUserFullyAuthenticated;
		this.userProfileCollection = userProfileCollection;
		this.seccondFactorAuthenticationDetails = seccondFactorAuthenticationDetails;
	}
	
	public AuthenticationDetailsResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isUserFullyAuthenticated() {
		return isUserFullyAuthenticated;
	}
	public void setUserFullyAuthenticated(boolean isUserFullyAuthenticated) {
		this.isUserFullyAuthenticated = isUserFullyAuthenticated;
	}
	public UserProfileCollection getUserProfileCollection() {
		return userProfileCollection;
	}
	public void setUserProfileCollection(UserProfileCollection userProfileCollection) {
		this.userProfileCollection = userProfileCollection;
	}
	public SeccondFactorAuthenticationDetails getSeccondFactorAuthenticationDetails() {
		return seccondFactorAuthenticationDetails;
	}
	public void setSeccondFactorAuthenticationDetails(
			SeccondFactorAuthenticationDetails seccondFactorAuthenticationDetails) {
		this.seccondFactorAuthenticationDetails = seccondFactorAuthenticationDetails;
	}
}
