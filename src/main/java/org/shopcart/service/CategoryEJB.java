package org.shopcart.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.shopcart.model.Category;

@Stateless
public class CategoryEJB
{
	@PersistenceContext(unitName = "RestTest-persistence-unit")
	private EntityManager em;

	public void create(Category entity)
	{
		em.persist(entity);
	}

	public void deleteById(Long id)
	{
		Category entity = em.find(Category.class, id);
		if (entity != null)
		{
			em.remove(entity);
		}
	}

	public Category findById(Long id)
	{
		return em.find(Category.class, id);
	}

	public Category update(Category entity)
	{
		return em.merge(entity);
	}

	public List<Category> listAll(Integer startPosition, Integer maxResult)
	{
		TypedQuery<Category> findAllQuery = em.createQuery(
				"SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.products ORDER BY c.id",
				Category.class);
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
