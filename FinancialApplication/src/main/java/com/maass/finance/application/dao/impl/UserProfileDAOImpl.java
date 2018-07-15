package com.maass.finance.application.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maass.finance.application.beans.exceptions.ApplicationException;
import com.maass.finance.application.collections.UserProfileCollection;
import com.maass.finance.application.dao.UserProfileDAO;
import com.maass.finance.application.helpers.ExceptionResolver;
import com.maass.finance.application.repositories.UserProfileRepository;

@Service
public class UserProfileDAOImpl implements UserProfileDAO{
	
	@Autowired
	private UserProfileRepository userProfileRepository;

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
	
}
