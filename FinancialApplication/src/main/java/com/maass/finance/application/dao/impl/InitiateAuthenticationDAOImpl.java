package com.maass.finance.application.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maass.finance.application.beans.exceptions.ApplicationException;
import com.maass.finance.application.collections.InitiateAuthenticationCollection;
import com.maass.finance.application.dao.InitiateAuthenticationDAO;
import com.maass.finance.application.helpers.ExceptionResolver;
import com.maass.finance.application.repositories.InitiateAuthenticationRepsitory;

@Service
public class InitiateAuthenticationDAOImpl implements InitiateAuthenticationDAO {
	
	@Autowired
	private InitiateAuthenticationRepsitory iniateAuthenticationRepository;

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

}
