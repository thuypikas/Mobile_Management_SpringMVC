package controllers;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import entities.Categories;
import entities.Products;

@Controller
@Transactional
public class HomeController {
	@Autowired
	SessionFactory factory;
	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}
	@RequestMapping(value = "index")
	public String index2() {
		return "index";
	}
	@RequestMapping(value = "contact")
	public String contac() {
		return "contact";
	}
	@ModelAttribute("products")
	public List<Products> getlist() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Products order";
		Query query = session.createQuery(hql);
		query.setFirstResult(1);
		query.setMaxResults(6);
		List<Products> list = query.list();
		return list;
	}
}
