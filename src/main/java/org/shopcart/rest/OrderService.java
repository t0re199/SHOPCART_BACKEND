package org.shopcart.rest;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.shopcart.rest.request.OrderRequest;
import org.shopcart.rest.responses.OrderResponse;
import org.shopcart.jwt.CookieUtils;
import org.shopcart.jwt.JWTSUtils;
import org.shopcart.jwt.JwtWrapper;
import org.shopcart.model.Order;
import org.shopcart.service.OrderCreationEJB;
import org.shopcart.service.OrderEJB;
import org.shopcart.service.exceptions.OrderException;

@RequestScoped
@Path("/orders")
@Produces("application/json")
@Consumes("application/json")
public class OrderService
{

	@EJB
	OrderCreationEJB orderCreationEJB;
	
	@EJB
	OrderEJB oderEJB;
	
	@POST
	public Response create(@CookieParam(CookieUtils.COOKIE_NAME) Cookie cookie, final OrderRequest orderRequest)
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
			try
			{
				orderCreationEJB.createOrder(orderRequest);
				ResponseBuilder responseBuilder = Response.ok(OrderResponse.SUCCESS());
				if(jwtWrapper.needsUpdate())
				{
					responseBuilder = responseBuilder.cookie(CookieUtils.createCookie(jwtWrapper.getToken()));
				}
				return responseBuilder.build();
			}
			catch (OrderException orderException) 
			{
				ResponseBuilder responseBuilder = Response.ok(orderException.getResponse());
				if(jwtWrapper.needsUpdate())
				{
					responseBuilder = responseBuilder.cookie(CookieUtils.createCookie(jwtWrapper.getToken()));
				}
				return responseBuilder.build();
			}
		}
		return Response.status(Status.FORBIDDEN).build();
	}

	
	@POST
	@Path("/orderDetail")
	@Produces("application/json")
	public Response make(final OrderRequest orderRequest)
	{
		return Response.ok(true)
						   .build();
	}
	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id)
	{
		Order order = oderEJB.findById(id);
		if (order == null)
		{
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(order).build();
	}

}
