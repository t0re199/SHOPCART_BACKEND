package org.shopcart.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.shopcart.rest.request.OrderRequest;
import org.shopcart.rest.responses.OrderResponse;
import org.shopcart.model.Order;
import org.shopcart.model.OrderDetail;
import org.shopcart.model.Product;
import org.shopcart.service.exceptions.OrderException;

@Stateless
@LocalBean
public class OrderCreationEJB implements Serializable
{
	private static final long serialVersionUID = -1L;
	
	@EJB
	CustomerEJB customerEJB;
	
	@EJB
	ProductEJB productEJB;
	
	@EJB
	OrderEJB orderEJB;
	
	@EJB
	OrderDetailEJB orderDetailEJB;
	
	@EJB
	OrderCreationEJB orderCreationEJB;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void createOrder(OrderRequest orderRequest) throws OrderException
	{
		Collection<OrderDetail> orderDetails = orderRequest.getOrderDetails();

		Order newOrder = new Order();
		newOrder.setCustomer(orderRequest.getOrder().getCustomer());
		newOrder.setDate(new Date());
		orderEJB.create(newOrder);
		for(OrderDetail orderDetail : orderDetails)
		{
			Product localProduct = productEJB.findById(orderDetail.getProduct().getId());
			
			if(localProduct == null)
			{
				throw new OrderException(localProduct, OrderResponse.PRODUCT_NOT_FOUND());
			}
			
			if(localProduct.getAvailability() < orderDetail.getAmount())
			{
				throw new OrderException(localProduct, OrderResponse.PRODUCT_AVAILABILITY_NOT_ENOUGH());
			}
			
			if(localProduct.getPrice().compareTo(orderDetail.getBoughtPrice()) != 0x0)
			{
				throw new OrderException(localProduct, OrderResponse.PRODUCT_PRICE_CHANGED());
			}
			
			orderDetail.setProduct(localProduct);
			orderDetail.setOrder(newOrder);
			orderDetailEJB.create(orderDetail);
			
			newOrder.getOrderDetails().add(orderDetail);
			
			localProduct.setAvailability(localProduct.getAvailability() - orderDetail.getAmount());
		}
	}

}