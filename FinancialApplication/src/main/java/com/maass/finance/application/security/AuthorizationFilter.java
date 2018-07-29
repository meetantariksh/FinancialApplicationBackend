package com.maass.finance.application.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClock;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.maass.finance.application.business.service.UserAuthenticationBusinessService;
import com.maass.finance.application.helpers.Constants;
import com.maass.finance.application.helpers.StringHandlers;

@Component
public class AuthorizationFilter extends OncePerRequestFilter  { 

	private Clock clock = DefaultClock.INSTANCE;

	@Autowired
	private AuthenticatedUserService authenticatedUserService;

	@Autowired
	private UserAuthenticationBusinessService userAuthenticationService;

	@Value("${application.authorization.header.name}")
	private String authorizationHeaderName;

	@Value("${application.authorization.header.prefix}")
	private String authorizationTokenPrefix;

	@Value("${application.authentication.token.constant}")
	private String authenticationTokenConstant;

	@Value("${initial.authentication.token.subject}")
	private String initialAuthenticationTokenSubject;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
					throws ServletException, IOException {

		System.out.println("Entering Authorization Filter");
		String authToken = null;
		String requestHeader = null;

		try {
			requestHeader = request.getHeader(authorizationHeaderName);
			if(!StringHandlers.isStringNullOrEmpty(requestHeader) && requestHeader.startsWith(authorizationTokenPrefix)) {
				authToken = requestHeader.split(authorizationTokenPrefix)[1];
				if(!StringHandlers.isStringNullOrEmpty(authToken)) {
					Claims claims = Jwts.parser()
							.setSigningKey(authenticationTokenConstant)
							.parseClaimsJws(authToken)
							.getBody();

					String subject = claims.getSubject();
					String accessToken = claims.get("access_token").toString();
					String auth0Subject = claims.get("auth0_subject").toString();
					String auth0Token = claims.get("auth0_token").toString();
					String authType = claims.get("authentication_type").toString();
					String email = claims.get("email").toString();
					if(initialAuthenticationTokenSubject.equals(subject)) {
						if(Constants.AUTHENTICATION_INPROGRESS.equals(authType)) {
							if(!StringHandlers.isStringNullOrEmpty(email)
									&& !StringHandlers.isStringNullOrEmpty(accessToken)
									&& !StringHandlers.isStringNullOrEmpty(auth0Token)
									&& !StringHandlers.isStringNullOrEmpty(auth0Subject)) {
								UserDetails userDetails = authenticatedUserService.loadInitialAuthenticationUser(auth0Subject, auth0Token, accessToken);
								UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
								authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
								SecurityContextHolder.getContext().setAuthentication(authentication);
							}
						}
					}else {

					}
				}
			}

		}catch(ExpiredJwtException e){

		}catch(Exception e) {
			e.printStackTrace();
		}
		filterChain.doFilter(request, response);
	}

	private boolean isTokenExpired(Date expirationDate) {
		boolean isExpired = true;
		try {
			isExpired = expirationDate.before(clock.now());
		}catch(Exception e){
			e.printStackTrace();
		}
		return isExpired;
	}

}
