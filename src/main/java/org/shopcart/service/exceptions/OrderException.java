package org.shopcart.service.exceptions;

import javax.ejb.ApplicationException;

import org.shopcart.rest.responses.OrderResponse;
import org.shopcart.model.Product;

@ApplicationException(rollback=true)
public class OrderException extends Exception
{
	private static final long serialVersionUID = 290588419415727327L;

	private Product product;
	
	private OrderResponse response;
	
	public OrderException()
	{
		response = OrderResponse.GENERIC_ERROR();
	}

	public OrderException(Product product, OrderResponse type)
	{
		this.product = product;
		this.response = type;
	}

	public Product getProduct()
	{
		return product;
	}

	public OrderResponse getResponse()
	{
		return response;
	}
}
