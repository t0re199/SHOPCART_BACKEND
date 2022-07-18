package org.shopcart.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "product")
public class Product implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", 
			updatable = false, 
			nullable = false)
	private Long product_id;
	
	@Column(updatable = false, 
			nullable = false)
	private String name;

	@Column(updatable = false, 
			nullable = false)
	private String description;
	
	@Column(updatable = true, 
			nullable = false)
	private Double price;
	
	@Column(updatable = true, 
			nullable = false)
	private Long availability;
	
	@ManyToOne(optional=false, 
			   targetEntity=Category.class)
	private Category category;
	
	@Version
    private long version;
	
	public long getVersion()
	{
		return version;
	}
	
	public void setVersion(long version)
	{
		this.version = version;
	}
	
	public Category getCategory()
	{
		return category;
	}
	
	public void setCategory(Category category)
	{
		this.category = category;
	}
	
	public Long getAvailability()
	{
		return availability;
	}
	
	public void setAvailability(Long availability)
	{
		this.availability = availability;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public Double getPrice()
	{
		return price;
	}
	
	public void setPrice(Double price)
	{
		this.price = price;
	}

	public Long getId()
	{
		return product_id;
	}

	public void setId(Long id)
	{
		this.product_id = id;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (!(obj instanceof Product))
		{
			return false;
		}
		Product other = (Product) obj;
		if (product_id != null)
		{
			if (!product_id.equals(other.product_id))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product_id == null) ? 0 : product_id.hashCode());
		return result;
	}
}