package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import entities.Carts;
import entities.Customers;
import entities.Products;
import models.Serialization;

@Controller
@Transactional
@RequestMapping(value = "cart")
public class CartController {
	@Autowired
	SessionFactory factory;

	@RequestMapping("")
	public String show() {
		return "cart";
	}

	// update
	@RequestMapping(method = RequestMethod.POST)
	public void update(HttpServletRequest request, HttpServletResponse response,
			@CookieValue(value = "carts") String cookievalue, Carts cart) {
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("user") != null) {// có tài khoản
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.update(cart);
				t.commit();

			} catch (Exception e) {
				t.rollback();
			} finally {
				session.close();
			}
		} else {// ko có tài khoản
			Serialization s = new Serialization();
			try {
				ArrayList<Carts> carts = (ArrayList<Carts>) s.fromString(cookievalue);
				for (Carts c : carts) {
					int n1 = c.getProduct().getProductid();
					int n2 = cart.getProduct().getProductid();
					if (n1 == n2) {
						c.setQuantity(cart.getQuantity());
						break;
					}
				}
				// ghi lại cookie
				cookievalue = s.toString(carts);
				Cookie c = new Cookie("carts", cookievalue);
				c.setMaxAge(60 * 60 * 24);
				response.addCookie(c);
				// response.sendRedirect(request.getContextPath() + "/cart");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//finish
		try {
			response.sendRedirect(request.getContextPath() + "/cart");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// add
	@RequestMapping(params = "add")
	public void add(HttpServletRequest request, HttpServletResponse response,
			@CookieValue(value = "carts", defaultValue = "") String cartsvalue,
			@RequestParam("productid") Integer productid, Products product) {
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("user") != null) {// có tài khoản
			List<Carts> list = (List<Carts>) httpSession.getAttribute("cartlist");
			if (list.size() > 0)
				for (Carts c : list) {
					if (c.getProduct().getProductid().equals(productid)) {
						Session session = factory.openSession();
						Transaction t = session.beginTransaction();
						try {
							c.setQuantity(c.getQuantity() + 1);
							session.update(c);
							t.commit();
							break;
						} catch (Exception e) {
							t.rollback();
						} finally {
							session.close();
						}
					}
				}
			Carts cart = new Carts();
			Customers customer = (Customers) httpSession.getAttribute("user");
			Products product2 = new Products();
			product2.setProductid(productid);
			cart.setCustomer(customer);
			cart.setProduct(product2);
			cart.setQuantity(1);
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.save(cart);
				t.commit();
			} catch (Exception e) {
				t.rollback();
			} finally {
				session.close();
			}
		} else {// k có tài khoản
			if (cartsvalue.equals("")) {
				ArrayList<Carts> list = new ArrayList();
				Carts cart = new Carts();
				cart.setQuantity(1);
				cart.setProduct(product);
				list.add(cart);
				Serialization s = new Serialization();
				try {
					cartsvalue = s.toString(list);
					Cookie c = new Cookie("carts", cartsvalue);
					c.setMaxAge(60 * 60 * 24);
					response.addCookie(c);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				Serialization s = new Serialization();
				try {
					ArrayList<Carts> list = (ArrayList<Carts>) s.fromString(cartsvalue);
					if (list.size() > 0) {
						boolean check = true;
						for (Carts c : list) {
							if (c.getProduct().getProductid().equals(productid)) {
								c.setQuantity(c.getQuantity() + 1);
								check = false;
								break;
							}
						}
						if (check) {
							Carts cart = new Carts();
							cart.setQuantity(1);
							cart.setProduct(product);
							list.add(cart);
						}
					} else {
						Carts cart = new Carts();
						cart.setQuantity(1);
						cart.setProduct(product);
						list.add(cart);
					}
					cartsvalue = s.toString(list);
					Cookie c = new Cookie("carts", cartsvalue);
					c.setMaxAge(60 * 60 * 24);
					response.addCookie(c);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		//finish
		try {
			response.sendRedirect(request.getContextPath() + "/cart");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// remove
	@RequestMapping(params = "remove")
	public void remove(HttpServletRequest request, HttpServletResponse response,
			@CookieValue(value = "carts") String cookievalue, Carts cart) {
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("user") != null) {// có tài khoản
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.delete(cart);
				t.commit();

			} catch (Exception e) {
				t.rollback();
			} finally {
				session.close();
			}
		} else {// k có tài khoản
			Serialization s = new Serialization();
			try {
				ArrayList<Carts> list = (ArrayList<Carts>) s.fromString(cookievalue);
				int id1 = cart.getProduct().getProductid();
				for (Carts c : list) {
					int id2 = c.getProduct().getProductid();
					if (id2 == id1) {
						list.remove(c);
						break;
					}
				}
				cookievalue = s.toString(list);
				Cookie c = new Cookie("carts", cookievalue);
				c.setMaxAge(60 * 60 * 24);
				response.addCookie(c);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//finish
		try {
			response.sendRedirect(request.getContextPath() + "/cart");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
