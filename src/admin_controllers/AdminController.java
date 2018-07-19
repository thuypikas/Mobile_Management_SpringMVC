package admin_controllers;

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
import models.Serialization;

@Controller
@Transactional
@RequestMapping("admin")
public class AdminController {
	@Autowired
	SessionFactory factory;

	@RequestMapping("")
	public String adpagae() {
		return "admin/dashboard";
	}

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpServletRequest request) {
		HttpSession httpSession=request.getSession();
		if(httpSession.getAttribute("admin")!=null)return "admin/dashboard";
		return "admin/login";
	}
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String authentication(HttpServletRequest request,HttpServletResponse response,ModelMap model, @RequestParam("username")String username,@RequestParam("password")String password) {
		Session session=factory.getCurrentSession();
		String hql="From Accounts where UserName=:username and PassWord=:password";
		Query query= session.createQuery(hql);
		query.setParameter("username", username);
		String pass=null;
		try {
			pass=new Serialization().toString(password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		query.setParameter("password", pass);
		List<Accounts>list=query.list();
		if(list.size()>0&&username.equals("admin")){
			Accounts account=list.get(0);
			Customers customer=account.getCustomer();
			HttpSession httpSession=request.getSession();
			httpSession.setAttribute("admin", customer);
			return "admin/dashboard";
		}
		model.addAttribute("message", "Đăng nhập thất bại");
		return "admin/login";
	}
	@RequestMapping(params="logout")
	public String logout(HttpServletRequest request) {
		HttpSession session=request.getSession();
		session.invalidate();
		return "admin/login";
	}
}
