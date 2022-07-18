package org.shopcart.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.shopcart.model.OrderDetail;
import org.shopcart.model.Product;

@Stateless
public class OrderDetailEJB
{
	@PersistenceContext(unitName = "RestTest-persistence-unit")
	private EntityManager em;

	public void create(OrderDetail entity)
	{
		entity.setOrder(em.merge(entity.getOrder()));
		entity.setProduct(em.merge(entity.getProduct()));
		em.persist(entity);
	}

	public void deleteById(Product id)
	{
		OrderDetail entity = em.find(OrderDetail.class, id);
		if (entity != null)
		{
			em.remove(entity);
		}
	}

	public OrderDetail findById(Product id)
	{
		return em.find(OrderDetail.class, id);
	}

	public OrderDetail update(OrderDetail entity)
	{
		return em.merge(entity);
	}

	public List<OrderDetail> listAll(Integer startPosition, Integer maxResult)
	{
		TypedQuery<OrderDetail> findAllQuery = em.createQuery(
				"SELECT DISTINCT o FROM OrderDetail o LEFT JOIN FETCH o.product LEFT JOIN FETCH o.order ORDER BY o.productORDER BY o.order",
				OrderDetail.class);
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
