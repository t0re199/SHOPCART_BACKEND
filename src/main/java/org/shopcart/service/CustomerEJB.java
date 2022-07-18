package org.shopcart.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.shopcart.model.Customer;

@Stateless
public class CustomerEJB
{
	@PersistenceContext(unitName = "RestTest-persistence-unit")
	private EntityManager em;

	public Customer create(Customer entity)
	{
		em.persist(entity);
		return entity;
	}
	
	public Customer merge(Customer customer)
	{
		return em.merge(customer);
	}

	public void deleteById(String id)
	{
		Customer entity = em.find(Customer.class, id);
		if (entity != null)
		{
			em.remove(entity);
		}
	}
	
	public Customer findById(String id)
	{
		return em.find(Customer.class, id);
	}

	public Customer update(Customer entity)
	{
		return em.merge(entity);
	}

	public List<Customer> listAll(Integer startPosition, Integer maxResult)
	{
		TypedQuery<Customer> findAllQuery = em.createQuery(
				"SELECT DISTINCT c FROM Customer c LEFT JOIN FETCH c.orders ORDER BY c.username",
				Customer.class);
		if (startPosition != null)
		{
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null)
		{
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}
}
