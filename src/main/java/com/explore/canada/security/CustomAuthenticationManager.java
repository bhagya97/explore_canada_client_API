package com.explore.canada.security;
import com.explore.canada.bean.ServiceEndPoint;
import com.explore.canada.bean.UserInfo;
import com.explore.canada.configuration.Config;
import com.explore.canada.service.LoginService;
import com.explore.canada.service.RestServiceClient;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomAuthenticationManager implements AuthenticationManager
{
	private static final String ADMIN = "ADMIN";
	private static final String USER = "USER";

	private Authentication grantAdminRole(UserInfo u, Authentication authentication) throws AuthenticationException
	{
		// Grant ADMIN rights system-wide, this is used to protect controller mappings.
		List<GrantedAuthority> rights = new ArrayList<GrantedAuthority>();
		rights.add(new SimpleGrantedAuthority(ADMIN));
		// Return valid authentication token.
		UsernamePasswordAuthenticationToken token;
		token = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
				authentication.getCredentials(),
				rights);
		return token;
	}

	private Authentication grantNormalRole(UserInfo u, Authentication authentication) throws AuthenticationException
	{
		// Grant USER rights system-wide, this is used to protect controller mappings.
		List<GrantedAuthority> rights = new ArrayList<GrantedAuthority>();
		rights.add(new SimpleGrantedAuthority("USER"));
		// Return valid authentication token.
		UsernamePasswordAuthenticationToken token;
		token = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
				authentication.getCredentials(),
				rights);
		return token;
	}

	// Authenticate against our database using the input UserID and password.
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userId = authentication.getPrincipal().toString();
		String password = authentication.getCredentials().toString();
		UserInfo userAuth = Config.getInstance().getUserAuth();
		userAuth.setUserEmail(userId);
		userAuth.setUserPassword(password);
		LoginService loginService = Config.getInstance().getLoginService();
		UserInfo userInfo;
		try{
			userInfo = loginService.authenticateUser(userId,password);
		}
		catch (Exception e)
		{
			throw new AuthenticationServiceException("1000");
		}
		if (userInfo != null)
		{
			return grantNormalRole(userInfo, authentication);
		}
		else
		{
			// No user with this banner id found.
			throw new BadCredentialsException("1001");
		}
	}
}
