package org.shopcart.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="order_detail")
public class OrderDetail implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	OrderDetailPrimaryKey orderDetailPrimaryKey = new OrderDetailPrimaryKey();
	
	@ManyToOne
	@MapsId("product_id")
	@JoinColumn(name="product_id")
	private Product product;
	
	@ManyToOne
	@MapsId("order_id")
	@JoinColumn(name="order_id")
	private Order order;
	
	private long amount;
	
	private Double boughtPrice;
	
	@Version
	@Column(name = "version")
	private Long version;
	
	public Product getProduct()
	{
		return product;
	}
	
	public void setProduct(Product product)
	{
		this.product = product;
	}
	
	public Order getOrder()
	{
		return order;
	}
	
	public void setOrder(Order order)
	{
		this.order = order;
	}

	public void setAmount(long amount)
	{
		this.amount = amount;
	}
	
	public long getAmount()
	{
		return amount;
	}
	
	public void setBoughtPrice(Double boughtPrice)
	{
		this.boughtPrice = boughtPrice;
	}
	
	public Double getBoughtPrice()
	{
		return boughtPrice;
	}
	
	public void setVersion(Long version)
	{
		this.version = version;
	}
	
	public Long getVersion()
	{
		return version;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		OrderDetail other = (OrderDetail) obj;
		if (order == null)
		{
			if (other.order != null)
				return false;
		}
		else
			if (!order.equals(other.order))
				return false;
		if (product == null)
		{
			if (other.product != null)
				return false;
		}
		else
			if (!product.equals(other.product))
				return false;
		return true;
	}

}