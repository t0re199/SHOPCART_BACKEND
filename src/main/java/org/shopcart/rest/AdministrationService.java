package org.shopcart.rest;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.shopcart.rest.responses.RegistrationResponse;
import org.shopcart.model.Customer;
import org.shopcart.service.CustomerEJB;

@Path("/administrationService")
@Consumes("application/json")
@Produces("application/json")
public class AdministrationService
{
	@EJB
	CustomerEJB customerEJB;
	
	@POST
	public Response insertCustomer(Customer customer)
	{
		String field = customer != null ? customer.getUsername() : null;
		if(field == null || field.isEmpty())
		{
			return Response.serverError()
						   .status(Response.Status.BAD_REQUEST)
						   .build();
		}
		if(!field.matches("[a-zA-Z0-9\\._\\-]{3,}"))
		{
			return Response.ok(RegistrationResponse.NOT_ALLOWED_CHARS())
					       .build();
		}
		Customer local = customerEJB.findById(field);
		if(local != null)
		{
			return Response.ok(RegistrationResponse.ALREADY_USED_UNAME())
						   .build();
		}
		field = customer.getName();
		if(field == null || !field.matches("[a-zA-Z]+"))
		{
			return Response.ok(RegistrationResponse.INVALID_DATA())
				       .build();
		}
		field = customer.getSurname();
		if(field == null || !field.matches("[a-zA-Z]+"))
		{
			return Response.ok(RegistrationResponse.INVALID_DATA())
				       .build();
		}
		customer = customerEJB.create(customer);
		return Response.ok(RegistrationResponse.SUCCESS()).build();
	}
}