package org.shopcart.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.shopcart.model.Customer;
import org.shopcart.model.Order;

@Stateless
public class OrderEJB
{
	@PersistenceContext(unitName = "RestTest-persistence-unit")
	private EntityManager em;

	public void create(Order entity)
	{
		Customer customer = em.find(Customer.class, entity.getCustomer().getUsername());
		customer = em.merge(customer);
		entity.setCustomer(customer);
		em.persist(entity);
	}

	public void deleteById(Long id)
	{
		Order entity = em.find(Order.class, id);
		if (entity != null)
		{
			em.remove(entity);
		}
	}

	public Order findById(Long id)
	{
		return em.find(Order.class, id);
	}

	public Order update(Order entity)
	{
		return em.merge(entity);
	}

	public List<Order> listAll(Integer startPosition, Integer maxResult)
	{
		TypedQuery<Order> findAllQuery = em.createQuery(
				"SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.customer LEFT JOIN FETCH o.orderDetails ORDER BY o.id",
				Order.class);
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
