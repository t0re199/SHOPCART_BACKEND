package org.shopcart.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.shopcart.model.Product;
import org.shopcart.service.ProductEJB;

@RequestScoped
@Path("/products")
@Produces("application/json")
@Consumes("application/json")
public class ProductService
{
	@EJB
	ProductEJB productEJB;

	@GET
	@Path("/{id:[0-9][0-9]*}")
	public Response findById(@PathParam("id") final Long id)
	{
		Product product = productEJB.findById(id);
		if (product == null)
		{
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(product).build();
	}

	@GET
	public List<Product> listAll(
			@QueryParam("start") final Integer startPosition,
			@QueryParam("max") final Integer maxResult)
	{
		final List<Product> products = productEJB.listAll(startPosition, maxResult);
		return products;
	}

}
