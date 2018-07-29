package com.maass.finance.application.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.maass.finance.application.beans.exceptions.ApplicationException;
import com.maass.finance.application.business.service.UserAuthenticationBusinessService;
import com.maass.finance.application.collections.UserProfileCollection;

@Service
public class AuthenticatedUserService implements UserDetailsService {
	
	@Autowired
	private UserAuthenticationBusinessService userAuthenticationBusinessService;

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public UserDetails loadInitialAuthenticationUser(String auth0Subject, String auth0Token, String accessToken) throws UsernameNotFoundException, ApplicationException, Exception {
		AuthenticatedUser authenticatedUser = null;
		try {
			UserProfileCollection userProfileCollection = userAuthenticationBusinessService.getUserProfileCollectionInitialAuthentication(auth0Token, auth0Subject, accessToken);
			if(null != userProfileCollection) {
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				authorities.add(new Authorities("ROLE_INITIAL_AUTH"));
				authenticatedUser = new AuthenticatedUser(1L, userProfileCollection.getEmail(), userProfileCollection.getFirst_name(), userProfileCollection.getLast_name(), userProfileCollection.getEmail(), (Math.random() + ""), authorities, true, null, false, false, userProfileCollection);
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return authenticatedUser;
	}
	
	public UserDetails loadAuthenticatedUser(String token) throws UsernameNotFoundException, ApplicationException {
		AuthenticatedUser authenticatedUser = null;
		try {
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		return authenticatedUser;
	}

}
