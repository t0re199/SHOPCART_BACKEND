package org.shopcart.jwt;

import javax.ws.rs.core.NewCookie;

public final class CookieUtils
{
	public static final String COOKIE_NAME = "jwtCookie";
	private static final int COOKIE_MAX_AGE = 3600;
	
	private CookieUtils()
	{
	}
	
	public static NewCookie createCookie(String token)
	{
		return new NewCookie(COOKIE_NAME, 
				token, 
				"/", 
				null,
				"",
				COOKIE_MAX_AGE, 
				false, 
				true);
	}
}
