package com.maass.finance.application.dao;

import com.maass.finance.application.beans.exceptions.ApplicationException;
import com.maass.finance.application.collections.UserProfileCollection;

public interface UserProfileDAO {
	public UserProfileCollection getUserProfileFromEmail(String email) throws ApplicationException;
}
