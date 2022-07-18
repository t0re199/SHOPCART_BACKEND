package org.shopcart.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.shopcart.model.Product;

@Stateless
public class ProductEJB
{
	@PersistenceContext(unitName = "RestTest-persistence-unit")
	private EntityManager em;

	public void create(Product entity)
	{
		em.persist(entity);
	}

	public void deleteById(Long id)
	{
		Product entity = em.find(Product.class, id);
		if (entity != null)
		{
			em.remove(entity);
		}
	}

	public Product findById(Long id)
	{
		return em.find(Product.class, id);
	}

	public Product update(Product entity)
	{
		return em.merge(entity);
	}

	public List<Product> listAll(Integer startPosition, Integer maxResult)
	{
		TypedQuery<Product> findAllQuery = em.createQuery(
				"SELECT DISTINCT p FROM Product p ORDER BY p.id",
				Product.class);
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
