package org.shopcart.servlet;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.shopcart.model.Category;
import org.shopcart.model.Customer;
import org.shopcart.model.Order;
import org.shopcart.model.Product;
import org.shopcart.service.CategoryEJB;
import org.shopcart.service.CustomerEJB;
import org.shopcart.service.OrderEJB;
import org.shopcart.service.ProductEJB;


@WebServlet("/Init")
public class Init extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@EJB
	CustomerEJB customerEJB;

	@EJB
	ProductEJB productEJB;

	@EJB
	CategoryEJB categoryEJB;

	@EJB
	OrderEJB orderEJB;

	public Init()
	{
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		for (long i = 0x0; i < 0x5; i++)
		{
			Customer customer = new Customer();
			customer.setAddress("address" + i);
			customer.setName("name" + i);
			customer.setPassword("pass" + i);
			customer.setPhone("phone" + i);
			customer.setSurname("surname" + i);
			customer.setUsername("uname" + i);

			Category category = new Category();
			category.setName("name" + i);

			Product product = new Product();
			product.setName("productname" + i);
			product.setDescription("Aa0Aa1Aa2Aa3Aa4Aa5Aa6Aa7Aa8Aa9A" + i);
			product.setPrice(i + 1d);
			product.setAvailability(199L);
			product.setCategory(category);

			categoryEJB.create(category);
			productEJB.create(product);
			customerEJB.create(customer);

			Order order = new Order();
			order.setCustomer(customer);
			order.setDate(new Date());
			orderEJB.create(order);
		}

		response.getWriter().append("Intialized!");
	}
}
