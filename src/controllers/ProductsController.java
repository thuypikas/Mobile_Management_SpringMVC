package controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import entities.Products;

@Controller
@Transactional
@RequestMapping("products")
public class ProductsController {
	@Autowired
	SessionFactory factory;

	@RequestMapping("")
	public String showproducts(ModelMap model, HttpServletRequest request) {
		int page = 1;
		String sortby = "";
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("sortby") != null) {
			sortby = (String) httpSession.getAttribute("sortby");
		}
		if (request.getParameter("sortby") != null) {
			sortby = request.getParameter("sortby");
			httpSession.setAttribute("sortby", sortby);
		}
		String hql = "FROM Products";
		switch(sortby){
		case "name-asc":hql = "FROM Products order by productname";break;
		case "name-desc":hql = "FROM Products order by productname desc";break;
		case "price-asc":hql = "FROM Products order by unitprice";break;
		case "price-desc":hql = "FROM Products order by unitprice desc";break;
		}
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Products> list1 = query.list();
		int soluong = list1.size();
		query.setFirstResult((page - 1) * 9);
		query.setMaxResults(9);
		List<Products> list2 = query.list();
		model.addAttribute("products", list2);
		model.addAttribute("soluong", soluong);
		model.addAttribute("paging", page);
		model.addAttribute("sortby", sortby);
		System.out.println(soluong);
		return "products";
	}

	@RequestMapping(params = "supid")
	public String showcategories(ModelMap model, HttpServletRequest request, @RequestParam("supid") int supid) {
		int page = 1;String sortby = "";
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		HttpSession httpSession = request.getSession();
		if (httpSession.getAttribute("sortby") != null) {
			sortby = (String) httpSession.getAttribute("sortby");
		}
		if (request.getParameter("sortby") != null) {
			sortby = request.getParameter("sortby");
			httpSession.setAttribute("sortby", sortby);
		}
		String hql = "FROM Products where supplier.supplierid=:supid";
		switch(sortby){
		case "name-asc":hql = "FROM Products where supplier.supplierid=:supid order by productname";break;
		case "name-desc":hql = "FROM Products where supplier.supplierid=:supid order by productname desc";break;
		case "price-asc":hql = "FROM Products where supplier.supplierid=:supid order by unitprice";break;
		case "price-desc":hql = "FROM Products where supplier.supplierid=:supid order by unitprice desc";break;
		}
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("supid", supid);
		List<Products> list1 = query.list();
		int soluong = list1.size();
		query.setFirstResult((page - 1) * 9);
		query.setMaxResults(9);
		List<Products> list2 = query.list();
		model.addAttribute("products", list2);
		model.addAttribute("soluong", soluong);
		model.addAttribute("paging", page);
		model.addAttribute("supid", supid);
		return "products";
	}
	@RequestMapping(params = "prdid")
	public String single(ModelMap model, @RequestParam("prdid") int prdid) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Products where productid=:prdid";
		Query query = session.createQuery(hql);
		query.setParameter("prdid", prdid);
		List<Products> list = query.list();
		Products product = list.get(0);
		model.addAttribute("product", product);
		System.out.println(product.getProductname());
		return "product_details";
	}
	// @RequestMapping(params="sortby")
	// public String sort(HttpSession httpSession,@RequestParam("sortby")int
	// sortby){
	// httpSession.setAttribute("sortby", sortby);
	// return "products";
	// }
}
