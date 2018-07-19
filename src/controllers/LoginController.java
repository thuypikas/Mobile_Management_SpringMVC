package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import entities.Accounts;
import entities.Customers;
import entities.Products;
import models.Serialization;

@Controller
@Transactional
public class LoginController {
	@Autowired
	SessionFactory factory;

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		HttpSession httpSession=request.getSession();
		if(httpSession.getAttribute("user")!=null)return "index";
		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String authentication(HttpServletRequest request,HttpServletResponse response,ModelMap model, @RequestParam("username")String username,@RequestParam("password")String password) {
		Session session=factory.getCurrentSession();
		String hql="From Accounts where UserName=:username and PassWord=:password";
		Query query= session.createQuery(hql);
		query.setParameter("username", username);
		String pass=null;
		try {
			pass= new Serialization().toString(password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query.setParameter("password", pass);
		List<Accounts>list=query.list();
		if(list.size()>0){
			Accounts account=list.get(0);
			Customers customer=account.getCustomer();
			HttpSession httpSession=request.getSession();
			httpSession.setAttribute("user", customer);
			Session session1 = factory.getCurrentSession();
			String hql1 = "FROM Products order";
			Query query1 = session1.createQuery(hql1);
			query1.setFirstResult(1);
			query1.setMaxResults(6);
			List<Products> list1 = query1.list();
			model.addAttribute("products", list1);
			return "index";
		}
		model.addAttribute("message", "Đăng nhập thất bại");
		return "login";
	}
	@RequestMapping(value="login",params="logout")
	public String logout(HttpServletRequest request){
		HttpSession session=request.getSession();
		session.invalidate();
		return "index";
	}
}
