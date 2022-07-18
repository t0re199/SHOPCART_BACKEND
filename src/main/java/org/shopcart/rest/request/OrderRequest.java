package org.shopcart.rest.request;

import java.util.Collection;

import org.shopcart.model.Order;
import org.shopcart.model.OrderDetail;

public class OrderRequest
{
	private Order order;
	
	private Collection<OrderDetail> orderDetails;

	public Order getOrder()
	{
		return order;
	}

	public void setOrder(Order order)
	{
		this.order = order;
	}

	public Collection<OrderDetail> getOrderDetails()
	{
		return orderDetails;
	}

	public void setOrderDetails(Collection<OrderDetail> orderDetails)
	{
		this.orderDetails = orderDetails;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result
				+ ((orderDetails == null) ? 0 : orderDetails.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderRequest other = (OrderRequest) obj;
		if (order == null)
		{
			if (other.order != null)
				return false;
		}
		else
			if (!order.equals(other.order))
				return false;
		if (orderDetails == null)
		{
			if (other.orderDetails != null)
				return false;
		}
		else
			if (!orderDetails.equals(other.orderDetails))
				return false;
		return true;
	}
}
