package org.shopcart.rest;

import java.util.Random;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.shopcart.jwt.CookieUtils;
import org.shopcart.jwt.JWTSUtils;
import org.shopcart.jwt.JwtWrapper;
import org.shopcart.model.Customer;
import org.shopcart.service.CustomerEJB;

@Path("/authenticationService")
public class AuthenticationService
{
	private Random random = new Random();
	
	@EJB
	CustomerEJB customerEJB;
	
	@POST
	@Path("/login")
	@Consumes("application/json")
	@Produces("application/json")
	public Response login(Customer customer)
	{
		Customer result = customerEJB.findById(customer.getUsername());
		if(result != null && result.getPassword().equals(customer.getPassword()))
		{
			String token = JWTSUtils.createJWT(random.nextLong(), result.getUsername());
			
			return Response.ok(true)
					 	   .cookie(CookieUtils.createCookie(token))
						   .build();
		}
		return Response.ok(false).build();
	}
	
	@POST
	@Path("/logout")
	@Produces("application/json")
	public Response logout(@CookieParam(CookieUtils.COOKIE_NAME) Cookie cookie)
	{
		if(cookie == null)
		{
			return Response.serverError().status(Response.Status.BAD_REQUEST).build();
		}
		
		return Response.ok(true)
						.cookie(CookieUtils.createCookie("null"))
						.build();
	}
	
	
	@POST
	@Path("/verifyCookie")
	@Produces("application/json")
	public Response verify(@CookieParam(CookieUtils.COOKIE_NAME) Cookie cookie)
	{
		if(cookie == null)
		{
			return Response.serverError().status(Response.Status.BAD_REQUEST).build();
		}
		
		String token = cookie.getValue();
		JwtWrapper jwtWrapper = new JwtWrapper(token); 
		JWTSUtils.checkAndUpdateJwt(jwtWrapper);
		
		if(jwtWrapper.isValid())
		{
			ResponseBuilder responseBuilder = Response.ok(true);
			if(jwtWrapper.needsUpdate())
			{
				responseBuilder = responseBuilder.cookie(CookieUtils.createCookie(jwtWrapper.getToken()));
			}
			
			return responseBuilder.build();
		}
		
		return Response.ok(false).build();
	}
}