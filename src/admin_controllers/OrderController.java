package admin_controllers;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import entities.Categories;
import entities.Orders;

@Controller
@Transactional
@RequestMapping("admin/orders")
public class OrderController {
	@Autowired
	SessionFactory factory;

	@RequestMapping("")
	public String order(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Orders";
		Query query = session.createQuery(hql);
		List<Orders> list = query.list();
		model.addAttribute("orders", list);
		return "admin/orders";
	}
}
