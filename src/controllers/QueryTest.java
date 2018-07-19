package controllers;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import entities.Accounts;
import entities.Categories;
import entities.Customers;
import entities.Products;
import entities.Suppliers;

@Controller
@Transactional
public class QueryTest {
	@Autowired
	SessionFactory factory;

	@RequestMapping(value = "query1")
	public String query1(ModelMap model) {
		Session session=factory.getCurrentSession();
		String hql="FROM Categories";
		Query query=session.createQuery(hql);
		List<Categories>list=query.list();
		model.addAttribute("list", list);
		return "query";
	}
	@RequestMapping(value = "query2")
	public String query2(ModelMap model) {
		Session session=factory.getCurrentSession();
		String hql="FROM Products";
		Query query=session.createQuery(hql);
		List<Products>list=query.list();
		model.addAttribute("list", list);
		return "query";
	}
	@RequestMapping(value = "query3")
	public String query3(ModelMap model) {
		Session session=factory.getCurrentSession();
		String hql="FROM Suppliers";
		Query query=session.createQuery(hql);
		List<Suppliers>list=query.list();
		model.addAttribute("list", list);
		return "query";
	}
	@RequestMapping(value = "query4")
	public String query4(ModelMap model) {
		Session session=factory.getCurrentSession();
		String hql="FROM Customers";
		Query query=session.createQuery(hql);
		List<Customers>list=query.list();
		model.addAttribute("list", list);
		return "query";
	}
	@RequestMapping(value = "query5")
	public String query5(ModelMap model) {
		Session session=factory.getCurrentSession();
		String hql="FROM Accounts";
		Query query=session.createQuery(hql);
		List<Accounts>list=query.list();
		model.addAttribute("list", list);
		return "query";
	}
}
