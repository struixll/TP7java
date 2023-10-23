package com.applicationpanier.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.applicationpanier.panier.Product;
import com.applicationpanier.panier.CartBeanLocal;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

	@EJB
	private CartBeanLocal cartBean;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			request.setAttribute("cartItems", cartBean.getCartItems());
			request.getRequestDispatcher("/cart.jsp").forward(request, response);
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
			String productCode = request.getParameter("productCode");
			String productLibelle = request.getParameter("productLibelle");
			String productDescription = request.getParameter("productDescription");
			Product product = new Product();
			product.setCode(productCode);
			product.setLibelle(productLibelle);
			product.setDescription(productDescription);
	
			cartBean.addProductToCart(product);
			cartBean.checkOut();
	
			String productIdToDelete = request.getParameter("productIdToDelete");
			cartBean.removeProduct(productIdToDelete);

		}

	@PersistenceContext
	private EntityManager entityManager;

	public void persistProduct(Product product) {
		entityManager.persist(product); 
	}

}
