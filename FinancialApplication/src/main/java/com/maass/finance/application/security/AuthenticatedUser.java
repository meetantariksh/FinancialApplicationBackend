package com.maass.finance.application.security;

import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.maass.finance.application.collections.UserProfileCollection;

public class AuthenticatedUser implements UserDetails {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    private boolean enabled;
    private Date lastPasswordResetDate;
    private boolean isAccountLocked;
    private boolean isAccountExpired;
    private UserProfileCollection userProfileCollection;
    
    public AuthenticatedUser(
            Long id,
            String username,
            String firstname,
            String lastname,
            String email,
            String password, Collection<? extends GrantedAuthority> authorities,
            boolean enabled,
            Date lastPasswordResetDate,
            boolean isAccountLocked,
            boolean isAccountExpired,
            UserProfileCollection userProfileCollection
      ) {
          this.id = id;
          this.username = username;
          this.firstname = firstname;
          this.lastname = lastname;
          this.email = email;
          this.password = password;
          this.authorities = authorities;
          this.enabled = enabled;
          this.lastPasswordResetDate = lastPasswordResetDate;
          this.isAccountLocked = isAccountLocked;
          this.isAccountExpired = isAccountExpired;
          this.userProfileCollection = userProfileCollection;
      }

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return !isAccountExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !isAccountLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	public UserProfileCollection getUserProfileCollection() {
		return userProfileCollection;
	}

}
