package org.shopcart.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="sp_order")
public class Order implements Serializable 
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", 
			updatable = false, 
			nullable = false)
	private Long order_id;
	
	@ManyToOne(optional = false, 
			   targetEntity = Customer.class)
	private Customer customer;
	
	@Column(name = "date", 
			updatable = false, 
			nullable = false)
	private Date date;

	@OneToMany(mappedBy="order", 
			   fetch = FetchType.EAGER, 
			   cascade={CascadeType.MERGE})
	private Collection<OrderDetail> orderDetails = new LinkedList<>();
	
	public Long getOrder_id()
	{
		return order_id;
	}
	
	public void setOrder_id(Long order_id)
	{
		this.order_id = order_id;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
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
		result = prime * result + ((order_id == null) ? 0 : order_id.hashCode());
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
		Order other = (Order) obj;
		if (order_id == null)
		{
			if (other.order_id != null)
				return false;
		}
		else
			if (!order_id.equals(other.order_id))
				return false;
		return true;
	}
}