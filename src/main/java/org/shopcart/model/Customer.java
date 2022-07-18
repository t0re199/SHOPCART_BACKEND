package org.shopcart.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "username", 
			updatable = false, 
			nullable = false)
	private String username;
	
	@Column(name = "password", 
			updatable = false, 
			nullable = false)
	private String password;
	
	@Column(name = "name", 
			updatable = false, 
			nullable = false)
	private String name;
	
	@Column(name = "surname", 
			updatable = false, 
			nullable = false)
	private String surname;
	
	@Column(name = "address", 
			updatable = false, 
			nullable = true)
	private String address;
	
	@Column(name = "phone", 
			updatable = false, 
			nullable = true)
	private String phone;

	@OneToMany(cascade={CascadeType.ALL}, 
			   targetEntity=Order.class,
			   mappedBy="customer")
    private Set<Order> orders;
	
	public Customer()
	{
	}

	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSurname()
	{
		return surname;
	}

	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		Customer other = (Customer) obj;
		if (username == null)
		{
			if (other.username != null)
				return false;
		}
		else
			if (!username.equals(other.username))
				return false;
		return true;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}
}