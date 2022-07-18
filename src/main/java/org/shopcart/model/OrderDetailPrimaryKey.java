package org.shopcart.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class OrderDetailPrimaryKey implements Serializable
{
	private static final long serialVersionUID = 2751749066479053780L;
	
	private Long product_id;
	
	private Long order_id;
	
	public OrderDetailPrimaryKey()
	{
	}
	
	public OrderDetailPrimaryKey(Long product_id, Long order_id)
	{
		this.product_id = product_id;
		this.order_id = order_id;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order_id == null) ? 0 : order_id.hashCode());
		result = prime * result + ((product_id == null) ? 0 : product_id.hashCode());
		return result;
	}

	public Long getProduct_id()
	{
		return product_id;
	}

	public void setProduct_id(Long product_id)
	{
		this.product_id = product_id;
	}

	public Long getOrder_id()
	{
		return order_id;
	}

	public void setOrder_id(Long order_id)
	{
		this.order_id = order_id;
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
		OrderDetailPrimaryKey other = (OrderDetailPrimaryKey) obj;
		if (order_id == null)
		{
			if (other.order_id != null)
				return false;
		}
		else
			if (!order_id.equals(other.order_id))
				return false;
		if (product_id == null)
		{
			if (other.product_id != null)
				return false;
		}
		else
			if (!product_id.equals(other.product_id))
				return false;
		return true;
	}
}
